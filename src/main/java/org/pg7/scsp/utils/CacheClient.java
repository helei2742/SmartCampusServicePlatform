package org.pg7.scsp.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Component
public class CacheClient {
    private final StringRedisTemplate stringRedisTemplate;

    public CacheClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 插入redis方法
     * @param key   key值
     * @param value value值
     * @param time  延时时间
     * @param unit  时间格式
     */
    public void set(String key, Object value, Long time, TimeUnit unit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    /**
     * redis 逻辑延时,插入
     * @param key   key值
     * @param value 原来的value值
     * @param time  延时
     * @param unit  时间格式
     */
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit){
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }


    /**
     * 缓存穿透，设置空缓存的过期时间,单位为分钟
     */
    private final long CACHE_NULL_TTL = 2l;
    /**
     * 添加缓存穿透处理的查询，
     * @param keyPrefix redis中key值的前缀
     * @param id        查找条件id，
     * @param type      查找出的类型
     * @param dbFallBack    查询数据库的函数
     * @param time          延迟时间
     * @param unit          延迟时间单位
     * @return
     * @param <R>           返回类型的范型
     * @param <ID>          id的范型
     */
    public  <R,ID>R queryWithPassThrough(String keyPrefix, ID id, Class<R> type,
                                         Function<ID, R> dbFallBack, Long time, TimeUnit unit){
        String CACHE_KEY = keyPrefix + id;
        String json = stringRedisTemplate.opsForValue().get(CACHE_KEY);
        if(StrUtil.isNotBlank(json)){
            return JSONUtil.toBean(json, type);
        }
        if("".equals(json)){
            return null;
        }
        R r = dbFallBack.apply(id);
        if(r == null){
            stringRedisTemplate.opsForValue().set(CACHE_KEY, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        this.set(CACHE_KEY, JSONUtil.toJsonStr(r), time, unit);
        return r;
    }

    /**
     * 线程池， 用于逻辑缓冲过期时，开启线程查询数据库更新缓存
     */
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    /**
     * redis实现互斥锁的key前缀
     */
    private static final String LOCK_KEY = "lock:key";

    /**
     *  设置逻辑过期,解决缓存击穿，
     * @param keyPrefix     redis中key值的前缀
     * @param id            查找条件id，
     * @param type          查找出的类型
     * @param dbQuery　    查询数据库的函数
     * @param time          延迟时间
     * @param unit          延迟时间单位
     * @return               查询出的结果
     * @param <R>           返回类型的范型
     * @param <ID>          id的范型
     */
    public <R,ID> R queryWithLogicExpire(String keyPrefix, ID id, Class<R> type, Function<ID,R> dbQuery, Long time, TimeUnit unit) {
        String CACHE_KEY = keyPrefix + id;

        String json = stringRedisTemplate.opsForValue().get(CACHE_KEY);
        if(StrUtil.isBlank(json)){
            return null;
        }

        RedisData redisData = JSONUtil.toBean(json, RedisData.class);
        JSONObject data = (JSONObject) redisData.getData();
        R r = JSONUtil.toBean(data, type);
        LocalDateTime expireTime = redisData.getExpireTime();

        if(expireTime.isAfter(LocalDateTime.now())){
            return r;
        }

        String lockKey = LOCK_KEY+id;
        boolean lock = tryLock(lockKey);
        if(lock){
            try{
                CACHE_REBUILD_EXECUTOR.submit(()->{
                    R r1 = dbQuery.apply(id);
                    this.setWithLogicalExpire(lockKey, r1, time, unit);
                });
            } finally {
                unLock(lockKey);
            }
        }
        return r;
    }

    /**
     * redis 实现互斥锁
     * @param key   锁的key
     * @return 是否获取成功
     */
    private boolean tryLock(String key){
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.MINUTES);
        return BooleanUtil.isTrue(flag);
    }

    /**
     * 释放锁
     * @param key 锁的key
     */
    private void unLock(String key){
        stringRedisTemplate.delete(key);
    }


    /**
     * 互斥锁解决缓存击穿问题，并且通过对缓存和数据库都没有的key设置空value解决缓存穿透问题
     * @param keyPrefix key的前缀
     * @param id        查询的id
     * @param type      查询出的类型
     * @param dbQuery   从数据库查询的方法
     * @param nullTime      解决缓存穿透问题空值key的过期时间
     * @param nullUnit      解决缓存穿透问题空值key的时间单位
     * @param cacheTime     对象被放到缓存中的过期时间
     * @param cacheUnit     过期时间的单位
     * @return              查询出的对象
     * @param <R>       需查询的范型
     * @param <ID>      id的范型
     */
    public  <R, ID> R queryWithMutex(String keyPrefix, ID id, Class<R> type, Function<ID,R> dbQuery, Long nullTime,
                                     TimeUnit nullUnit,Long cacheTime, TimeUnit cacheUnit){

        String CACHE_KEY = keyPrefix + id;
        String json = stringRedisTemplate.opsForValue().get(CACHE_KEY);

        if(StrUtil.isNotBlank(json)){
            return JSONUtil.toBean(json, type);
        }

        if("".equals(json)){
            return null;
        }

        R r = null;
        String lockKey = LOCK_KEY+id;

        try{
            boolean lock = tryLock(lockKey);
            if(!lock){
                Thread.sleep(50);
                return queryWithMutex(keyPrefix, id, type, dbQuery, nullTime, nullUnit, cacheTime, cacheUnit);
            }

            r = dbQuery.apply(id);

            if(r == null){
                stringRedisTemplate.opsForValue().set(CACHE_KEY, "", nullTime, nullUnit);
                return null;
            }
            stringRedisTemplate.opsForValue().set(CACHE_KEY, JSONUtil.toJsonStr(r), cacheTime, cacheUnit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            unLock(lockKey);
        }
        return r;
    }
}

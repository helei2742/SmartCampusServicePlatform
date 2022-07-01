package org.pg7.scsp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.SeckillCourse;
import org.pg7.scsp.entity.SeckillCourseOrder;
import org.pg7.scsp.mapper.SeckillCourseOrderMapper;
import org.pg7.scsp.service.ISeckillCourseOrderService;
import org.pg7.scsp.service.ISeckillCourseService;
import org.pg7.scsp.utils.RedisConstants;
import org.pg7.scsp.utils.RedisIdWorker;
import org.pg7.scsp.utils.SystemConstants;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-28
 */
@Slf4j
@Service
public class SeckillCourseOrderServiceImpl extends ServiceImpl<SeckillCourseOrderMapper, SeckillCourseOrder> implements ISeckillCourseOrderService {

    @Autowired
    private RedisIdWorker redisIdWorker;

    @Autowired
    private ISeckillCourseService seckillCourseService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 类加载完成就开始执行保存选课任务。
     */
    @PostConstruct
    private void init(){

        EXECUTOR_SERVICE.submit(new SeckillCourseHandler());
        EXECUTOR_SERVICE.submit(new CancelSeckillCourseHandler());
    }

/*-----------------------------------------------秒杀选课---------------------------------------------------------*/
   /* @Override
    public Result seckillCourse(Integer courseId, Integer userId) {
        //查询数据库看是否存在
        SeckillCourse seckillCourse = seckillCourseService.query().eq("course_id", courseId).one();
        if(seckillCourse == null){
            return Result.fail("没有该课程");
        }

        //没有开始
        if (seckillCourse.getStartTime().isAfter(LocalDateTime.now())) {
            return Result.fail("选课还没开始");
        }
        //已经结束
        if(seckillCourse.getEndTime().isBefore(LocalDateTime.now())) {
            return Result.fail("选课已经结束");
        }
        //课余量不足
        Integer stock = seckillCourse.getStock();
        if(stock <= 0){
            return Result.fail("课余量不足");
        }

        //没毛病，开选
        String lock = String.valueOf(userId).intern();
        synchronized (lock){
            SeckillCourseOrderServiceImpl proxy = (SeckillCourseOrderServiceImpl) AopContext.currentProxy();
            return proxy.createSeckillOrder(userId,courseId);
        }
    }

    @Transactional
    public Result createSeckillOrder(Integer userId, Integer courseId){
        //一人只能一节课
        Integer count = query()
                        .eq("user_id", userId)
                        .eq("course_id", courseId)
                        .eq("status", SystemConstants.SECKILL_COURSE_ORDER_STATUSE_WAIT).count();

        if(count>0){
            return Result.fail("你已选过该课程");
        }

        //乐观锁，用stock字段做标志，只要更新时课余量大于0则可以更新
        //更改课余量
        boolean update = update().setSql("stock=stock-1")
                .eq("course_id", courseId)
                .gt("stock",0)
                .update();

        if(!update){
            return Result.fail("课余量不足");
        }
        //保存选课记录
        SeckillCourseOrder order = new SeckillCourseOrder();

        long orderId = redisIdWorker.nextId(SystemConstants.REDIS_ID_WORKER_KEY_COURSE);
        order.setId(orderId);
        order.setUserId(userId);
        order.setCourseId(courseId);
        order.setStatus(1);

        save(order);
        return Result.ok(orderId);
    }

*/

    /**
     * 判断一人选一节课，以及课余量是否充足的脚本
     */
    private static final DefaultRedisScript<Long> COURSE_SECKILL_SCRIPT;

    static {
        COURSE_SECKILL_SCRIPT = new DefaultRedisScript<>();
        COURSE_SECKILL_SCRIPT.setLocation(new ClassPathResource("courseseckill.lua"));
        COURSE_SECKILL_SCRIPT.setResultType(Long.class);
    }

    /**
     * 秒杀选课,从redis中判断完用户是否只选了一次以及课余量足够后，直接返回给用户秒杀选课记录id，
     * 真正的将选课记录保存到数据库交给单独的线程来完成，降低响应时间。
     * @param courseId
     * @param userId
     * @return
     */
    @Override
    public Result seckillCourse(Integer courseId, Integer userId) {
        //执行脚本，判断余量是否足够，是否只选一次
        //返回0代表可以选，-1代表redis中没有课程，1代表库存不足，2代表选过
        Long result = stringRedisTemplate.execute(COURSE_SECKILL_SCRIPT,
                Collections.emptyList(),
                courseId.toString(),
                userId.toString());
        int r = result.intValue();

        if(r!=0){
            if(r == -1) return Result.fail("没有该选课课程");
            if(r == 1) return Result.fail("课余量不足");
            if(r == 2) return Result.fail("已选过该课程");
        }

        //可以选课,

        SeckillCourseOrder order = new SeckillCourseOrder();

        long orderId = redisIdWorker.nextId(SystemConstants.REDIS_ID_WORKER_KEY_COURSE_ORDER);
        order.setId(orderId);
        order.setUserId(userId);
        order.setCourseId(courseId);

        //TODO 放入阻塞队列等待写入数据库
        orderTask.add(order);
        //本类的代理对象放到成员变量中
        if(proxy == null){
            proxy = (SeckillCourseOrderServiceImpl) AopContext.currentProxy();
        }


        //返回记录id
        return Result.ok(orderId);
    }

    private SeckillCourseOrderServiceImpl proxy;

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);
    private final static BlockingQueue<SeckillCourseOrder> orderTask = new ArrayBlockingQueue<>(1024*1024);


    @Autowired
    private RedissonClient redissonClient;

    /**
     * 不断的从阻塞队列中取出选课订单保存到数据库
     */
    class SeckillCourseHandler implements Runnable{
        @Override
        public void run() {
            while (true){
                //1、获取队列中的订单信息，
                try {
                    SeckillCourseOrder take = orderTask.take();

                    handlerSeckillCourse(take);
                } catch (Exception e) {
                    log.error("处理选课异常"+e);
                }
            }
        }
    }

    /**
     * 给保存选课记录订单加上互斥锁，防止与修改操作产生冲突，兜底～
     * @param order
     */
    private void handlerSeckillCourse(SeckillCourseOrder order) {
        Integer userId = order.getUserId();
        //使用Redisson的锁
        RLock lock = redissonClient.getLock(RedisConstants.LOCK_SECKILL_COURSE_USER_KEY + userId);

        boolean tryLock = lock.tryLock();
        if(!tryLock){
            //获取锁失败
            log.error("不允许重复选课");
            return;
        }
        //  获取成功
        try{
            proxy.createSeckillOrder(order);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 选课订单保存到数据库
     * @param order
     */
    @Transactional
    public void createSeckillOrder(SeckillCourseOrder order){
        Integer userId = order.getUserId();
        Integer courseId = order.getCourseId();
        //一人只能一节课
        Integer count = query()
                .eq("user_id", userId)
                .eq("course_id", courseId)
                .eq("status", SystemConstants.SECKILL_COURSE_ORDER_STATUSE_WAIT).count();

        if(count>0){
//            return Result.fail("你已选过该课程");
            return;
        }

        //乐观锁，用stock字段做标志，只要更新时课余量大于0则可以更新
        //更改课余量
        boolean update = seckillCourseService.update().setSql("stock=stock-1")
                .eq("course_id", courseId)
                .gt("stock",0)
                .update();

        if(!update){
//            return Result.fail("课余量不足");
            return;
        }
        order.setStatus(1);
        save(order);
    }
    /*-----------------------------------------------秒杀选课---------------------------------------------------------*/


    @Override
    public Result userSeckillCourseId(Integer userId) {
        List<SeckillCourseOrder> list = query()
                .eq("user_id", userId)
                .eq("status", SystemConstants.SECKILL_COURSE_ORDER_STATUSE_WAIT)
                .list();

        return Result.ok(list);
    }

//    ------------------------------------------取消选课 ------------------------------------------
    private static final DefaultRedisScript<Long> CANCEL_SECKILL_COURSE_SCRIPT;
    static {
        CANCEL_SECKILL_COURSE_SCRIPT = new DefaultRedisScript<>();
        CANCEL_SECKILL_COURSE_SCRIPT.setLocation(new ClassPathResource("cancelcourseseckill.lua"));
        CANCEL_SECKILL_COURSE_SCRIPT.setResultType(Long.class);
    }
    @Override
    public Result cancelSeckillCourseId(Integer userId, Integer courseId) {
        Long result = stringRedisTemplate.execute(CANCEL_SECKILL_COURSE_SCRIPT,
                Collections.emptyList(),
                courseId.toString(),
                userId.toString());
        if(result.intValue() == 0){
            return Result.fail("没有选过该课程");
        }
        SeckillCourseOrder order = new SeckillCourseOrder();
        order.setUserId(userId);
        order.setCourseId(courseId);

        //放入取消选课的阻塞队列等待执行。
        cancelOrderTask.add(order);
        //本类的代理对象放到成员变量中
        if(proxy == null){
            proxy = (SeckillCourseOrderServiceImpl) AopContext.currentProxy();
        }

        return Result.ok();
    }
    private static final BlockingQueue<SeckillCourseOrder> cancelOrderTask =
            new ArrayBlockingQueue<>(1024);

    /**
     * 从阻塞队列中取出需要完成取消的选课订单，进行取消
     */
    class CancelSeckillCourseHandler implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    SeckillCourseOrder take = cancelOrderTask.take();
                    handlerCancelSeckillCourse(take);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("取消选课出错"+e);
                }
            }
        }
    }

    /**
     * 取消选课，加上锁，防止与选课发生冲突
     * @param order
     */
    private void handlerCancelSeckillCourse(SeckillCourseOrder order) {

        RLock lock = redissonClient.getLock(RedisConstants.LOCK_SECKILL_COURSE_USER_KEY + order.getUserId());
        boolean f = lock.tryLock();
        if(!f){
            log.error("取消选课失败");
        }
        try {
            proxy.cancelSeckillOrder(order.getUserId(), order.getCourseId());
        }finally {
            lock.unlock();
        }
    }

    /**
     * 取消选课
     * @param userId
     * @param courseId
     */
    @Transactional
    public void cancelSeckillOrder(Integer userId, Integer courseId){
        boolean update = update()
                .set("status", SystemConstants.SECKILL_COURSE_ORDER_STATUSE_CANCEL)
                .set("update_time",LocalDateTime.now())
                .eq("user_id", userId)
                .eq("course_id", courseId)
                .eq("status", SystemConstants.SECKILL_COURSE_ORDER_STATUSE_WAIT)
                .update();

        boolean update1 = seckillCourseService.update().setSql("stock=stock+1")
                .set("update_time",LocalDateTime.now())
                .eq("course_id", courseId)
                .update();
        if(!(update&&update1)){
            log.error("取消选课失败");
        }
    }
    //    ------------------------------------------取消选课 ------------------------------------------
}

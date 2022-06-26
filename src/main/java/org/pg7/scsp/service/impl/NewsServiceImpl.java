package org.pg7.scsp.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.NewsFormDTO;
import org.pg7.scsp.dto.NewsRedisDto;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.News;
import org.pg7.scsp.mapper.NewsMapper;
import org.pg7.scsp.query.NewsQuery;
import org.pg7.scsp.service.INewsService;

import org.pg7.scsp.utils.RedisConstants;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Resource
    private NewsMapper newsMapper;


    @Override
    public Result addNews(NewsFormDTO newsFormDTO) {
        News news = BeanUtil.copyProperties(newsFormDTO, News.class);

        boolean save = save(news);
        if(!save) return Result.fail("保存失败！");
        return Result.ok();
    }

    @Override
    public Result deleteNews(NewsFormDTO newsFormDTO) {

        boolean b = removeById(newsFormDTO.getId());
        if(!b) return Result.fail("删除失败！");

        return Result.ok();
    }

    @Override
    public Result updateNews(NewsFormDTO newsFormDTO) {
        News news = BeanUtil.copyProperties(newsFormDTO, News.class);
        boolean b = updateById(news);
        if(!b) return Result.fail("更新失败!");

        return Result.ok();
    }

    @Override
    public Result pageQuery(NewsQuery newsQuery) {

        Page<News> page = new Page<>(newsQuery.getPage(), newsQuery.getSize());

        Page<News> res = page(page, getQueryWrapper(newsQuery));
        return Result.ok(res);
    }

    /**
     * 获取不同条件的条件构造器
     * @param newsQuery
     * @return
     */
    private Wrapper<News> getQueryWrapper(NewsQuery newsQuery){
        int type = newsQuery.getQueryType();

        QueryWrapper<News> wrapper = new QueryWrapper<>();
        if(type == SystemConstants.CREATE_TIME_DESC){
            wrapper.orderByDesc("create_time");
        }else if(type == SystemConstants.CREATE_TIME_ASC){
            wrapper.orderByAsc("create_time");
        }else if(type == SystemConstants.SEECOUNT_DESC){
            wrapper.orderByDesc("see_count");
        }else if(type == SystemConstants.EQ_COLLAGE_CREATE_TIME_ASC){
            wrapper.eq("collage_Id",
                    newsQuery.getCollageId()).orderByAsc("create_time");
        }else if(type == SystemConstants.EQ_COLLAGE_CREATE_TIME_DESC){
            wrapper.eq("collage_Id",
                    newsQuery.getCollageId()).orderByDesc("create_time");
        }else if(type == SystemConstants.EQ_COLLAGE_SEECOUNT_DESC){
            wrapper.eq("collage_Id",
                    newsQuery.getCollageId()).orderByAsc("see_count");
        }
        return wrapper;
    }


    @Override
    public Result queryById(int id) {
        News news = getById(id);

        if(news == null) return Result.fail("不存在该新闻");
        
        String lock  = new String(RedisConstants.LOCK_NEWS_KEY + id).intern();
//        String idStr = String.valueOf(id);
        synchronized (lock){
//            Object o = stringRedisTemplate.opsForHash().get(RedisConstants.LOCK_NEWS_KEY, idStr);
//            if(o == null){
//                stringRedisTemplate.opsForHash().put(RedisConstants.LOCK_NEWS_KEY, idStr,  String.valueOf(news.getSeeCount()));
//            }else{
//                int seeCount = Integer.parseInt((String) o);
//                stringRedisTemplate.opsForHash().put(RedisConstants.LOCK_NEWS_KEY, idStr,  String.valueOf(seeCount+1));
//            }
            String rankKey = RedisConstants.RANK_NEWS_KEY;
            NewsRedisDto newsRedisDto = new NewsRedisDto();
            newsRedisDto.setId(id);
            newsRedisDto.setTitle(news.getTitle());
            String value = JSONUtil.toJsonStr(newsRedisDto);

            Double score = stringRedisTemplate.opsForZSet().score(rankKey, value);
            if(score == null){
                stringRedisTemplate.opsForZSet().add(rankKey, value, news.getSeeCount());
            }else {
                stringRedisTemplate.opsForZSet().add(rankKey, value, score + 1);
            }
        }
        return Result.ok(news);
    }

    @Override
    public Result queryHotX(int x) {
        String rankKey = RedisConstants.RANK_NEWS_KEY;
        Set<String> ranges = stringRedisTemplate.opsForZSet().reverseRangeByScore(rankKey, 0, Integer.MAX_VALUE);

        int size = ranges.size();
        List<NewsRedisDto> res = new ArrayList<>();

        for (String range : ranges) {
            if(x == 0) break;
            NewsRedisDto nrd = JSONUtil.toBean(range, NewsRedisDto.class);
            Double score = stringRedisTemplate.opsForZSet().score(rankKey, range);
            if(score == null) continue;
            nrd.setSeeCount(score.intValue());
            res.add(nrd);
            x--;
        }
        if(x > 0){
            List<NewsRedisDto> db = newsMapper.queryTitleAndSeeCount(size, x);
            res.addAll(db);
        }
        return Result.ok(res);
    }


    /**
     * 将redis中存储的新闻浏览次数更新到数据库。
     * @return
     */
    public int updateRedisSeeCountToDB(){
/*        Set<Object> keys = stringRedisTemplate.opsForHash().keys(RedisConstants.LOCK_NEWS_KEY);
        int res = 0;
        for (Object key : keys) {
            String idStr = (String) key;
            Object o = stringRedisTemplate.opsForHash().get(RedisConstants.LOCK_NEWS_KEY, idStr);
            int count = Integer.parseInt((String) o);
            if(count <=0 ) continue;

            News n = new News();
            n.setId(Integer.valueOf(idStr));
            n.setSeeCount(count);
            updateById(n);
            res++;
        }
        return res;*/
        String rankKey = RedisConstants.RANK_NEWS_KEY;

        Set<String> ranges = stringRedisTemplate.opsForZSet().range(rankKey, 0, Integer.MAX_VALUE);
        if(ranges == null) return 0;
        int res = 0;
        for (String range : ranges) {
            NewsRedisDto newsRedisDto = JSONUtil.toBean(range, NewsRedisDto.class);
            Double seeCount = stringRedisTemplate.opsForZSet().score(rankKey, range);

            if(seeCount == null) continue;
            News n = new News();
            n.setId(newsRedisDto.getId());
            n.setSeeCount(seeCount.intValue());
            updateById(n);
            res++;
        }
        return res;
    }
}

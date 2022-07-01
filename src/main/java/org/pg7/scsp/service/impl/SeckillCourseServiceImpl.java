package org.pg7.scsp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.SeckillCourse;
import org.pg7.scsp.entity.SeckillCourseOrder;
import org.pg7.scsp.mapper.SeckillCourseMapper;
import org.pg7.scsp.service.ISeckillCourseOrderService;
import org.pg7.scsp.service.ISeckillCourseService;
import org.pg7.scsp.utils.RedisConstants;
import org.pg7.scsp.utils.RedisIdWorker;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-28
 */
@Service
public class SeckillCourseServiceImpl extends ServiceImpl<SeckillCourseMapper, SeckillCourse> implements ISeckillCourseService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result querySelectInfoById(Integer courseId) {
        SeckillCourse seckillCourse = query().eq("course_id", courseId).one();
        if(seckillCourse == null){
            return Result.fail("该课程不存在!");
        }

        return Result.ok(seckillCourse);
    }

    @Override
    public Result addSeckillCourseStockToRedis() {
        List<SeckillCourse> seckillCourses = query().select("course_id", "stock").list();
        for (SeckillCourse seckillCours : seckillCourses) {
            stringRedisTemplate.opsForValue().set(
                    RedisConstants.SECKILL_COURSE_STOCK_KEY + seckillCours.getCourseId()
                    , seckillCours.getStock().toString());
        }

        return Result.ok(seckillCourses.size());
    }

}

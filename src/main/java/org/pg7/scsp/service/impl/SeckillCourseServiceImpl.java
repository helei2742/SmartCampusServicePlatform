package org.pg7.scsp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.SeckillCourse;
import org.pg7.scsp.entity.SeckillCourseOrder;
import org.pg7.scsp.mapper.SeckillCourseMapper;
import org.pg7.scsp.service.ISeckillCourseOrderService;
import org.pg7.scsp.service.ISeckillCourseService;
import org.pg7.scsp.utils.RedisIdWorker;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    private RedisIdWorker redisIdWorker;

    @Autowired
    private ISeckillCourseOrderService seckillCourseOrderService;

    @Override
    public Result querySelectInfoById(Integer courseId) {
        SeckillCourse seckillCourse = query().eq("course_id", courseId).one();
        if(seckillCourse == null){
            return Result.fail("该课程不存在!");
        }

        return Result.ok(seckillCourse);
    }

    @Override
    public Result seckillCourse(Integer courseId, Integer userId) {
        //查询数据库看是否存在
        SeckillCourse seckillCourse = query().eq("course_id", courseId).one();
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
            SeckillCourseServiceImpl proxy = (SeckillCourseServiceImpl) AopContext.currentProxy();
            return proxy.createSeckillOrder(userId,courseId);
        }
    }

    @Transactional
    public Result createSeckillOrder(Integer userId, Integer courseId){
        //一人只能一节课
        Integer count = seckillCourseOrderService.query()
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

        seckillCourseOrderService.save(order);
        return Result.ok(orderId);
    }

}

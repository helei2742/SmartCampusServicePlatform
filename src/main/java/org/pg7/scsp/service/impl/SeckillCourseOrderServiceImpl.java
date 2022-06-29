package org.pg7.scsp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.SeckillCourseOrder;
import org.pg7.scsp.mapper.SeckillCourseOrderMapper;
import org.pg7.scsp.service.ISeckillCourseOrderService;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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
public class SeckillCourseOrderServiceImpl extends ServiceImpl<SeckillCourseOrderMapper, SeckillCourseOrder> implements ISeckillCourseOrderService {

    @Override
    public Result userSeckillCourseId(Integer userId) {
        List<SeckillCourseOrder> list = query()
                .eq("user_id", userId)
                .eq("status", SystemConstants.SECKILL_COURSE_ORDER_STATUSE_WAIT)
                .list();

        return Result.ok(list);
    }

    @Override
    @Transactional
    public Result cancelSeckillCourseId(Integer userId, Integer courseId) {
        boolean update = update()
                        .set("status", SystemConstants.SECKILL_COURSE_ORDER_STATUSE_CANCEL)
                        .eq("user_id", userId)
                        .eq("course_id", courseId)
                        .eq("status", SystemConstants.SECKILL_COURSE_ORDER_STATUSE_WAIT)
                        .update();
        if(!update){
            return Result.fail("取消选课失败!");
        }
        return Result.ok();
    }
}

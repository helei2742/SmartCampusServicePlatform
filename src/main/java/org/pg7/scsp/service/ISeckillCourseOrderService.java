package org.pg7.scsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.SeckillCourseOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-28
 */
public interface ISeckillCourseOrderService extends IService<SeckillCourseOrder> {
    /**
     * 选课，
     * @param courseId
     * @param userId
     * @return
     */
    Result seckillCourse(Integer courseId, Integer userId);

    /**
     * 查询用户当前选课阶段抢到的课程id
     * @param userId
     * @return
     */
    Result userSeckillCourseId(Integer userId);
    /**
     * 查询用户当前抢到的课程的时间
     * @param userId
     * @return
     */
    Result queryUserCourseTime(Integer userId);

    /**
     * 取消用户当前选课阶段选的课
     * @param userId
     * @param courseId
     * @return
     */
    Result cancelSeckillCourseId(Integer userId, Integer courseId);
}

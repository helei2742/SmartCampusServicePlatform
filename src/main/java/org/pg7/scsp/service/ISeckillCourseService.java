package org.pg7.scsp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.SeckillCourse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-28
 */
public interface ISeckillCourseService extends IService<SeckillCourse> {

    /**
     * 根据id查选课信息
     * @param courseId
     * @return
     */
    Result querySelectInfoById(Integer courseId);

    /**
     * 选课，
     * @param courseId
     * @param userId
     * @return
     */
    Result seckillCourse(Integer courseId, Integer userId);


}

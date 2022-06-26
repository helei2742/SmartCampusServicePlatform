package org.pg7.scsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.Course;
import org.pg7.scsp.query.CourseQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-26
 */
public interface ICourseService extends IService<Course> {

    /**
     * 根据条件查询课程
     *      当queryType为 9时，查询全部课程的全部课程信息
     *      当queryType为 10时。查询老师对应id当全部课程信息
     * @param courseQuery
     * @return
     */
    Result queryCourse(CourseQuery courseQuery);
}

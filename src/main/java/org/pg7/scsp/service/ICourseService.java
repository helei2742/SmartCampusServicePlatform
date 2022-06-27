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
     * 根据条件分页查询课程
     *      基本的条件查询参数
     *          page， size
     *     queryType需为9
     *      其他参数
     *     private Integer teacherId;
     *
     *     private String semester;
     *
     *     private Integer major;
     *         需要条件时带入即可
     *      不含以上条件则分页查询所有
     * @param courseQuery
     * @return
     */
    Result conditionPageQueryCourse(CourseQuery courseQuery);


    /**
     * 查询课程总数
     * @return
     */
    Result queryTotalCountCourse();

    /**
     * 查询当前学期的课程，添加有缓存，选课阶段使用。
     * @return
     */
    Result currentSemesterCourse();

    /**
     * 带缓存的查找当前学期的该课程名的课程
     * @param courseName
     * @return
     */
    Result queryCSTCourseByName(String courseName);

    /**
     * 带有缓存的查询当前学期的课程的collageName学院的
     * @param collageName
     * @return
     */
    Result queryCSTCourseByCollageName(String collageName);
}

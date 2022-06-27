package org.pg7.scsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.pg7.scsp.dto.CourseDTO;
import org.pg7.scsp.entity.Course;
import org.pg7.scsp.query.CourseQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helei
 * @since 2022-06-26
 */
public interface CourseMapper extends BaseMapper<Course> {


    /**
     * 查询所有课程的所有信息
     * @return
     */
    List<CourseDTO> queryAllCourseAllInfo();


    /**
     * 查询老师教的课程信息
     * @return
     */
    List<CourseDTO> queryCourseAllInfoByTeacherId(Integer teacherId);

    /**
     * 查询指定学期的课程信息
     * @param semester
     * @return
     */
    List<CourseDTO> querySemesterAllCourseAllInfo(String semester);

    /**
     * 设置课程到时间
     * @param courseId
     * @param courseTimeId
     * @return
     */
    Integer setCourseTime(@Param("courseId") Integer courseId, @Param("courseTimeId") Integer courseTimeId);

    /**
     * 设置课程老师
     * @param courseId
     * @param teacherId
     * @return
     */
    Integer setCourseTeacher(@Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId);

    /**
     * 查询总课程数量
     * @return
     */
    int queryTotalCountCourse();

    /**
     * 条件查询所有课程
     * @return
     */
    List<CourseDTO> conditionQueryAllCourseAllInfo(CourseQuery query);

    /**
     * 根据学期和课程名查找课程的全部信息
     * @param semester
     * @param courseName
     * @return
     */
    List<CourseDTO> queryBySemesterAndName(@Param("semester") String semester,@Param("courseName") String courseName);

    /**
     * 根据学期和学院名查找课程的全部信息
     * @param semester
     * @param collageName
     * @return
     */
    List<CourseDTO> queryBySemesterAndCollageName(String semester, String collageName);
}

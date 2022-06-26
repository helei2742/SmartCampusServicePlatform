package org.pg7.scsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.pg7.scsp.dto.CourseDTO;
import org.pg7.scsp.entity.Course;

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

}

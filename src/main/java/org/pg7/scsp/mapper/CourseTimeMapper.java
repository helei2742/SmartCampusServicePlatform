package org.pg7.scsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.pg7.scsp.entity.CourseTime;

import java.util.List;

public interface CourseTimeMapper extends BaseMapper<CourseTime> {
    /**
     * 查询课程对应的课程时间id
     * @param courseIds
     * @return
     */
    List<Integer> queryCourseCourseTimeIdBatch(List<Integer> courseIds);
}

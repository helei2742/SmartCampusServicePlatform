package org.pg7.scsp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.CourseDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.Course;
import org.pg7.scsp.mapper.CourseMapper;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.service.ICourseService;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-26
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Override
    public Result queryCourse(CourseQuery courseQuery) {
        Integer queryType = courseQuery.getQueryType();
        List<CourseDTO> all = null;
        if(queryType == SystemConstants.QUERY_ALL_COURSE_ALLINFO){
             all = baseMapper.queryAllCourseAllInfo();
        }else if(queryType == SystemConstants.QUERY_COURSE_ALLINFO_BY_TEACHERID){
             all = baseMapper.queryCourseAllInfoByTeacherId(courseQuery.getTeacherId());

        }else {
            return Result.fail("查询类型错误");
        }
        return Result.ok(all);
    }
}

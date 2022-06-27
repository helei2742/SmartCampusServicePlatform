package org.pg7.scsp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.CourseDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.Course;
import org.pg7.scsp.mapper.CourseMapper;
import org.pg7.scsp.mapper.MajorMapper;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.service.ICourseService;
import org.pg7.scsp.dto.PageBean;
import org.pg7.scsp.utils.RedisConstants;
import org.pg7.scsp.utils.SemesterUtil;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private MajorMapper majorMapper;

    @Override
    public Result conditionPageQueryCourse(CourseQuery courseQuery) {
        if(courseQuery.getQueryType() != SystemConstants.CONDITION_QUERY_PAGE_COURSE_ALLINFO){
            return Result.fail("查询类型错误");
        }

        List<CourseDTO> all = null;

        String semester = courseQuery.getSemester();

        if(semester!=null && !SemesterUtil.isAllowedSemester(semester)){
            return Result.fail("学期格式错误,需为yyyy年春(秋)季学期");
        }

        all = baseMapper.conditionQueryAllCourseAllInfo(courseQuery);

        PageBean<CourseDTO> page = new PageBean<>(courseQuery.getPage(), courseQuery.getSize(),all.size());
        page.addData(all);

        return Result.ok(page);
    }

    @Override
    public Result queryTotalCountCourse() {
        int total = baseMapper.queryTotalCountCourse();
        return Result.ok(total);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


/*----------------------------------------带缓存的方法--------------------------------------------------------------------------------------------------------*/
    @Override
    public Result currentSemesterCourse() {
        //获取当前学期
        String semester = SemesterUtil.getCurrentSemester();
        String semesterKey = RedisConstants.CACHE_COURSE_SEMESTER_KEY+semester;

        //查询缓存
        //有，返回
        String json = stringRedisTemplate.opsForValue().get(semesterKey);
        if(StrUtil.isNotBlank(json)){
            List<CourseDTO> courseDTOS = JSONUtil.toList(json, CourseDTO.class);
            return Result.ok(courseDTOS);
        }

        //没有，查询数据库
        List<CourseDTO> courseDTOS = baseMapper.querySemesterAllCourseAllInfo(semester);

        //更新缓存
        stringRedisTemplate.opsForValue().set(semesterKey, JSONUtil.toJsonStr(courseDTOS),
                RedisConstants.CACHE_COURSE_SEMESTER_TTL, TimeUnit.DAYS);
        //返回
        return Result.ok(courseDTOS);
    }

    @Override
    public Result queryCSTCourseByName(String courseName) {
        String semester = SemesterUtil.getCurrentSemester();
        String redisKey = RedisConstants.CACHE_COURSE_NAME_KEY + semester +":courseName:"+ courseName;

        String json = stringRedisTemplate.opsForValue().get(redisKey);
        if(StrUtil.isNotBlank(json)){
            List<CourseDTO> courseDTOs = JSONUtil.toList(json, CourseDTO.class);
            return Result.ok(courseDTOs);
        }

        List<CourseDTO> courseDTOs = baseMapper.queryBySemesterAndName(semester, courseName);

        stringRedisTemplate.opsForValue().set(redisKey, JSONUtil.toJsonStr(courseDTOs),
                RedisConstants.CACHE_COURSE_NAME_TTL, TimeUnit.MINUTES);

        return Result.ok(courseDTOs);
    }

    @Override
    public Result queryCSTCourseByCollageName(String collageName) {
        String semester = SemesterUtil.getCurrentSemester();
        String redisKey = RedisConstants.CACHE_COURSE_NAME_KEY + semester +":collageName:"+collageName;

        String json = stringRedisTemplate.opsForValue().get(redisKey);
        if(StrUtil.isNotBlank(json)){
            List<CourseDTO> courseDTOs = JSONUtil.toList(json, CourseDTO.class);
            return Result.ok(courseDTOs);
        }

        List<CourseDTO> courseDTOs = baseMapper.queryBySemesterAndCollageName(semester, collageName);
        stringRedisTemplate.opsForValue().set(redisKey, JSONUtil.toJsonStr(courseDTOs),
                RedisConstants.CACHE_COURSE_NAME_TTL, TimeUnit.MINUTES);

        return Result.ok(courseDTOs);
    }
/*------------------------------------------------------------------------------------------------------------------------------------------------*/
}

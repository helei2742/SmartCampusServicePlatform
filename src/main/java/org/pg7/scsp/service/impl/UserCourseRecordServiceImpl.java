package org.pg7.scsp.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.UserCourseRecord;
import org.pg7.scsp.mapper.UserCourseRecordMapper;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.service.IUserCourseRecordService;
import org.pg7.scsp.utils.SemesterUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-26
 */
@Service
public class UserCourseRecordServiceImpl extends ServiceImpl<UserCourseRecordMapper, UserCourseRecord> implements IUserCourseRecordService {


    @Override
    public Result conditionPageQueryCourseRecord(CourseQuery courseQuery) {

        // TODO 权限验证
        if(!isAllowedUse(courseQuery.getAuth())){
            return Result.fail("权限不足！");
        }


        QueryWrapper<UserCourseRecord> wrapper = this.getWrapper(courseQuery);
        Page<UserCourseRecord> page = new Page<>(courseQuery.getPage(), courseQuery.getSize());
        baseMapper.selectPage(page, wrapper);

        return Result.ok(page);
    }
    /**
     * 需包含分页参数
     *      page（默认1）， size（默认20）
     * 构造条件，可选择的条件有，为空则表示不需要此条件
     *      userId       用户id
     *      semester     学期
     *      courseName    课程名
     *      isPass       是否通过
     *      count        选修次数
     *      isFirst      是否只修过一次
     * @param courseQuery
     * @return
     */
    private QueryWrapper<UserCourseRecord> getWrapper(CourseQuery courseQuery){
        QueryWrapper<UserCourseRecord> wrapper = new QueryWrapper<>();
        Integer userId = courseQuery.getUserId();
        String semester = courseQuery.getSemester();
        String courseName = courseQuery.getCourseName();
        Boolean isPass = courseQuery.getIsPass();
        Integer count = courseQuery.getCount();
        Boolean isFirst = courseQuery.getIsFirst();
        if(userId != null){
            wrapper.eq("user_id", userId);
        }
        if(StrUtil.isNotBlank(semester)){
            if (!SemesterUtil.isAllowedSemester(semester)) {
                Result.fail("非法的学期格式");
            }
            wrapper.eq("semester", semester);
        }
        if(StrUtil.isNotBlank(courseName)){
            wrapper.eq("course_name", courseName);
        }
        if(isPass != null){
            if(isPass) {
                wrapper.ge("score", 60);
            }else {
                wrapper.lt("score", 60);
            }
        }
        if(isFirst != null){
            if(isFirst){
                wrapper.eq("count", 1);
            }else {
                wrapper.ge("count", 1);
            }
        }

        if(count != null){
            wrapper.eq("count", count);
        }
        return wrapper;
    }
    @Override
    public Result queryUserCourseRecord(CourseQuery courseQuery) {
        // TODO 权限验证


        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }
        List<UserCourseRecord> list = query().eq("user_id", userId).list();
        return Result.ok(list);
    }

    @Override
    public Result queryUserPassCourse(CourseQuery courseQuery) {
        // TODO 权限验证

        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }

        List<UserCourseRecord> list = query().eq("user_id", userId).ge("score", 60).list();
        return Result.ok(list);
    }

    @Override
    public Result queryUserUnPassCourse(CourseQuery courseQuery) {
        // TODO 权限验证

        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }
        List<UserCourseRecord> list = query().eq("user_id", userId).lt("score", 60).list();
        return Result.ok(list);
    }

    @Override
    public Result queryUserNeedRetakeCourse(CourseQuery courseQuery) {
        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }

        List<UserCourseRecord> unPass = query().eq("user_id", userId).lt("score", 60).list();
        List<UserCourseRecord> pass = query().eq("user_id", userId).ge("score", 60).list();
        Set<String> set = new HashSet<>();
        unPass.forEach(u->set.add(u.getCourseName()));
        pass.forEach(u->set.remove(u.getCourseName()));
        return Result.ok(set);
    }

    @Override
    public Result queryUserCredit(CourseQuery courseQuery) {
        // TODO 权限验证

        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }
        List<Map<String, Object>> pass = baseMapper.queryUserCredit(userId);
        List<Map<String, Object>> unPass = baseMapper.queryUserUnPassCredit(userId);
        Map<String, Object> res = new HashMap<>();
        res.put("pass", pass);
        res.put("unPass", unPass);
        return Result.ok(res);
    }

    @Override
    public Result queryUserTotalCredit(CourseQuery courseQuery) {
        // TODO 权限验证

        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }
        List<Map<String, Object>> list = baseMapper.queryUserCredit(userId);
        final float[] total = {0};
        list.forEach(m->{total[0] = (total[0] +  (float)m.get("credit"));});
        return Result.ok(total[0]);
    }

    @Override
    public Result queryUserTotalUnPassCredit(CourseQuery courseQuery) {
        // TODO 权限验证

        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }

        List<Map<String, Object>> list = baseMapper.queryUserUnPassCredit(userId);
        final float[] total = {0};
        list.forEach(m->{total[0] = (total[0] +  (float)m.get("credit"));});
        return Result.ok(total[0]);
    }
}

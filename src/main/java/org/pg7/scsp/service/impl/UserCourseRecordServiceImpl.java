package org.pg7.scsp.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.UserCourseRecord;
import org.pg7.scsp.mapper.UserCourseRecordMapper;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.service.IUserCourseRecordService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result queryUserCourseRecord(CourseQuery courseQuery) {
        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }
        List<UserCourseRecord> list = query().eq("user_id", userId).list();
        return Result.ok(list);
    }

    @Override
    public Result queryUserPassCourse(CourseQuery courseQuery) {
        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }

        List<UserCourseRecord> list = query().eq("user_id", userId).ge("score", 60).list();
        return Result.ok(list);
    }

    @Override
    public Result queryUserUnPassCourse(CourseQuery courseQuery) {
        Integer userId = courseQuery.getUserId();
        if(userId == null){
            return Result.fail("用户id错误！");
        }
        List<UserCourseRecord> list = query().eq("user_id", userId).lt("score", 60).list();
        return Result.ok(list);
    }

    @Override
    public Result queryUserCredit(CourseQuery courseQuery) {
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

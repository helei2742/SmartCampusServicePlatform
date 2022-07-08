package org.pg7.scsp.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.pg7.scsp.entity.UserCourseRecord;
import org.pg7.scsp.query.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helei
 * @since 2022-06-26
 */
public interface UserCourseRecordMapper extends BaseMapper<UserCourseRecord> {


    /**
     * 查询用户学分（重复课程的学分不算）
     * @param query
     * @return
     */
    List<Map<String,Object>> queryUserCredit(CourseQuery query);

    /**
     * 查询用户未通过的学分（重复课程的学分不算）
     * @param query
     * @return
     */
    List<Map<String, Object>> queryUserUnPassCredit(CourseQuery query);

    /**
     * 设置用户需重修课程
     * @param userId
     * @param courseId
     * @return
     */
    Integer setRetakeCourse(@Param("userId") Integer userId, @Param("courseId")Integer courseId);
}

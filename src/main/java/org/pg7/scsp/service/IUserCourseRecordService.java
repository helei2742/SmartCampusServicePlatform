package org.pg7.scsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.UserCourseRecord;
import org.pg7.scsp.query.CourseQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-26
 */
public interface IUserCourseRecordService extends IService<UserCourseRecord> {

    /**
     * 查询用户的选课记录
     * @param courseQuery
     * @return
     */
    Result queryUserCourseRecord(CourseQuery courseQuery);

    /**
     * 查询用户通过的课程
     * @param courseQuery
     * @return
     */
    Result queryUserPassCourse(CourseQuery courseQuery);

    /**
     * 查询用户未通过的课程
     * @param courseQuery
     * @return
     */
    Result queryUserUnPassCourse(CourseQuery courseQuery);

    /**
     * 查询用户学分情况（已修学分）
     * @param courseQuery
     * @return
     */
    Result queryUserCredit(CourseQuery courseQuery);

    /**
     * 查询用户通过总学分
     * @param courseQuery
     * @return
     */
    Result queryUserTotalCredit(CourseQuery courseQuery);

    /**
     * 查询用户未通过的总学分
     * @param courseQuery
     * @return
     */
    Result queryUserTotalUnPassCredit(CourseQuery courseQuery);
}

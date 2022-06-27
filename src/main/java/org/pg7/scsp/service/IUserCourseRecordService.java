package org.pg7.scsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.dto.UserDTO;
import org.pg7.scsp.entity.UserCourseRecord;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.utils.UserHolder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-26
 */
public interface IUserCourseRecordService extends IService<UserCourseRecord> {

    default boolean isAllowedUse(String auth){

        //TODO 其它权限

        return true;
    }

    /**
     * 条件分页查询课程记录
     *      * 需包含分页参数
     *      *      page（默认1）， size（默认20）
     *      * 构造条件，可选择的条件有，为空则表示不需要此条件
     *      *      userId       用户id
     *      *      semester     学期
     *      *      courseName    课程名
     *      *      isPass       是否通过
     *      *      count        选修次数
     *      *      isFirst      是否只修过一次
     * @param courseQuery
     * @return
     */
    Result conditionPageQueryCourseRecord(CourseQuery courseQuery);

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

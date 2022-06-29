package org.pg7.scsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.UserTestRecord;
import org.pg7.scsp.query.UserTestQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-29
 */
public interface IUserTestRecordService extends IService<UserTestRecord> {


    /**
     * 条件分页查询用户的考试信息
     *  需传入分页参数，
     *      page 默认1, size默认20
     *  以及选传的条件参数
     *      userId
     *      courseId
     *      testType  1:期末考试 2:期中考试 3:补考
     *      status 1:未开考, 2:未参加, 3:已参加, 4:考试已取消
     * @param query
     * @return
     */
    Result conditionPageQueryUserTest(UserTestQuery query);
}

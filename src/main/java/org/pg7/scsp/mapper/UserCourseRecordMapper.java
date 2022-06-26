package org.pg7.scsp.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.pg7.scsp.entity.UserCourseRecord;

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
     * @param userId
     * @return
     */
    List<Map<String,Object>> queryUserCredit(Integer userId);

    /**
     * 查询用户未通过的学分（重复课程的学分不算）
     * @param userId
     * @return
     */
    List<Map<String, Object>> queryUserUnPassCredit(Integer userId);
}

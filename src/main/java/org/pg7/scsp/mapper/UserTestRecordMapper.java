package org.pg7.scsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.pg7.scsp.dto.UserTestDTO;
import org.pg7.scsp.entity.UserTestRecord;
import org.pg7.scsp.query.UserTestQuery;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helei
 * @since 2022-06-29
 */
public interface UserTestRecordMapper extends BaseMapper<UserTestRecord> {

    /**
     * 条件查询考试信息
     * @param query
     * @return
     */
    List<UserTestDTO> conditionQueryUserTest(UserTestQuery query);
}

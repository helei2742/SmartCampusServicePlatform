package org.pg7.scsp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.PageBean;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.dto.UserTestDTO;
import org.pg7.scsp.entity.UserTestRecord;
import org.pg7.scsp.mapper.UserTestRecordMapper;
import org.pg7.scsp.query.UserTestQuery;
import org.pg7.scsp.service.IUserTestRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-29
 */
@Service
public class UserTestRecordServiceImpl extends ServiceImpl<UserTestRecordMapper, UserTestRecord> implements IUserTestRecordService {

    @Override
    public Result conditionPageQueryUserTest(UserTestQuery query) {


        List<UserTestDTO> all = baseMapper.conditionQueryUserTest(query);

        PageBean<UserTestDTO> page = new PageBean<>(query.getPage(), query.getSize(),all.size());

        page.addData(all);
        return Result.ok(page);
    }

}

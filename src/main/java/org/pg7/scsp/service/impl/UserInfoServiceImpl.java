package org.pg7.scsp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.entity.UserInfo;
import org.pg7.scsp.mapper.UserInfoMapper;
import org.pg7.scsp.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}

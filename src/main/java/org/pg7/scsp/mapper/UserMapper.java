package org.pg7.scsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.pg7.scsp.dto.UserInfoDTO;
import org.pg7.scsp.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户id查询用户信息，包括基本信息和学院信息
     * @param userId
     * @return
     */
    UserInfoDTO queryUserInfo(int userId);

    /**
     * 根据学工号查询用户的所以信息，包括基本信息和学院信息
     * @param idNumber
     * @return
     */
    UserInfoDTO queryUserInfoByIdNumber(String idNumber);
}

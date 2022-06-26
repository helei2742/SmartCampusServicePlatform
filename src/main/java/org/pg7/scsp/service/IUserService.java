package org.pg7.scsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.LoginFormDTO;
import org.pg7.scsp.dto.RegisterFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.User;
import org.pg7.scsp.query.UserQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
public interface IUserService extends IService<User> {
    /**
     * 获取验证放入session中并返回
     */
    Result getCaptchaImg(String idNumber, HttpServletResponse response);

    /**
     * 登录
     * @param loginFormDTO
     * @param request
     * @return
     */
    Result login(LoginFormDTO loginFormDTO, HttpServletRequest request);

    /**
     * 注册
     * @param registerFormDTO
     * @return
     */
    Result register(RegisterFormDTO registerFormDTO);

    /**
     * 查询用户所有基本信息
     * @param userQuery
     * @return
     */
    Result queryUserInfo(UserQuery userQuery);
}

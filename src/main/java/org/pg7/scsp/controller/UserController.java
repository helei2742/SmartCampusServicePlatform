package org.pg7.scsp.controller;



import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import org.pg7.scsp.dto.LoginFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.dto.UserFormDto;
import org.pg7.scsp.entity.User;
import org.pg7.scsp.entity.UserInfo;
import org.pg7.scsp.query.UserQuery;
import org.pg7.scsp.service.IUserService;
import org.pg7.scsp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService = new UserServiceImpl();

    /**
     * 登录
     * @param loginForm
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody LoginFormDTO loginForm, HttpServletRequest request) {
        //TODO 登录
        return userService.login(loginForm, request);
    }

    @PostMapping("/validate")
    @ResponseBody
    public Result validate(HttpServletRequest request){
        //TODO 免登录认证
        return userService.validate(request.getHeader("Authorization"));
    }
    /**
     * 产生验证码
     * @return
     */
    @PostMapping("/getCaptchaImg")
    @ResponseBody
    public Result getCaptchaImg(@RequestParam("idNumber")String idNumber,HttpServletRequest request, HttpServletResponse response) {
        //TODO 实现生成并返回随机图片验证码，并将验证码存入redis中
        return userService.getCaptchaImg(idNumber, response);
    }


    @PostMapping("/register/{role}")
    @ResponseBody
    public Result register(@RequestBody Map<String, Object> map,@PathVariable("role")Integer roleId){
        //TODO 用户注册
        User user = BeanUtil.mapToBean(map, User.class, false);
        UserInfo userInfo = BeanUtil.mapToBean(map, UserInfo.class, false);
        return userService.register(user, userInfo, roleId);
    }
    @PostMapping("/logout/{token}")
    @ResponseBody
    public void logout(@PathVariable("token")String token){
        userService.logout(token);
    }

    @PostMapping("/userInfo")
    @ResponseBody
    public Result queryUserInfo(@RequestBody UserQuery userQuery){
        //TODO 根据条件查询
        return userService.queryUserInfo(userQuery);
    }


    @PostMapping("/alterUserInfo")
    @ResponseBody
    public Result alterUserInfo(@RequestBody UserFormDto userFormDto){
        //TODO 修改用户信息

        return userService.alterUserInfo(userFormDto);
    }


    @PostMapping("/queryUserSemester")
    @ResponseBody
    public Result queryUserSemester(@RequestBody UserQuery userQuery){
        //TODO 查询用户的学期
        return userService.queryUserSemester(userQuery.getUserId());
    }

}


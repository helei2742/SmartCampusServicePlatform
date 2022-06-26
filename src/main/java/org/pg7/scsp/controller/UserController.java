package org.pg7.scsp.controller;



import org.pg7.scsp.dto.LoginFormDTO;
import org.pg7.scsp.dto.RegisterFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.query.UserQuery;
import org.pg7.scsp.service.IUserService;
import org.pg7.scsp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody RegisterFormDTO registerFormDTO){
        //TODO 用户注册
        return userService.register(registerFormDTO);
    }

    @PostMapping("/userInfo")
    @ResponseBody
    public Result queryUserInfo(@RequestBody UserQuery userQuery){
        //TODO 根据条件查询
        return userService.queryUserInfo(userQuery);
    }


}


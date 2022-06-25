package org.pg7.scsp.interceptor;


import org.pg7.scsp.dto.UserDTO;
import org.pg7.scsp.utils.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取前面拦截器放入ThreadLocal的token
        UserDTO userDTO = UserHolder.getUser();

        if(userDTO == null){
            response.setStatus(401);
            return false;
        }

        //通过
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}

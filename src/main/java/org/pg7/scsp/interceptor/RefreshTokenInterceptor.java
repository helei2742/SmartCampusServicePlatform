package org.pg7.scsp.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.pg7.scsp.dto.UserDTO;
import org.pg7.scsp.utils.RedisConstants;
import org.pg7.scsp.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RefreshTokenInterceptor implements HandlerInterceptor {

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头的token
        String token = request.getHeader("authorization");
        System.out.println(token);
        //redis获取用户
        String json = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_USER_KEY + token);

        if(StrUtil.isNotBlank(json)){
            UserDTO userDTO = JSONUtil.toBean(json, UserDTO.class);

            //更新redis
            stringRedisTemplate.expire(RedisConstants.LOGIN_USER_KEY + token, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);

            //保存到ThreadLocal
            UserHolder.saveUser(userDTO);
        }

        //通过
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}

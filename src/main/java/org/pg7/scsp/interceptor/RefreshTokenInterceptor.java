package org.pg7.scsp.interceptor;

import cn.hutool.core.bean.BeanUtil;
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

        //redis获取用户
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(RedisConstants.LOGIN_USER_KEY + token);

        if(!map.isEmpty()){
            UserDTO userDTO = BeanUtil.mapToBean(map, UserDTO.class,false);

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

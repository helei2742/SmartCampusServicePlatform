package org.pg7.scsp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.pg7.scsp.dto.LoginFormDTO;
import org.pg7.scsp.dto.RegisterFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.dto.UserDTO;
import org.pg7.scsp.entity.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.mapper.UserMapper;
import org.pg7.scsp.service.IUserService;
import org.pg7.scsp.utils.RedisConstants;

import org.pg7.scsp.utils.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result getCaptchaImg(String idNumber, HttpServletResponse response) {
        User user = query().eq("id_number", idNumber).one();

        if(user == null){
            response.setStatus(401);
            return Result.fail("该用户不存在");
        }

        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            ValidateCodeUtil.getRandCodeImageAndSaveRedis(RedisConstants.LOGIN_CHECKCODE_KEY,
                    idNumber, response, stringRedisTemplate, RedisConstants.LOGIN_CHECKCODE_TTL, TimeUnit.MINUTES);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginFormDTO, HttpServletRequest request) {
        String checkCode = loginFormDTO.getCode();
        String idNumber = loginFormDTO.getIdNumber();
        String password = loginFormDTO.getPassword();

        if(StrUtil.isBlank(checkCode) || checkCode.length() > 4){
            return Result.fail("验证码格式错误！");
        }
//        HttpSession session = request.getSession();
//        String sessionCode = (String) session.getAttribute(SystemConstants.LOGINCHECKCODE);
        String redisCode = stringRedisTemplate.opsForValue().get(RedisConstants.LOGIN_CHECKCODE_KEY + idNumber);

        if(!checkCode.equals(redisCode)){
            return Result.fail("验证码错误！");
        }

        if(StrUtil.isBlank(idNumber) || StrUtil.isBlank(password)){
            return Result.fail("学号或密码错误！");
        }

        User user = query().eq("id_number", idNumber).eq("password", password).one();

        if(user == null){
            return Result.fail("学号或密码错误！");
        }

        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        System.out.println(userDTO);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO);

        String token = UUID.randomUUID().toString(true);
        log.debug("用户:"+idNumber+"token:"+token);
        String tokenKey = RedisConstants.LOGIN_USER_KEY + token;

        stringRedisTemplate.opsForValue().set(tokenKey, JSONUtil.toJsonStr(userDTO),
                RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
        userMap.put("token", token);

        return Result.ok(userMap);
    }

    @Override
    public Result register(RegisterFormDTO registerFormDTO) {
        return null;
    }

}

package org.pg7.scsp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.UserDTO;
import org.pg7.scsp.entity.Collage;
import org.pg7.scsp.entity.User;
import org.pg7.scsp.mapper.CollageMapper;
import org.pg7.scsp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ScspApplicationTests {
    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setIdNumber("123123");
        user.setTrueName("123123");
        boolean save = userService.save(user);
        System.out.println(save);
    }

    @Test
    void test1(){
        UserDTO userDTO = new UserDTO();
        userDTO.setIcon("123");
        userDTO.setId(123);
        userDTO.setIdNumber("123123");

        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO);
        System.out.println(userMap);
    }


@Resource
    CollageMapper collageMapper;

    @Test
    void test2(){
        List<Collage> collages = collageMapper.selectList(null);
        System.out.println(collages);
    }
}

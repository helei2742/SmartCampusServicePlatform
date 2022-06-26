package org.pg7.scsp.mapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    void queryUserInfo() {
        System.out.println(userMapper.queryUserInfo(1011));
    }

    @Test
    void queryUserInfoByIdNumber() {

        System.out.println(userMapper.queryUserInfoByIdNumber("123123"));
    }
}
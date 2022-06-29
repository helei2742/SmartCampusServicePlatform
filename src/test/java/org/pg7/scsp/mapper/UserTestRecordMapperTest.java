package org.pg7.scsp.mapper;

import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.UserTestDTO;
import org.pg7.scsp.query.UserTestQuery;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTestRecordMapperTest {

    @Resource
    UserTestRecordMapper userTestRecordMapper;

    @Test
    void conditionQueryUserTest() {
        UserTestQuery userTestQuery = new UserTestQuery();
        userTestQuery.setUserId(48);

        List<UserTestDTO> userTestDTOS = userTestRecordMapper.conditionQueryUserTest(userTestQuery);

        userTestDTOS.forEach(System.out::println);
    }
}
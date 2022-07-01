package org.pg7.scsp.service.impl;

import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeckillCourseServiceImplTest {

    @Autowired
    SeckillCourseOrderServiceImpl seckillCourseOrderService;

    @Test
    void seckillCourse() {

        Result result = seckillCourseOrderService.seckillCourse(13501, 1);
        System.out.println(result);
    }
}
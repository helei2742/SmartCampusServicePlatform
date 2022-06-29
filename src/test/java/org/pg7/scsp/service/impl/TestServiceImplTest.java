package org.pg7.scsp.service.impl;

import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.CourseTestFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestServiceImplTest {

    @Autowired
    TestServiceImpl testService;

    @Test
    void createReTestByCourseId() {
        CourseTestFormDTO courseTestFormDTO = new CourseTestFormDTO();

        courseTestFormDTO.setCourseId(12926);

        courseTestFormDTO.setStartTime(LocalDateTime.now());
        courseTestFormDTO.setEndTime(LocalDateTime.now().plusDays(7));
        courseTestFormDTO.setLocation("地狱见阎王去吧");

        testService.createReTestByCourseId(courseTestFormDTO);
    }
}
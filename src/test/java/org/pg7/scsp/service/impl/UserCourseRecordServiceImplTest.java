package org.pg7.scsp.service.impl;

import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.query.CourseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCourseRecordServiceImplTest {

    @Autowired
    UserCourseRecordServiceImpl userCourseRecordService;

    @Test
    void queryUserCourseRecord() {
        CourseQuery courseQuery = new CourseQuery();
        courseQuery.setUserId(1179);
        Result result = userCourseRecordService.queryUserCourseRecord(courseQuery);
        System.out.println(result);
    }

    @Test
    void queryUserPassCourse() {
        CourseQuery courseQuery = new CourseQuery();
        courseQuery.setUserId(1011);
        Result result = userCourseRecordService.queryUserPassCourse(courseQuery);
        System.out.println(result);
    }

    @Test
    void queryUserUnPassCourse() {
        CourseQuery courseQuery = new CourseQuery();
        courseQuery.setUserId(1011);
        Result result = userCourseRecordService.queryUserUnPassCourse(courseQuery);
        System.out.println(result);
    }
@Test
void queryUserNeedRetakeCourse(){
    CourseQuery courseQuery = new CourseQuery();
    courseQuery.setUserId(1);
    Result result = userCourseRecordService.queryUserNeedRetakeCourse(courseQuery);
    System.out.println(result);
}
    @Test
    void queryUserCredit() {
        CourseQuery courseQuery = new CourseQuery();
        courseQuery.setUserId(1011);
        Result result = userCourseRecordService.queryUserCredit(courseQuery);
        System.out.println(result);
    }

    @Test
    void queryUserTotalCredit() {
        CourseQuery courseQuery = new CourseQuery();
        courseQuery.setUserId(1011);
        Result result = userCourseRecordService.queryUserTotalCredit(courseQuery);
        System.out.println(result);
    }

    @Test
    void queryUserTotalUnPassCredit() {
        CourseQuery courseQuery = new CourseQuery();
        courseQuery.setUserId(1011);
        Result result = userCourseRecordService.queryUserTotalUnPassCredit(courseQuery);
        System.out.println(result);
    }
}
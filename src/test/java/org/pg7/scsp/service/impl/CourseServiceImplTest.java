package org.pg7.scsp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.CourseDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.dto.PageBean;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseServiceImplTest {

    @Autowired
    CourseServiceImpl courseService;


    @Test
    void queryCourseCondition() {
        CourseQuery courseQuery = new CourseQuery();
        courseQuery.setQueryType(SystemConstants.CONDITION_QUERY_PAGE_COURSE_ALLINFO);
        courseQuery.setTeacherId(68);
//        courseQuery.setSemester("2010年春季");

        Result result = courseService.conditionPageQueryCourse(courseQuery);
//        System.out.println(result);

    }

    @Test
    void currentSemesterCourse() {

        Result result = courseService.currentSemesterCourse();
        System.out.println(result);
    }

    @Test
    void queryCSTCourseByName() {
        Result result = courseService.queryCSTCourseByName("机械制图");

        printResult(result);
    }

    @Test
    void queryCSTCourseByCollageName() {
        Result result = courseService.queryCSTCourseByCollageName("数学学院");
        printResult(result);
    }
    private void printResult(Result result){
        Object data = result.getData();
        List<CourseDTO> list = (List<CourseDTO>) data;

        list.forEach(System.out::println);
        System.out.println("total:"+list.size());
    }

}
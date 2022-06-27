package org.pg7.scsp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.CourseDTO;
import org.pg7.scsp.utils.SemesterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMapperTest {

    @Resource
    CourseMapper courseMapper;

    @Test
    void queryAllCourseAllInfo() {

        List<CourseDTO> courseDTOS = courseMapper.queryAllCourseAllInfo();
        courseDTOS.forEach(this::printCourseDto);
    }

    @Test
    void queryCourseAllInfoByTeacherId() {
        List<CourseDTO> courseDTOS1 = courseMapper.queryCourseAllInfoByTeacherId(1079);
//        List<CourseDTO> courseDTOS2 = courseMapper.queryCourseAllInfoByTeacherId(1011);
//        List<CourseDTO> courseDTOS3 = courseMapper.queryCourseAllInfoByTeacherId(1080);

        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
        courseDTOS1.forEach(this::printCourseDto);
        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU\n\n");

//        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
//        courseDTOS2.forEach(this::printCourseDto);
//        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU\n\n");
//
//        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
//        courseDTOS3.forEach(this::printCourseDto);
//        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");

    }

    private void printCourseDto(CourseDTO courseDTO){
        System.out.println("++++++++++++++++++++++++");
        System.out.println(courseDTO.getCourseName());
        System.out.println(courseDTO.getCredit());
        System.out.println(courseDTO.getDetail());
        System.out.println(courseDTO.getId());
        System.out.println(courseDTO.getSemester());
        System.out.println(courseDTO.getTeacherList());
        System.out.println(courseDTO.getCourseTimeList());
        System.out.println("+++++++++++++++++++++++\n\n\n");
    }

    @Test
    void querySemesterAllCourseAllInfo() {

        List<CourseDTO> courseDTOS = courseMapper.querySemesterAllCourseAllInfo("2021年春季学期");
        System.out.println(courseDTOS);
    }

    @Test
    void queryTotalCountCourse() {
        int i = courseMapper.queryTotalCountCourse();
        System.out.println(i);
    }

    @Test
    void queryBySemesterAndCollageName() {
        String semester = SemesterUtil.getCurrentSemester();

        List<CourseDTO> list = courseMapper.queryBySemesterAndCollageName(semester, "数学学院");

        list.forEach(System.out::println);
    }
}
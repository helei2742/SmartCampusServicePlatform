package org.pg7.scsp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.pg7.scsp.dto.CourseDTO;
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
        List<CourseDTO> courseDTOS1 = courseMapper.queryCourseAllInfoByTeacherId(1012);
        List<CourseDTO> courseDTOS2 = courseMapper.queryCourseAllInfoByTeacherId(1011);
        List<CourseDTO> courseDTOS3 = courseMapper.queryCourseAllInfoByTeacherId(1013);

        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
        courseDTOS1.forEach(this::printCourseDto);
        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU\n\n");

        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
        courseDTOS2.forEach(this::printCourseDto);
        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU\n\n");

        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
        courseDTOS3.forEach(this::printCourseDto);
        System.out.println("UUUuuuUuUUUuuuuuuuuuuuuuuuuuuuuuuuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");

    }

    private void printCourseDto(CourseDTO courseDTO){
        System.out.println("++++++++++++++++++++++++");
        System.out.println(courseDTO.getCourseName());
        System.out.println(courseDTO.getCredit());
        System.out.println(courseDTO.getDetail());
        System.out.println(courseDTO.getId());
        System.out.println(courseDTO.getTeacherList());
        System.out.println(courseDTO.getCourseTimeList());
        System.out.println("+++++++++++++++++++++++\n\n\n");
    }
}
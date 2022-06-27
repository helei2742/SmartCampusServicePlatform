package org.pg7.scsp.controller;


import org.pg7.scsp.dto.Result;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.service.ICourseService;
import org.pg7.scsp.service.IUserCourseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2022-06-26
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;


    @PostMapping("/conditionPageQueryCourse")
    @ResponseBody
    public Result queryCourse(@RequestBody CourseQuery courseQuery){
        //TODO  根据条件查询课程
        return courseService.conditionPageQueryCourse(courseQuery);
    }


    @PostMapping("/currentSemesterCourse")
    @ResponseBody
    public Result currentSemesterCourse(){
        // TODO 带有缓存的查询当前学期课程

        return courseService.currentSemesterCourse();
    }

    @PostMapping("/queryCSTCourseByName")
    @ResponseBody
    public Result queryCSTCourseByName(@RequestParam("courseName") String courseName) {
        // TODO 带有缓存的查询当前学期的课程的名字教courseName的课程
        return courseService.queryCSTCourseByName(courseName);
    }

    @PostMapping("/queryCSTCourseByCollage")
    @ResponseBody
    public Result queryCSTCourseByCollage(@RequestParam("collageName")String collageName) {
        // TODO 带有缓存的查询当前学期的课程的collageName学院的
        return courseService.queryCSTCourseByCollageName(collageName);
    }


}


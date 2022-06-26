package org.pg7.scsp.controller;


import org.pg7.scsp.dto.Result;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.service.ICourseService;
import org.pg7.scsp.service.IUserCourseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private IUserCourseRecordService userCourseRecordService;

    @PostMapping("/queryCourse")
    @ResponseBody
    public Result queryCourse(@RequestBody CourseQuery courseQuery){
        //TODO  根据条件查询课程
        return courseService.queryCourse(courseQuery);
    }


    @PostMapping("/queryUserCourseRecord")
    @ResponseBody
    public Result queryUserCourseRecord(@RequestBody CourseQuery courseQuery) {
        //TODO 查询用户的选课记录
        return userCourseRecordService.queryUserCourseRecord(courseQuery);
    }

    @PostMapping("/queryUserPassCourse")
    @ResponseBody
    public Result queryUserPassCourse(@RequestBody CourseQuery courseQuery){
        //TODO 查询用户已通过的课程
        return userCourseRecordService.queryUserPassCourse(courseQuery);
    }

    @PostMapping("/queryUserUnPassCourse")
    @ResponseBody
    public Result queryUserUnPassCourse(@RequestBody CourseQuery courseQuery){
        //TODO 查询用户未通过的课程
        return userCourseRecordService.queryUserUnPassCourse(courseQuery);
    }

    @PostMapping("/queryUserCredit")
    @ResponseBody
    public Result UserHaveCredit(@RequestBody CourseQuery courseQuery){
        //TODO 查询用户的学分情况
        return userCourseRecordService.queryUserCredit(courseQuery);
    }

    @PostMapping("/queryUserTotalCredit")
    @ResponseBody
    public Result UserHaveTotalCredit(@RequestBody CourseQuery courseQuery){
        //TODO 查询用户通过的总学分
        return userCourseRecordService.queryUserTotalCredit(courseQuery);
    }
    @PostMapping("/queryUserTotalUnPassCredit")
    @ResponseBody
    public Result UserHaveTotalUnPassCredit(@RequestBody CourseQuery courseQuery){
        //TODO 查询用户未通过的总学分
        return userCourseRecordService.queryUserTotalUnPassCredit(courseQuery);
    }

}


package org.pg7.scsp.controller;

import org.pg7.scsp.dto.Result;
import org.pg7.scsp.query.CourseQuery;
import org.pg7.scsp.service.IUserCourseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courseRecord")
public class CourseRecordController {

    @Autowired
    private IUserCourseRecordService userCourseRecordService;

    @PostMapping("/conditionPageQuery")
    @ResponseBody
    public Result conditionPageQuery(@RequestBody CourseQuery courseQuery){
        // TODO 条件分页查询
        return userCourseRecordService.conditionPageQueryCourseRecord(courseQuery);
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
    public Result userHaveCredit(@RequestBody CourseQuery courseQuery){
        //TODO 查询用户的学分情况
        return userCourseRecordService.queryUserCredit(courseQuery);
    }

    @PostMapping("/queryUserTotalCredit")
    @ResponseBody
    public Result userHaveTotalCredit(@RequestBody CourseQuery courseQuery){
        //TODO 查询用户通过的总学分
        return userCourseRecordService.queryUserTotalCredit(courseQuery);
    }
    @PostMapping("/queryUserTotalUnPassCredit")
    @ResponseBody
    public Result sserHaveTotalUnPassCredit(@RequestBody CourseQuery courseQuery){
        //TODO 查询用户未通过的总学分
        return userCourseRecordService.queryUserTotalUnPassCredit(courseQuery);
    }

    @PostMapping("/queryNeedRetake")
    @ResponseBody
    public Result queryNeedRetake(@RequestBody CourseQuery courseQuery){
        //TODO 查询用户需重修课都课程名
        return userCourseRecordService.queryUserNeedRetakeCourse(courseQuery);
    }

    @PostMapping("/selectCourse")
    @ResponseBody
    public Result userSelectCourse(@RequestParam("userId") Integer userId, @RequestParam("courseId") Integer courseId){
        //TODO 用户选课

//        userCourseRecordService.userSelectCourse()
        return null;
    }
}

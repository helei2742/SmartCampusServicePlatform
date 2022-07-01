package org.pg7.scsp.controller;

import org.pg7.scsp.dto.Result;
import org.pg7.scsp.service.ISeckillCourseOrderService;
import org.pg7.scsp.service.ISeckillCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2022-06-28
 */
@Controller
@RequestMapping("/selectCourse")
public class SeckillCourseOrderController {

    @Autowired
    ISeckillCourseOrderService seckillCourseOrderService;


    @PostMapping("/seckillCourse")
    @ResponseBody
    public Result seckillCourse(@RequestParam("courseId") Integer courseId, @RequestParam("userId")  Integer userId){
        // TODO 选课

        return seckillCourseOrderService.seckillCourse(courseId, userId);
    }

    @PostMapping("/userSeckillCourseId")
    @ResponseBody
    public Result userSeckillCourseId(@RequestParam("userId")  Integer userId){
        // TODO 查询用户当前选课阶段选到的课
        return seckillCourseOrderService.userSeckillCourseId(userId);
    }

    @PostMapping("/cancelSeckillCourse")
    @ResponseBody
    public Result cancelSeckillCourseId(@RequestParam("userId")  Integer userId,
                                        @RequestParam("courseId")  Integer courseId){
        // TODO 取消选课
        return seckillCourseOrderService.cancelSeckillCourseId(userId,courseId);
    }
}


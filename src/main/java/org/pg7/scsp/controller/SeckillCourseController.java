package org.pg7.scsp.controller;


import org.pg7.scsp.dto.Result;
import org.pg7.scsp.service.ISeckillCourseOrderService;
import org.pg7.scsp.service.ISeckillCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2022-06-28
 */
@Controller
@RequestMapping("/seckillcourse")
public class SeckillCourseController {
    @Autowired
    ISeckillCourseService seckillCourseService;

    @PostMapping("/querySelectInfoById/{id}")
    @ResponseBody
    public Result querySelectInfoById(@PathVariable("id") Integer courseId){
        // TODO 根据id查询选课信息
        return seckillCourseService.querySelectInfoById(courseId);
    }


    @PostMapping("/addSeckillCourseStockToRedis")
    @ResponseBody
    public Result addSeckillCourseStockToRedis(){
        //TODO 将秒杀课程以及课余量添加到redis
        return seckillCourseService.addSeckillCourseStockToRedis();
    }

}


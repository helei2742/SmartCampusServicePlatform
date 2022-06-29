package org.pg7.scsp.controller;

import org.pg7.scsp.dto.CourseTestFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2022-06-29
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @PostMapping("/createReTest")
    @ResponseBody
    public Result createReTestByCourseId(@RequestBody CourseTestFormDTO formDTO){
        //TODO 根据课程id，创建课程的补考信息。
        return testService.createReTestByCourseId(formDTO);
    }
}


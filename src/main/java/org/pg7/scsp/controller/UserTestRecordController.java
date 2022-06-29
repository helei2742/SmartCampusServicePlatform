package org.pg7.scsp.controller;

import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.UserTestRecord;
import org.pg7.scsp.mapper.UserTestRecordMapper;
import org.pg7.scsp.query.UserTestQuery;
import org.pg7.scsp.service.IUserTestRecordService;
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
 * @since 2022-06-29
 */
@Controller
@RequestMapping("/userTestRecord")
public class UserTestRecordController {

    @Autowired
    private IUserTestRecordService userTestRecordService;

    @PostMapping("/conditionPageQuery")
    @ResponseBody
    public Result queryUserTest(@RequestBody UserTestQuery query){
        //TODO 查询用户的考试
        return userTestRecordService.conditionPageQueryUserTest(query);
    }


}


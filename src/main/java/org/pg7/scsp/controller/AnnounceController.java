package org.pg7.scsp.controller;

import org.pg7.scsp.dto.AnnounceFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.Announce;
import org.pg7.scsp.query.AnnounceQuery;
import org.pg7.scsp.service.IAnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
@Controller
@RequestMapping("/announce")
public class AnnounceController {
    @Autowired
    private IAnnounceService announceService;

    @PostMapping("/add")
    @ResponseBody
    public Result addAnnounce(@RequestBody AnnounceFormDTO announceFormDTO){
        //TODO 校验是否有删除权限
        boolean permission = announceService.checkPermission(announceFormDTO.getAuth());
        if(!permission){
            return Result.fail("权限不足！");
        }

        //TODO 添加公告
        return announceService.addAnnounce(announceFormDTO);
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result deleteAnnounce(@RequestBody AnnounceFormDTO announceFormDTO){
        //TODO 校验是否有删除权限
        boolean permission = announceService.checkPermission(announceFormDTO.getAuth());
        if(!permission){
            return Result.fail("权限不足！");
        }
        //TODO 删除公告
        return announceService.deleteAnnounce(announceFormDTO);
    }

    @PostMapping("/update")
    @ResponseBody
    public Result updateAnnounce(@RequestBody AnnounceFormDTO announceFormDTO){
        //TODO 校验是否有删除权限
        boolean permission = announceService.checkPermission(announceFormDTO.getAuth());
        if(!permission){
            return Result.fail("权限不足！");
        }

        //TODO  更改公告
        return announceService.updateAnnounce(announceFormDTO);
    }

    @PostMapping("/query")
    @ResponseBody
    public Result deleteAnnounce(@RequestBody AnnounceQuery query){

        //TODO 查询公告
        return announceService.pageQueryAnnounce(query);
    }

}


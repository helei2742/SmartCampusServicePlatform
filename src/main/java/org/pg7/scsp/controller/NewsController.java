package org.pg7.scsp.controller;



import org.pg7.scsp.dto.NewsFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.query.NewsQuery;
import org.pg7.scsp.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
@Controller
@RequestMapping("/news")
public class NewsController implements BaseController{

    @Autowired
    private INewsService newsService;

    @PostMapping("/add")
    @ResponseBody
    public Result addNews(@RequestBody NewsFormDTO newsFormDTO){
        boolean permission = this.checkPermission(newsFormDTO.getAuth());
        if(!permission) return Result.fail("权限不足！");

        //TODO 添加新闻

        return newsService.addNews(newsFormDTO);
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result deleteNews(@RequestBody NewsFormDTO newsFormDTO){
        boolean permission = this.checkPermission(newsFormDTO.getAuth());
        if(!permission) return Result.fail("权限不足！");

        //TODO 删除新闻
        return newsService.deleteNews(newsFormDTO);
    }

    @PostMapping("/update")
    @ResponseBody
    public Result updateNews(@RequestBody NewsFormDTO newsFormDTO){
        boolean permission = this.checkPermission(newsFormDTO.getAuth());
        if(!permission) return Result.fail("权限不足！");

        //TODO 更改新闻
        return newsService.updateNews(newsFormDTO);
    }
    @PostMapping("/pageQuery")
    @ResponseBody
    public Result queryNews(@RequestBody NewsQuery newsQuery){
        //TODO 查询新闻
        return newsService.pageQuery(newsQuery);
    }

    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody NewsQuery newsQuery){
        //TODO 根据id查询新闻
        return newsService.queryById(newsQuery.getId());
    }

    @GetMapping("/queryHotX/{x}")
    @ResponseBody
    public Result queryHotX(@PathVariable("x") int x){

        //TODO 查询点击量最多的x条
        return newsService.queryHotX(x);
    }

    @PostMapping("/queryByCollageId")
    @ResponseBody
    public Result queryByCollageId(@RequestBody NewsQuery newsQuery){
        //TODO 根据新闻的学院id分页来查询

        return newsService.pageQuery(newsQuery);
    }
}


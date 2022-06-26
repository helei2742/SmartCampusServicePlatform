package org.pg7.scsp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.NewsFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.News;
import org.pg7.scsp.query.NewsQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
public interface INewsService extends IService<News> {

    /**
     * 添加新闻
     * @param newsFormDTO
     * @return
     */
    Result addNews(NewsFormDTO newsFormDTO);

    /**
     * 删除新闻
     * @param newsFormDTO
     * @return
     */
    Result deleteNews(NewsFormDTO newsFormDTO);

    /**
     * 更新新闻
     * @param newsFormDTO
     * @return
     */
    Result updateNews(NewsFormDTO newsFormDTO);

    /**
     * 分页查询新闻
     * @param newsQuery
     * @return
     */
    Result pageQuery(NewsQuery newsQuery);

    /**
     * 根据id查找新闻
     * @param id
     * @return
     */
    Result queryById(int id);

    /**
     * 查询点击量在前x条的新闻的id，title和点击量
     * @param x
     * @return
     */
    Result queryHotX(int x);

}

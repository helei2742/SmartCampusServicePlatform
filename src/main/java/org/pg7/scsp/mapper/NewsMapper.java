package org.pg7.scsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.pg7.scsp.dto.NewsRedisDto;
import org.pg7.scsp.entity.News;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
public interface NewsMapper extends BaseMapper<News> {

    List<NewsRedisDto> queryTitleAndSeeCount(@Param("start") int start, @Param("len") int len);
}

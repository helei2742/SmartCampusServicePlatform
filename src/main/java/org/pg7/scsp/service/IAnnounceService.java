package org.pg7.scsp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pg7.scsp.dto.AnnounceFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.Announce;
import org.pg7.scsp.query.AnnounceQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
public interface IAnnounceService extends IService<Announce> {

    /**
     * 添加公告
     * @param announceFormDTO
     * @return
     */
    Result addAnnounce(AnnounceFormDTO announceFormDTO);

    /**
     * 删除公告
     * @param announceFormDTO
     * @return
     */
    Result deleteAnnounce(AnnounceFormDTO announceFormDTO);


    /**
     * 更新公告
     * @param announceFormDTO
     * @return
     */
    Result updateAnnounce(AnnounceFormDTO announceFormDTO);

    /**
     * 分页查询公告
     * @param query
     * @return
     */
    Result pageQueryAnnounce(AnnounceQuery query);
}

package org.pg7.scsp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pg7.scsp.dto.AnnounceFormDTO;
import org.pg7.scsp.dto.Result;
import org.pg7.scsp.entity.Announce;
import org.pg7.scsp.mapper.AnnounceMapper;
import org.pg7.scsp.query.AnnounceQuery;
import org.pg7.scsp.service.IAnnounceService;
import org.pg7.scsp.utils.SystemConstants;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helei
 * @since 2022-06-25
 */
@Service
public class AnnounceServiceImpl extends ServiceImpl<AnnounceMapper, Announce> implements IAnnounceService {

    @Override
    public Result addAnnounce(AnnounceFormDTO announceFormDTO) {
        Announce announce = BeanUtil.copyProperties(announceFormDTO, Announce.class);

        boolean save = save(announce);
        if(!save) return Result.fail("添加失败！");
        return Result.ok();
    }

    @Override
    public Result deleteAnnounce(AnnounceFormDTO announceFormDTO) {
        int id = announceFormDTO.getId();

        boolean isRemove = removeById(id);
        if(!isRemove){
           return Result.fail("删除失败");
        }
        return Result.ok();
    }



    @Override
    public Result updateAnnounce(AnnounceFormDTO announceFormDTO) {
        Announce announce = BeanUtil.copyProperties(announceFormDTO, Announce.class);

        boolean b = updateById(announce);
        if(!b) return Result.fail("更新失败！");

        return Result.ok();
    }

    @Override
    public Result pageQueryAnnounce(AnnounceQuery query) {
        Page<Announce> page = new Page<>(query.getPage(), query.getSize());

        Page<Announce> res = page(page, this.getQueryWrapper(query.getQueryType()));
        return Result.ok(res);
    }

    /**
     * 获取条件生成器
     * @param type
     * @return
     */
    private Wrapper<Announce> getQueryWrapper(int type){
        QueryWrapper<Announce> wrapper = new QueryWrapper<Announce>();
        if(type == SystemConstants.CREATE_TIME_DESC){
            wrapper.orderByDesc("create_Time");
        }else if(type == SystemConstants.CREATE_TIME_ASC){
            wrapper.orderByAsc("create_Time");
        }
        return wrapper;
    }
}

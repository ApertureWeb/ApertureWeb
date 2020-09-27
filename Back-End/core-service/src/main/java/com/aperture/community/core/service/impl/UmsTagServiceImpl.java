package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.UmsTagMap;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.manager.TagManager;
import com.aperture.community.core.module.UmsTag;
import com.aperture.community.core.module.converter.UmsTagConverter;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsTagParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsTagVO;
import com.aperture.community.core.service.IUmsTagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteTimeoutException;

/**
 * <p>
 * 标签用来标记视频、帖子 服务实现类
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-24
 */
public class UmsTagServiceImpl implements IUmsTagService {

    private TagManager tagManager;
    private PrimaryIdManager primaryIdManager;

    @Autowired
    public UmsTagServiceImpl(TagManager tagManager, PrimaryIdManager primaryIdManager) {
        this.tagManager = tagManager;
        this.primaryIdManager = primaryIdManager;
    }

    @Override
    public void delete(Long id) {
        tagManager.getUmsTagMapper().removeById(id);
    }

    @Override
    public PageVO<UmsTagVO> listPage(PageParam pageParam) {
        IPage<UmsTag> tagPage = tagManager.getUmsTagMapper().page(new Page<>(pageParam.getPage(), pageParam.getSize()),
                new QueryWrapper<UmsTag>().select(UmsTagMap.ID.getValue(), UmsTagMap.NAME.getValue()));
        long total = tagPage.getTotal();
        return new PageVO<>(total, UmsTagConverter.INSTANCE.toUmsTagVOList(tagPage.getRecords()));
    }


    @Override
    public boolean update(UmsTagParam umsTagParam) {
        return tagManager.getUmsTagMapper().updateById(UmsTagConverter.INSTANCE.toUmsTag(umsTagParam));
    }

    @Override
    public boolean save(UmsTagParam umsTagParam) {
        Long id = primaryIdManager.getPrimaryId();
        if (id == null) {
            throw new RemoteTimeoutException("Id获取失败");
        }
        umsTagParam.setId(id);
        return tagManager.getUmsTagMapper().save(UmsTagConverter.INSTANCE.toUmsTag(umsTagParam));
    }


}


package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.map.CmsTagMap;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.manager.TagManager;
import com.aperture.community.core.module.CmsTagEntity;
import com.aperture.community.core.module.converter.CmsTagConverter;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.CmsTagParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsTagVO;
import com.aperture.community.core.service.CmsTagService;
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
public class CmsTagServiceImpl implements CmsTagService {

    private TagManager tagManager;
    private PrimaryIdManager primaryIdManager;

    @Autowired
    public CmsTagServiceImpl(TagManager tagManager, PrimaryIdManager primaryIdManager) {
        this.tagManager = tagManager;
        this.primaryIdManager = primaryIdManager;
    }

    @Override
    public boolean delete(Long id) {
        return tagManager.getUmsTagMapper().removeById(id);
    }

    @Override
    public PageVO<CmsTagVO> listPage(PageParam pageParam) {
        IPage<CmsTagEntity> tagPage = tagManager.getUmsTagMapper().page(new Page<>(pageParam.getPage(), pageParam.getSize()),
                new QueryWrapper<CmsTagEntity>().select(CmsTagMap.ID.getValue(), CmsTagMap.NAME.getValue()));
        long total = tagPage.getTotal();
        return new PageVO<>(total, CmsTagConverter.INSTANCE.toUmsTagVOList(tagPage.getRecords()));
    }


    @Override
    public boolean update(CmsTagParam cmsTagParam) {
        return tagManager.getUmsTagMapper().updateById(CmsTagConverter.INSTANCE.toUmsTag(cmsTagParam));
    }

    @Override
    public boolean save(CmsTagParam cmsTagParam) {
        Long id = primaryIdManager.getPrimaryId();
        if (id == null) {
            throw new RemoteTimeoutException("Id获取失败");
        }
        cmsTagParam.setId(id);
        return tagManager.getUmsTagMapper().save(CmsTagConverter.INSTANCE.toUmsTag(cmsTagParam));
    }



}


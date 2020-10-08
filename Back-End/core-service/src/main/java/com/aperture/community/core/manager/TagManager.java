package com.aperture.community.core.manager;

import com.aperture.community.core.common.map.CmsTagMergeMap;
import com.aperture.community.core.dao.CmsTagMapper;
import com.aperture.community.core.dao.CmsTagMergeMapper;
import com.aperture.community.core.module.CmsTagEntity;
import com.aperture.community.core.module.CmsTagMergeEntity;
import com.aperture.community.core.module.converter.CmsTagConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsTagParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagManager {

    private CmsTagMapper cmsTagMapper;
    private CmsTagMergeMapper cmsTagMergeMapper;
    private PrimaryIdManager primaryIdManager;

    @Autowired
    public TagManager(CmsTagMapper cmsTagMapper, CmsTagMergeMapper cmsTagMergeMapper, PrimaryIdManager primaryIdManager) {
        this.cmsTagMapper = cmsTagMapper;
        this.cmsTagMergeMapper = cmsTagMergeMapper;
        this.primaryIdManager = primaryIdManager;
    }

    public CmsTagMapper getUmsTagMapper() {
        return this.cmsTagMapper;
    }


    @Transactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> addTag(CmsTagParam tagParam) {
        CmsTagEntity tag = CmsTagConverter.INSTANCE.toUmsTag(tagParam);

        tag.setId(primaryIdManager.getPrimaryId());
        if (!cmsTagMapper.save(tag)) {
            return new MessageDto<>("保存标签失败", false);
        }
        if (!cmsTagMergeMapper.save(new CmsTagMergeEntity(primaryIdManager.getPrimaryId(), tagParam.getContentId(), tag.getId()))) {
            throw new RuntimeException("保存标签失败");
        }
        return new MessageDto<>("success", true);
    }

    public CmsTagMergeMapper getUmsTagMergeMapper() {
        return this.cmsTagMergeMapper;
    }


//    @Transactional(rollbackFor = Exception.class)
//    public boolean addContentTag(List<CmsTagMergeEntity> umsTagMergeEntities) {
//        assert umsTagMergeEntities.size() != 0;
//        List<CmsTagEntity> tags = cmsTagMapper.list(new QueryWrapper<CmsTagEntity>().in(CmsTagMergeMap.TAG_ID.getValue(), umsTagMergeEntities.stream().map(CmsTagMergeEntity::getTagId).collect(Collectors.toList())));
//        if (tags.size() != umsTagMergeEntities.size()) {
//            throw new IllegalArgumentException("tag不存在");
//        }
//        return cmsTagMergeMapper.saveBatch(umsTagMergeEntities);
//    }


}

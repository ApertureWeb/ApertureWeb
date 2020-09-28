package com.aperture.community.core.manager;

import com.aperture.community.core.common.map.UmsTagMergeMap;
import com.aperture.community.core.dao.UmsTagMapper;
import com.aperture.community.core.dao.UmsTagMergeMapper;
import com.aperture.community.core.module.UmsTag;
import com.aperture.community.core.module.UmsTagMerge;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagManager {

    private UmsTagMapper umsTagMapper;
    private UmsTagMergeMapper umsTagMergeMapper;
    private PrimaryIdManager primaryIdManager;

    @Autowired
    public TagManager(UmsTagMapper umsTagMapper, UmsTagMergeMapper umsTagMergeMapper, PrimaryIdManager primaryIdManager) {
        this.umsTagMapper = umsTagMapper;
        this.umsTagMergeMapper = umsTagMergeMapper;
        this.primaryIdManager = primaryIdManager;
    }

    public UmsTagMapper getUmsTagMapper() {
        return this.umsTagMapper;

    }

    public UmsTagMergeMapper getUmsTagMergeMapper() {
        return this.umsTagMergeMapper;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean addContentTag(List<UmsTagMerge> umsTagMerges) {
        assert umsTagMerges.size() != 0;
        List<UmsTag> tags = umsTagMapper.list(new QueryWrapper<UmsTag>().in(UmsTagMergeMap.TAG_ID.getValue(), umsTagMerges.stream().map(UmsTagMerge::getTagId).collect(Collectors.toList())));
        if (tags.size() != umsTagMerges.size()) {
            throw new IllegalArgumentException("tag不存在");
        }
        return umsTagMergeMapper.saveBatch(umsTagMerges);
    }



}

package com.aperture.community.member.service.impl;

import com.aperture.community.member.dao.CollectionDao;
import com.aperture.community.member.entity.CollectionEntity;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.service.FavoratesService;
import com.aperture.community.member.vo.CollectionVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.CollectionService;
import org.springframework.transaction.annotation.Transactional;


@Service("collcetionsService")
public class CollectionServiceImpl extends ServiceImpl<CollectionDao, CollectionEntity> implements CollectionService {

    @Autowired
    FavoratesService favoratesService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<CollectionEntity> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key);
        }
        IPage<CollectionEntity> page = this.page(
                new Query<CollectionEntity>().getPage(params), queryWrapper
        );
        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveCollection(CollectionVo collectionVo) {
        CollectionEntity entity = new CollectionEntity();

        BeanUtils.copyProperties(collectionVo, entity);
        entity.setId(IdMaker.getId());
        entity.setCollectionDate(new Date());
        this.save(entity);
        // 收藏夹收藏的数量+1
        favoratesService.addCount(entity.getFavoratesId());
    }
}
package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.CollectionsFavoratesRelaDao;
import com.aperture.community.member.entity.CollectionsFavoratesRelaEntity;
import com.aperture.community.member.service.CollectionsFavoratesRelaService;


@Service("collectionsFavoratesRelaService")
public class CollectionsFavoratesRelaServiceImpl extends ServiceImpl<CollectionsFavoratesRelaDao, CollectionsFavoratesRelaEntity> implements CollectionsFavoratesRelaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectionsFavoratesRelaEntity> page = this.page(
                new Query<CollectionsFavoratesRelaEntity>().getPage(params),
                new QueryWrapper<CollectionsFavoratesRelaEntity>()
        );

        return new PageUtils(page);
    }

}
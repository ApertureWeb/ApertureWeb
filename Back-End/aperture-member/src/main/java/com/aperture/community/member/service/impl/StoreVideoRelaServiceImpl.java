package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.StoreVideoRelaDao;
import com.aperture.community.member.entity.StoreVideoRelaEntity;
import com.aperture.community.member.service.StoreVideoRelaService;


@Service("storeVideoRelaService")
public class StoreVideoRelaServiceImpl extends ServiceImpl<StoreVideoRelaDao, StoreVideoRelaEntity> implements StoreVideoRelaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StoreVideoRelaEntity> page = this.page(
                new Query<StoreVideoRelaEntity>().getPage(params),
                new QueryWrapper<StoreVideoRelaEntity>()
        );

        return new PageUtils(page);
    }

}
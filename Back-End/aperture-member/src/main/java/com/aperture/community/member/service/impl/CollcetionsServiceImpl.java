package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.CollcetionsDao;
import com.aperture.community.member.entity.CollcetionsEntity;
import com.aperture.community.member.service.CollcetionsService;


@Service("collcetionsService")
public class CollcetionsServiceImpl extends ServiceImpl<CollcetionsDao, CollcetionsEntity> implements CollcetionsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollcetionsEntity> page = this.page(
                new Query<CollcetionsEntity>().getPage(params),
                new QueryWrapper<CollcetionsEntity>()
        );

        return new PageUtils(page);
    }

}
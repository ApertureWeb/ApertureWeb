package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.StoreArticleRelaDao;
import com.aperture.community.member.entity.StoreArticleRelaEntity;
import com.aperture.community.member.service.StoreArticleRelaService;


@Service("storeArticleRelaService")
public class StoreArticleRelaServiceImpl extends ServiceImpl<StoreArticleRelaDao, StoreArticleRelaEntity> implements StoreArticleRelaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StoreArticleRelaEntity> page = this.page(
                new Query<StoreArticleRelaEntity>().getPage(params),
                new QueryWrapper<StoreArticleRelaEntity>()
        );

        return new PageUtils(page);
    }

}
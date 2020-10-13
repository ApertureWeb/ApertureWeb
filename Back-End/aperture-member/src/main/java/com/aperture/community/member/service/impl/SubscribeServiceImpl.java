package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.SubscribeDao;
import com.aperture.community.member.entity.SubscribeEntity;
import com.aperture.community.member.service.SubscribeService;


@Service("subscribeService")
public class SubscribeServiceImpl extends ServiceImpl<SubscribeDao, SubscribeEntity> implements SubscribeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SubscribeEntity> page = this.page(
                new Query<SubscribeEntity>().getPage(params),
                new QueryWrapper<SubscribeEntity>()
        );

        return new PageUtils(page);
    }

}
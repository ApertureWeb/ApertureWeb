package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.WatchHistoryDao;
import com.aperture.community.member.entity.WatchHistoryEntity;
import com.aperture.community.member.service.WatchHistoryService;


@Service("watchHistoryService")
public class WatchHistoryServiceImpl extends ServiceImpl<WatchHistoryDao, WatchHistoryEntity> implements WatchHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WatchHistoryEntity> page = this.page(
                new Query<WatchHistoryEntity>().getPage(params),
                new QueryWrapper<WatchHistoryEntity>()
        );

        return new PageUtils(page);
    }

}
package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.MemberWatchHistoryDao;
import com.aperture.community.member.entity.MemberWatchHistoryEntity;
import com.aperture.community.member.service.MemberWatchHistoryService;


@Service("memberWatchHistoryService")
public class MemberWatchHistoryServiceImpl extends ServiceImpl<MemberWatchHistoryDao, MemberWatchHistoryEntity> implements MemberWatchHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberWatchHistoryEntity> page = this.page(
                new Query<MemberWatchHistoryEntity>().getPage(params),
                new QueryWrapper<MemberWatchHistoryEntity>()
        );

        return new PageUtils(page);
    }

}
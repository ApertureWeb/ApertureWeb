package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.MemberWatchHistoryRelaDao;
import com.aperture.community.member.entity.MemberWatchHistoryRelaEntity;
import com.aperture.community.member.service.MemberWatchHistoryRelaService;


@Service("memberWatchHistoryRelaService")
public class MemberWatchHistoryRelaServiceImpl extends ServiceImpl<MemberWatchHistoryRelaDao, MemberWatchHistoryRelaEntity> implements MemberWatchHistoryRelaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberWatchHistoryRelaEntity> page = this.page(
                new Query<MemberWatchHistoryRelaEntity>().getPage(params),
                new QueryWrapper<MemberWatchHistoryRelaEntity>()
        );

        return new PageUtils(page);
    }

}
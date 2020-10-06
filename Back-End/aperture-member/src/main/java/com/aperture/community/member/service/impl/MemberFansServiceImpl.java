package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.MemberFansDao;
import com.aperture.community.member.entity.MemberFansEntity;
import com.aperture.community.member.service.MemberFansService;


@Service("memberFansService")
public class MemberFansServiceImpl extends ServiceImpl<MemberFansDao, MemberFansEntity> implements MemberFansService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberFansEntity> page = this.page(
                new Query<MemberFansEntity>().getPage(params),
                new QueryWrapper<MemberFansEntity>()
        );

        return new PageUtils(page);
    }

}
package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.MemberFavoratesRelaDao;
import com.aperture.community.member.entity.MemberFavoratesRelaEntity;
import com.aperture.community.member.service.MemberFavoratesRelaService;


@Service("memberFavoratesRelaService")
public class MemberFavoratesRelaServiceImpl extends ServiceImpl<MemberFavoratesRelaDao, MemberFavoratesRelaEntity> implements MemberFavoratesRelaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberFavoratesRelaEntity> page = this.page(
                new Query<MemberFavoratesRelaEntity>().getPage(params),
                new QueryWrapper<MemberFavoratesRelaEntity>()
        );

        return new PageUtils(page);
    }

}
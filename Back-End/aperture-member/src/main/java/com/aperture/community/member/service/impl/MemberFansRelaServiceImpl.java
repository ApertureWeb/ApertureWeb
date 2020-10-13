package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.MemberFansRelaDao;
import com.aperture.community.member.entity.MemberFansRelaEntity;
import com.aperture.community.member.service.MemberFansRelaService;


@Service("memberFansRelaService")
public class MemberFansRelaServiceImpl extends ServiceImpl<MemberFansRelaDao, MemberFansRelaEntity> implements MemberFansRelaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberFansRelaEntity> page = this.page(
                new Query<MemberFansRelaEntity>().getPage(params),
                new QueryWrapper<MemberFansRelaEntity>()
        );

        return new PageUtils(page);
    }

}
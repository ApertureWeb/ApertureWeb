package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.MemberCircleRelaDao;
import com.aperture.community.member.entity.MemberCircleRelaEntity;
import com.aperture.community.member.service.MemberCircleRelaService;


@Service("memberCircleRelaService")
public class MemberCircleRelaServiceImpl extends ServiceImpl<MemberCircleRelaDao, MemberCircleRelaEntity> implements MemberCircleRelaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberCircleRelaEntity> page = this.page(
                new Query<MemberCircleRelaEntity>().getPage(params),
                new QueryWrapper<MemberCircleRelaEntity>()
        );

        return new PageUtils(page);
    }

}
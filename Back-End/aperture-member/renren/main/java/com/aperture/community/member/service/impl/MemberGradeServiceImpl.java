package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.MemberGradeDao;
import com.aperture.community.member.entity.MemberGradeEntity;
import com.aperture.community.member.service.MemberGradeService;


@Service("memberGradeService")
public class MemberGradeServiceImpl extends ServiceImpl<MemberGradeDao, MemberGradeEntity> implements MemberGradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberGradeEntity> page = this.page(
                new Query<MemberGradeEntity>().getPage(params),
                new QueryWrapper<MemberGradeEntity>()
        );

        return new PageUtils(page);
    }

}
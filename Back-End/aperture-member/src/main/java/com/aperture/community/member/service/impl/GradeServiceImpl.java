package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.GradeDao;
import com.aperture.community.member.entity.GradeEntity;
import com.aperture.community.member.service.GradeService;


@Service("gradeService")
public class GradeServiceImpl extends ServiceImpl<GradeDao, GradeEntity> implements GradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GradeEntity> page = this.page(
                new Query<GradeEntity>().getPage(params),
                new QueryWrapper<GradeEntity>()
        );

        return new PageUtils(page);
    }

}
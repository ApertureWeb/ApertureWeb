package com.aperture.community.core.service.impl;

import com.aperture.community.core.module.CmsCategoryCircleRelaEntity;
import com.aperture.community.utils.PageUtils;
import com.aperture.community.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.aperture.community.core.dao.CategoryCircleRelaDao;
import com.aperture.community.core.service.CmsCategoryCircleRelaService;


@Service("categoryCircleRelaService")
public class CmsCategoryCircleRelaServiceImpl extends ServiceImpl<CategoryCircleRelaDao, CmsCategoryCircleRelaEntity> implements CmsCategoryCircleRelaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CmsCategoryCircleRelaEntity> page = this.page(
                new Query<CmsCategoryCircleRelaEntity>().getPage(params),
                new QueryWrapper<CmsCategoryCircleRelaEntity>()
        );

        return new PageUtils(page);
    }

}
package com.aperture.community.core.service.impl;

import com.aperture.community.core.module.CmsCategoryEntity;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.aperture.community.core.dao.CategoryDao;
import com.aperture.community.core.service.CategoryService;


@Service("categoryService")
public class CmsCategoryServiceImpl extends ServiceImpl<CategoryDao, CmsCategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CmsCategoryEntity> page = this.page(
                new Query<CmsCategoryEntity>().getPage(params),
                new QueryWrapper<CmsCategoryEntity>()
        );
        return new PageUtils(page);
    }

}
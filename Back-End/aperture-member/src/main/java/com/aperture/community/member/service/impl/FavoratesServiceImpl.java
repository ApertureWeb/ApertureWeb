package com.aperture.community.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.FavoratesDao;
import com.aperture.community.member.entity.FavoratesEntity;
import com.aperture.community.member.service.FavoratesService;


@Service("favoratesService")
public class FavoratesServiceImpl extends ServiceImpl<FavoratesDao, FavoratesEntity> implements FavoratesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FavoratesEntity> page = this.page(
                new Query<FavoratesEntity>().getPage(params),
                new QueryWrapper<FavoratesEntity>()
        );

        return new PageUtils(page);
    }

}
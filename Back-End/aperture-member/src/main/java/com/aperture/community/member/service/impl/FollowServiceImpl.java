package com.aperture.community.member.service.impl;

import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.service.FavoratesService;
import com.aperture.community.member.service.FollowGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.FieldPosition;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.FollowDao;
import com.aperture.community.member.entity.FollowEntity;
import com.aperture.community.member.service.FollowService;


@Service("followService")
public class FollowServiceImpl extends ServiceImpl<FollowDao, FollowEntity> implements FollowService {

    @Autowired
    FollowGroupService followGroupService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FollowEntity> page = this.page(
                new Query<FollowEntity>().getPage(params),
                new QueryWrapper<FollowEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveFollow(FollowEntity follow) {
        follow.setFollowedId(IdMaker.getId());
        this.save(follow);
        // 添加到关注分组后分组里的数量+1
        followGroupService.addfollowCount(follow.getGroupId());
    }


}
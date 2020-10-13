package com.aperture.community.member.service.impl;

import com.aperture.community.member.feign.IdMaker;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.FollowGroupDao;
import com.aperture.community.member.entity.FollowGroupEntity;
import com.aperture.community.member.service.FollowGroupService;


@Service("followGroupService")
public class FollowGroupServiceImpl extends ServiceImpl<FollowGroupDao, FollowGroupEntity> implements FollowGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FollowGroupEntity> page = this.page(
                new Query<FollowGroupEntity>().getPage(params),
                new QueryWrapper<FollowGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void addfollowCount(Long groupId) {
        FollowGroupEntity entity = this.getById(groupId);
        entity.setFollowCount(entity.getFollowCount()+1);
        this.updateById(entity);
    }

    @Override
    public void saveFollowGroup(FollowGroupEntity followGroup) {
        followGroup.setId(IdMaker.getId());
        this.save(followGroup);
    }
}
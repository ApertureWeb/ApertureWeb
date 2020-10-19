package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.entity.FollowEntity;
import com.aperture.community.member.entity.MemberCircleRelaEntity;
import com.aperture.community.member.entity.WatchHistoryEntity;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.service.FollowService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.FollowGroupDao;
import com.aperture.community.member.entity.FollowGroupEntity;
import com.aperture.community.member.service.FollowGroupService;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.ProgressBarUI;


@Service("followGroupService")
public class FollowGroupServiceImpl extends ServiceImpl<FollowGroupDao, FollowGroupEntity> implements FollowGroupService {

    @Autowired
    private FollowService followService;

    @Autowired
    private FollowGroupDao followGroupDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FollowGroupEntity> page = this.page(
                new Query<FollowGroupEntity>().getPage(params),
                new QueryWrapper<FollowGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void addfollowCount(Long groupId) {
        if(groupId == null) {
            CastExcepion.cast("addfollowCount Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        FollowGroupEntity entity = this.getById(groupId);
        entity.setFollowCount(entity.getFollowCount()+1);
        this.updateById(entity);
    }

    @Transactional
    @Override
    public void saveFollowGroup(FollowGroupEntity followGroup) {
        if(followGroup == null) {
            CastExcepion.cast("saveFollowGroup Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        followGroup.setId(IdMaker.getId());
        this.save(followGroup);
    }

    @Transactional
    @Override
    public void subFollowCount(Long groupId) {
        if(groupId == null) {
            CastExcepion.cast("subFollowCount Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        FollowGroupEntity entity = this.getById(groupId);
        entity.setFollowCount(entity.getFollowCount()-1);
        this.updateById(entity);
    }

    @Transactional
    @Override
    public void removeFollowGroup(Long groupId) {
        if(groupId == null) {
            CastExcepion.cast("removeFollowGroup Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        this.removeById(groupId);
        followService.remove(new QueryWrapper<FollowEntity>().eq("group_id", groupId));
    }

    @Override
    public List<FollowGroupEntity> getGroupList(Long memberId) {
        if(memberId == null) {
            CastExcepion.cast("getGroupList Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        QueryWrapper<FollowGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId);
        List<FollowGroupEntity> followGroupEntityList = followGroupDao.selectList(queryWrapper);
        return followGroupEntityList;
    }
}
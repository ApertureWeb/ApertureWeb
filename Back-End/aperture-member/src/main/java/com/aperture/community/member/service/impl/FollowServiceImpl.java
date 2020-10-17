package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.entity.MemberCircleRelaEntity;
import com.aperture.community.member.entity.WatchHistoryEntity;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.service.FavoratesService;
import com.aperture.community.member.service.FollowGroupService;
import com.aperture.community.member.vo.FollowCopyVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
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
import org.springframework.transaction.annotation.Transactional;


@Service("followService")
public class FollowServiceImpl extends ServiceImpl<FollowDao, FollowEntity> implements FollowService {

    @Autowired
    private FollowGroupService followGroupService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FollowEntity> page = this.page(
                new Query<FollowEntity>().getPage(params),
                new QueryWrapper<FollowEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveFollow(FollowEntity follow) {
        if(follow == null) {
            CastExcepion.cast("saveFollow Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        follow.setFollowedId(IdMaker.getId());
        this.save(follow);
        // 添加到关注分组后分组里的数量+1
        followGroupService.addfollowCount(follow.getGroupId());
        // TODO: 异步修改被关注者的关注量+1
    }

    // 删除关注
    @Override
    @Transactional
    public void removeFollow(Long id) {
        if(id == null) {
            CastExcepion.cast("removeFollow Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        Long groupId = this.getById(id).getGroupId();
        this.removeById(id);
        followGroupService.subFollowCount(groupId);
        // TODO: 异步修改被关注者的关注量-1

    }

    @Transactional
    @Override
    public void removeBatchByGroupId(Long groupId) {
        this.removeById(groupId);
    }

    @Override
    @Transactional
    public void updateFollow(Long memberId, FollowCopyVo followCopyVo) {
        if(followCopyVo == null || memberId == null || !StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("updateFollow Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        FollowEntity followEntity = new FollowEntity();
        BeanUtils.copyProperties(followCopyVo, followEntity);
        followEntity.setId(IdMaker.getId());
        followEntity.setMemberId(memberId);
        this.updateById(followEntity);
        followGroupService.addfollowCount(followCopyVo.getGroupId());
    }
}
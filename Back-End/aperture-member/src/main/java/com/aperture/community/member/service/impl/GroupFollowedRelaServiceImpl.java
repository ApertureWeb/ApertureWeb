package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.common.map.FollowGroupRelaMap;
import com.aperture.community.member.common.map.GroupFollowedRelaMap;
import com.aperture.community.member.dao.GroupFollowedRelaDao;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.manager.CommonManager;
import com.aperture.community.member.manager.FollowManager;
import com.aperture.community.member.manager.PrimaryIdManager;
import com.aperture.community.member.model.FollowGroupRelaEntity;
import com.aperture.community.member.model.GroupFollowedRelaEntity;
import com.aperture.community.member.model.converter.UmsGroupFollowedRelaConverter;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.GroupFollowedRelaParam;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.GroupFollowedRelaService;
import org.springframework.transaction.annotation.Transactional;


@Service("followGroupService")
public class GroupFollowedRelaServiceImpl extends ServiceImpl<GroupFollowedRelaDao, GroupFollowedRelaEntity> implements GroupFollowedRelaService {

    private FollowManager followManager;
    private PrimaryIdManager primaryIdManager;
    private CommonManager commonManager;

    @Autowired
    public GroupFollowedRelaServiceImpl(FollowManager followManager,
                                        PrimaryIdManager primaryIdManager,
                                        CommonManager commonManager) {
        this.followManager = followManager;
        this.primaryIdManager = primaryIdManager;
        this.commonManager = commonManager;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GroupFollowedRelaEntity> page = this.page(
                new Query<GroupFollowedRelaEntity>().getPage(params),
                new QueryWrapper<GroupFollowedRelaEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public MessageDto<List<GroupFollowedRelaEntity>> getGroupFollowList(Long memberId) {
        assert memberId != null;
        if(StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("getGroupFollowList Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        List<GroupFollowedRelaEntity> followEntities = followManager.getGroupFollowedRelaMapper().getBaseMapper().
                selectList(new QueryWrapper<GroupFollowedRelaEntity>().select(
                        GroupFollowedRelaMap.ID.getValue(),
                        GroupFollowedRelaMap.GROUP_ID.getValue(),
                        GroupFollowedRelaMap.FOLLOWED_ID.getValue()
        ).eq(FollowGroupRelaMap.MEMBER_ID.getValue(), memberId));
        if(followEntities == null) {
            return new MessageDto<>("getGroupFollowList error", null, false);
        }
        return new MessageDto<>("getGroupFollowList success", followEntities, true);
    }

    // 添加关注
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> addFollow(Long memberId, GroupFollowedRelaParam groupFollowedRelaParam) {
        assert memberId != null;
        if(groupFollowedRelaParam == null || StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("addFollow Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        GroupFollowedRelaEntity groupFollowedRelaEntity = UmsGroupFollowedRelaConverter.INSTANCE.toGroupFollowedRelaEntity(groupFollowedRelaParam);
        groupFollowedRelaEntity.setId(primaryIdManager.getPrimaryId());
        if(groupFollowedRelaParam.getGroupId() == null) {
            // 不指定分组，就默认放到默认分组
            MessageDto<Long> defaultGroupId = followManager.getFollowGroupRelaService().getDefaultGroupId(memberId);
            groupFollowedRelaEntity.setGroupId(defaultGroupId.getData());
        }
        boolean save = followManager.getGroupFollowedRelaMapper().save(groupFollowedRelaEntity);
        // 该分组下的关注量+1
        MessageDto<Boolean> booleanMessageDto = followManager.getFollowGroupRelaService().addFollowCount(groupFollowedRelaEntity.getGroupId());

        if(!save || !booleanMessageDto.getFlag()) {
            return new MessageDto<>("addFollow error", null, false);
        }
        return new MessageDto<>("addFollow success", null, true);
    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> cancelFollow(Long memberId, GroupFollowedRelaParam groupFollowedRelaParam) {
        assert memberId != null;
        if(groupFollowedRelaParam == null || StringUtils.isEmpty(memberId.toString()) || StringUtils.isEmpty(groupFollowedRelaParam.getFollowedId().toString())) {
            CastExcepion.cast("cancelFollow Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        boolean remove = followManager.getGroupFollowedRelaMapper().remove(new QueryWrapper<GroupFollowedRelaEntity>().
                eq(GroupFollowedRelaMap.FOLLOWED_ID.getValue(), groupFollowedRelaParam.getFollowedId()).
                eq(GroupFollowedRelaMap.GROUP_ID.getValue(), groupFollowedRelaParam.getGroupId()));

        // 该分组下的关注量-1
        MessageDto<Boolean> booleanMessageDto = followManager.getFollowGroupRelaService().subFollowCount(groupFollowedRelaParam.getGroupId());
        if(!remove || !booleanMessageDto.getFlag()) {
            return new MessageDto<>("cancelFollow error", null, false);
        }
        return new MessageDto<>("cancelFollow success", null, true);
    }

    // 设置关注用户到一个或多个分组
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> setFollowtoGroup(Long memberId, GroupFollowedRelaParam groupFollowedRelaParam) {
        assert memberId != null;
        if(groupFollowedRelaParam == null || StringUtils.isEmpty(memberId.toString()) || StringUtils.isEmpty(groupFollowedRelaParam.getFollowedId().toString())) {
            CastExcepion.cast("setFollowtoGroup Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        Long followedId = groupFollowedRelaParam.getFollowedId();
        if(groupFollowedRelaParam.getGroupIdList().size() == 0) {
            MessageDto<Long> defaultGroupId = followManager.getFollowGroupRelaService().getDefaultGroupId(memberId);
            groupFollowedRelaParam.getGroupIdList().add(defaultGroupId.getData());
        }
        List<GroupFollowedRelaEntity> groupFollowedRelaEntityList = groupFollowedRelaParam.getGroupIdList().stream().map(groupId -> {
            GroupFollowedRelaEntity groupFollowedRelaEntity = new GroupFollowedRelaEntity();
            groupFollowedRelaEntity.setId(primaryIdManager.getPrimaryId());
            groupFollowedRelaEntity.setFollowedId(followedId);
            groupFollowedRelaEntity.setGroupId(groupId);
            followManager.getFollowGroupRelaService().addFollowCount(groupId);
            return groupFollowedRelaEntity;
        }).collect(Collectors.toList());
        boolean saveBatch = followManager.getGroupFollowedRelaMapper().saveBatch(groupFollowedRelaEntityList);
        if(!saveBatch) {
            return new MessageDto<>("cancelFollow error", null, false);
        }
        return new MessageDto<>("cancelFollow success", null, true);
    }



}
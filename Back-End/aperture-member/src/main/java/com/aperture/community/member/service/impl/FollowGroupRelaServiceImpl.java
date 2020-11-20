package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.common.constatnt.FollowConstant;
import com.aperture.community.member.common.map.FollowGroupRelaMap;
import com.aperture.community.member.dao.FollowGroupRelaDao;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.manager.CommonManager;
import com.aperture.community.member.manager.FollowManager;
import com.aperture.community.member.manager.PrimaryIdManager;
import com.aperture.community.member.model.FollowGroupRelaEntity;
import com.aperture.community.member.model.converter.UmsFollowGroupRelaConverter;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.FollowGroupRelaParam;
import com.aperture.community.member.model.vo.FollowGroupRelaVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.FollowGroupRelaService;
import org.springframework.transaction.annotation.Transactional;


@Service("followGroupRelaService")
public class FollowGroupRelaServiceImpl extends ServiceImpl<FollowGroupRelaDao, FollowGroupRelaEntity> implements FollowGroupRelaService {

    private FollowManager followManager;
    private CommonManager commonManager;
    private PrimaryIdManager primaryIdManager;

    @Autowired
    public FollowGroupRelaServiceImpl(FollowManager followManager,
                                      CommonManager commonManager,
                                      PrimaryIdManager primaryIdManager) {
        this.followManager = followManager;
        this.commonManager = commonManager;
        this.primaryIdManager = primaryIdManager;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FollowGroupRelaEntity> page = this.page(
                new Query<FollowGroupRelaEntity>().getPage(params),
                new QueryWrapper<FollowGroupRelaEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public MessageDto<List<FollowGroupRelaVo>> getFollowGroupList(Long memberId) {
        assert memberId != null;
        if (StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("getFollowGroupList error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        List<FollowGroupRelaEntity> followEntities = followManager.getFollowGroupRelaMapper().getBaseMapper().selectList(
                new QueryWrapper<FollowGroupRelaEntity>().
                        select(FollowGroupRelaMap.ID.getValue()).
                        select(FollowGroupRelaMap.GROUP_ID.getValue()).
                        select(FollowGroupRelaMap.FOLLOW_COUNT.getValue()).
                        eq(FollowGroupRelaMap.MEMBER_ID.getValue(), memberId));
        List<FollowGroupRelaVo> followGroupRelaVos = UmsFollowGroupRelaConverter.INSTANCE.toFollowGroupRelaVoList(followEntities);
        if (followGroupRelaVos == null) {
            return new MessageDto<>("getFollowGroupList error", null, false);
        }
        return new MessageDto<>("getFollowGroupList success", followGroupRelaVos, true);
    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> addFollowGroup(Long memberId, FollowGroupRelaParam followGroupRelaParam) {
        assert memberId != null;
        if (StringUtils.isEmpty(memberId.toString()) || followGroupRelaParam == null) {
            CastExcepion.cast("addFollowGroup error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        FollowGroupRelaEntity followGroupRelaEntity = UmsFollowGroupRelaConverter.INSTANCE.toFollowGroupRelaEntity(followGroupRelaParam);
        followGroupRelaEntity.setMemberId(memberId);
        followGroupRelaEntity.setId(primaryIdManager.getPrimaryId());
        followGroupRelaEntity.setIsDefault(0);
        followGroupRelaEntity.setIsSpecial(0);
        followGroupRelaEntity.setFollowCount(0);
        boolean save = followManager.getFollowGroupRelaMapper().save(followGroupRelaEntity);
        if (!save) {
            return new MessageDto<>("addFollowGroup error", null, false);
        }
        return new MessageDto<>("addFollowGroup success", null, true);
    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> removeFollowGroup(Long memberId, Long groupId) {
        assert memberId != null;
        if (StringUtils.isEmpty(groupId.toString())) {
            CastExcepion.cast("removeFollowGroup error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        boolean remove = followManager.getFollowGroupRelaMapper().remove(new QueryWrapper<FollowGroupRelaEntity>().
                eq(FollowGroupRelaMap.GROUP_ID.getValue(), groupId));
        if (!remove) {
            return new MessageDto<>("removeFollowGroup error", null, false);
        }
        return new MessageDto<>("removeFollowGroup success", null, true);
    }

    @Override
    public MessageDto<Boolean> addFollowCount(Long groupId) {
        assert groupId != null;
        if (StringUtils.isEmpty(groupId.toString())) {
            CastExcepion.cast("addFollowCount error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        boolean update = followManager.getFollowGroupRelaMapper().update(new UpdateWrapper<FollowGroupRelaEntity>().
                setSql(FollowGroupRelaMap.FOLLOW_COUNT.getValue() + "=" + (Integer.parseInt(FollowGroupRelaMap.FOLLOW_COUNT.getValue()) + 1)).
                eq(FollowGroupRelaMap.GROUP_ID.getValue(), groupId));
        if (!update) {
            return new MessageDto<>("addFollowCount error", null, false);
        }
        return new MessageDto<>("addFollowCount success", null, true);
    }

    @Override
    public MessageDto<Boolean> subFollowCount(Long groupId) {
        assert groupId != null;
        if (StringUtils.isEmpty(groupId.toString())) {
            CastExcepion.cast("removeFollowGroup error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        boolean update = followManager.getFollowGroupRelaMapper().update(new UpdateWrapper<FollowGroupRelaEntity>().
                setSql(FollowGroupRelaMap.FOLLOW_COUNT.getValue() + "=" + (Integer.parseInt(FollowGroupRelaMap.FOLLOW_COUNT.getValue()) - 1)).
                eq(FollowGroupRelaMap.GROUP_ID.getValue(), groupId));
        if (!update) {
            return new MessageDto<>("removeFollowGroup error", null, false);
        }
        return new MessageDto<>("removeFollowGroup success", null, true);
    }

    @Override
    public MessageDto<Long> getDefaultGroupId(Long memberId) {
        assert memberId != null;
        if (StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("getDefaultGroupId error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        FollowGroupRelaEntity followGroupRelaEntity = followManager.getFollowGroupRelaMapper().getOne(new QueryWrapper<FollowGroupRelaEntity>().
                select(FollowGroupRelaMap.GROUP_ID.getValue()).
                eq(FollowGroupRelaMap.MEMBER_ID.getValue(), memberId).
                eq(FollowGroupRelaMap.IS_DEFAULT.getValue(), Integer.parseInt(FollowConstant.IS_DEFAULT_GROUP.getValue())));

        if (followGroupRelaEntity.getGroupId() == null) {
            return new MessageDto<>("getDefaultGroupId error", null, false);
        }
        return new MessageDto<>("getDefaultGroupId success", followGroupRelaEntity.getGroupId(), true);
    }

    @Override
    public MessageDto<Long> getSpecialGroupId(Long memberId) {
        assert memberId != null;
        if (StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("getSpecialGroupId error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        FollowGroupRelaEntity followGroupRelaEntity = followManager.getFollowGroupRelaMapper().getOne(new QueryWrapper<FollowGroupRelaEntity>().
                select(FollowGroupRelaMap.GROUP_ID.getValue()).
                eq(FollowGroupRelaMap.MEMBER_ID.getValue(), memberId).
                eq(FollowGroupRelaMap.IS_SPECIAL.getValue(), Integer.parseInt(FollowConstant.IS_SPECIAL_GROUP.getValue())));
        Long groupId = followGroupRelaEntity.getGroupId();

        if (groupId == null) {
            return new MessageDto<>("getSpecialGroupId error", null, false);
        }
        return new MessageDto<>("getSpecialGroupId success", groupId, true);
    }
}
package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.member.common.constatnt.MemberCircleConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.common.map.LoginLogMap;
import com.aperture.community.member.common.map.MemberCircleRelaMap;
import com.aperture.community.member.dao.MemberCircleRelaDao;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.manager.CommonManager;
import com.aperture.community.member.manager.MemberCircleManager;
import com.aperture.community.member.manager.PrimaryIdManager;
import com.aperture.community.member.model.LoginLogEntity;
import com.aperture.community.member.model.MemberCircleRelaEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.vo.MemberCircleRelaVo;
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

import com.aperture.community.member.service.MemberCircleRelaService;
import org.springframework.transaction.annotation.Transactional;


@Service("memberCircleRelaService")
public class MemberCircleRelaServiceImpl extends ServiceImpl<MemberCircleRelaDao, MemberCircleRelaEntity> implements MemberCircleRelaService {

    private MemberCircleManager memberCircleManager;
    private PrimaryIdManager primaryIdManager;
    private CommonManager commonManager;

    @Autowired
    public MemberCircleRelaServiceImpl(MemberCircleManager memberCircleManager,
                                       PrimaryIdManager primaryIdManager,
                                       CommonManager commonManager) {
        this.memberCircleManager = memberCircleManager;
        this.primaryIdManager = primaryIdManager;
        this.commonManager = commonManager;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<MemberCircleRelaEntity> queryWrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key).or().eq("memebr_id", key).or().eq("circle_id", key);
        }
        IPage<MemberCircleRelaEntity> page = this.page(
                new Query<MemberCircleRelaEntity>().getPage(params), queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public MessageDto<MemberCircleRelaEntity> getMemberCircleInfo(Long memberId) {
        if(memberId == null) {
            CastExcepion.cast("getMemberCircleInfo Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        MemberCircleRelaEntity memberCircleRelaEntity = memberCircleManager.getMemberCircleRelaMapper().getOne(new QueryWrapper<MemberCircleRelaEntity>().select(
                MemberCircleRelaMap.ID.getValue(),
                MemberCircleRelaMap.CIRCLE_ID.getValue()).
                eq(MemberCircleRelaMap.MEMEBR_ID.getValue(), memberId));
        if(memberCircleRelaEntity == null) {
            return new MessageDto<>("getMemberCircleInfo error", null, false);
        }
        memberCircleRelaEntity.setMemebrId(memberId);
        return new MessageDto<>("getMemberCircleInfo success", memberCircleRelaEntity, true);
    }

    @Override
    public MessageDto<MemberCircleRelaVo> getMemberCircleRela(Long memberId) {
        if(memberId == null) {
            CastExcepion.cast("getMemberCircleInfo Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        MemberCircleRelaEntity memberCircleRelaEntity = memberCircleManager.getMemberCircleRelaMapper().getOne(new QueryWrapper<MemberCircleRelaEntity>().select(
                MemberCircleRelaMap.ID.getValue(),
                MemberCircleRelaMap.CIRCLE_ID.getValue()).
                eq(MemberCircleRelaMap.MEMEBR_ID.getValue(), memberId));
        if(memberCircleRelaEntity == null) {
            return new MessageDto<>("getMemberCircleInfo error", null, false);
        }
        memberCircleRelaEntity.setMemebrId(memberId);
        return new MessageDto<>("getMemberCircleInfo success", memberCircleRelaEntity, true);
    }

    @Transactional
    @Override
    public void createCircle(MemberCircleVo memberCircleVo) {
        if(memberCircleVo == null || memberCircleVo.getCircleId() == null) {
            CastExcepion.cast("createCircle Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        MemberCircleRelaEntity entity = new MemberCircleRelaEntity();
        BeanUtils.copyProperties(memberCircleVo, entity);
        entity.setId(IdMaker.getId());
        entity.setPosition(MemberCircleConstant.PositionEnum.POSITION_MASTER.getCode());
        this.save(entity);

        // TODO：通知用户创建圈子成功

    }

    @Transactional
    @Override
    public void joinCircle(MemberCircleRelaEntity memberCircleRela) {
        if(memberCircleRela == null) {
            CastExcepion.cast("joinCircle Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        memberCircleRela.setId(IdMaker.getId());
        memberCircleRela.setPosition(MemberCircleConstant.PositionEnum.POSITION_MEMBERS.getCode());
        this.save(memberCircleRela);
    }

    @Transactional
    @Override
    public void removeCircle(Long circleId) {
        if(circleId == null) {
            CastExcepion.cast("removeCircle Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        this.remove(new QueryWrapper<MemberCircleRelaEntity>().eq("circle_id", circleId));
    }

    @Transactional
    @Override
    public void exitCircle(Long memberId, Long circleId) {
        if(circleId == null || memberId == null) {
            CastExcepion.cast("exitCircle Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        this.remove(new QueryWrapper<MemberCircleRelaEntity>().eq("member_id", memberId).eq("circle_id", circleId));
    }

    @Override
    public void updatePositioon(MemberCircleRelaEntity memberCircleRela) {
        if(memberCircleRela == null) {
            CastExcepion.cast("updatePosition Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        this.updateById(memberCircleRela);
        // TODO：发送给该用户，修改身份成功


        // TODO: 发送广播消息，xxx已经成为xxx

    }

    @Override
    public List<MemberCircleRelaEntity> getMemberCircleByMemberId(Long memberId) {
        if(StringUtils.isEmpty(memberId.toString()) || memberId == null) {
            CastExcepion.cast("getMemberCircleByMemberId error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        List<MemberCircleRelaEntity> memberCircle = baseMapper.selectList(new QueryWrapper<MemberCircleRelaEntity>().eq("member_id", memberId));
        return memberCircle;
    }
}
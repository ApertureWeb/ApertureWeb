package com.aperture.community.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aperture.common.utils.CastExcepion;
import com.aperture.community.member.common.constatnt.GradeConstant;
import com.aperture.community.member.common.constatnt.MemberConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.common.map.MemberMap;
import com.aperture.community.member.dao.MemberDao;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.manager.MemberManager;
import com.aperture.community.member.model.MemberEntity;
import com.aperture.community.member.model.converter.UmsMemberConverter;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.respVo.MemberBaseInfoRespVo;
import com.aperture.community.member.model.vo.MemberBaseInfoVo;
import com.aperture.community.utils.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.MemberService;
import org.springframework.transaction.annotation.Transactional;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    private MemberManager memberManager;

    @Autowired
    public MemberServiceImpl(MemberManager memberManager) {
        this.memberManager = memberManager;
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<MemberEntity> queryWrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key).or().eq("username", key).or().eq("telephone", key)
                    .or().eq("grade_level", key).or().eq("is_certificated", key).or().likeRight("nickname", key);
        }
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params), queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public MessageDto<MemberEntity> getMemberAllInfo(Long memberId) {
        if(memberId == null || !StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("getMemberInfo error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        MemberEntity memberEntity = memberManager.getMemberMapper().getById(memberId);
        if(memberEntity == null) {
            return new MessageDto<>("getMemberInfo error", null, false);
        }
        return new MessageDto<>("getMemberInfo success", memberEntity, true);
    }


    @Override
    public MessageDto<MemberBaseInfoVo> getMemberBaseInfo(Long memberId) {
        if(memberId == null || !StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("getMemberBaseInfo error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        MemberEntity memberEntity = memberManager.getMemberMapper().getOne(new QueryWrapper<MemberEntity>().select(
                MemberMap.ID.getValue(),
                MemberMap.USERNAME.getValue(),
                MemberMap.MOBILE.getValue(),
                MemberMap.NICKNAME.getValue(),
                MemberMap.GENDER.getValue(),
                MemberMap.HEAD_URL.getValue(),
                MemberMap.EMAIL.getValue(),
                MemberMap.DONUT.getValue(),
                MemberMap.IS_VIP.getValue(),
                MemberMap.GRADE_LEVEL.getValue()
        ).eq(MemberMap.ID.getValue(), memberId));
        MemberBaseInfoVo memberBaseInfoVo = UmsMemberConverter.INSTANCE.toMemberBaseInfoVo(memberEntity);

        if(memberBaseInfoVo == null) {
            return new MessageDto<>("getMemberBaseInfo error", null, false);
        }
        return new MessageDto<>("getMemberBaseInfo success", memberBaseInfoVo, true);
    }

    @Override
    public MessageDto<List<MemberBaseInfoVo>> getMemberBaseInfoByIdList(List<Long> memberIdList) {
        if(memberIdList == null || memberIdList.size() == 0)) {
            CastExcepion.cast("getMemberBaseInfoByIdList error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        ArrayList<MemberBaseInfoVo> memberBaseInfoVos = new ArrayList<>();
        memberManager.getMemberMapper().getBaseMapper().selectList(new QueryWrapper<>())
        MemberEntity memberEntity = memberManager.getMemberMapper().(new QueryWrapper<MemberEntity>().select(
                MemberMap.ID.getValue(),
                MemberMap.USERNAME.getValue(),
                MemberMap.MOBILE.getValue(),
                MemberMap.NICKNAME.getValue(),
                MemberMap.GENDER.getValue(),
                MemberMap.HEAD_URL.getValue(),
                MemberMap.EMAIL.getValue(),
                MemberMap.DONUT.getValue(),
                MemberMap.IS_VIP.getValue(),
                MemberMap.GRADE_LEVEL.getValue()
        ).eq(MemberMap.ID.getValue(), memberId));
        MemberBaseInfoVo memberBaseInfoVo = UmsMemberConverter.INSTANCE.toMemberBaseInfoVo(memberEntity);

        if(memberBaseInfoVo == null) {
            return new MessageDto<>("getMemberBaseInfoByIdList error", null, false);
        }
        return new MessageDto<>("getMemberBaseInfoByIdList success", memberBaseInfoVo, true);
    }

    @Transactional
    @Override
    public void saveMemberInfo(MemberEntity member) {
        if(member == null) {
            CastExcepion.cast("member为null", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        Long memberId = IdMaker.getId();
        member.setId(memberId);
        member.setPassword(MD5.encrypt(member.getPassword()));
        member.setDonut(MemberConstant.MemberEnum.DONUT_ZERO.getCode());
        member.setFansCount(MemberConstant.MemberEnum.FANS_COUNT.getCode());

        member.setGradeLevel(GradeConstant.GradeEnum.GRADE_ONE.getCode());
        member.setIsVip(MemberConstant.MemberEnum.NOT_VIP.getCode());
        member.setMemberStatus(MemberConstant.MemberEnum.NORMAL.getCode());
        this.save(member);

        // TODO: 消息队列生成用户登录日志信息

        // TODO: 消息队列异步生成默认收藏夹、默认关注分组

        // TODO: 消息对垒异步生成全部关注、特别关注、悄悄关注、默认分组

    }

    @Override
    public void updateMemberInfo(MemberEntity memberEntity) {
        if(memberEntity == null) {
            CastExcepion.cast("updateMemberInfo为null", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        this.updateById(memberEntity);
    }


    @Cacheable(value = {"memberBaseInfo"}, key = "#root.methodName")
    public MemberBaseInfoRespVo getMemberBaseInfoFromDB(Long memberId) {
        String memberBaseInfo = (String) redisTemplate.opsForValue().get("memberBaseInfo");
        if(!StringUtils.isEmpty(memberBaseInfo)) {
            MemberBaseInfoRespVo memberBaseInfoRespVo = JSON.parseObject(memberBaseInfo, new TypeReference<MemberBaseInfoRespVo>() {
            });
            return memberBaseInfoRespVo;
        }
        MemberEntity memberEntity = this.getOne(new QueryWrapper<MemberEntity>().eq("member_id", memberId));
        MemberBaseInfoRespVo memberBaseInfoRespVo = new MemberBaseInfoRespVo();
        BeanUtils.copyProperties(memberEntity, memberBaseInfoRespVo);

        redisTemplate.opsForValue().setIfAbsent("memberBaseInfo", JSON.toJSONString(memberBaseInfoRespVo), 30, TimeUnit.DAYS);
        return memberBaseInfoRespVo;
    }


}
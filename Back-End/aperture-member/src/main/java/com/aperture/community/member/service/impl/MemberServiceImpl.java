package com.aperture.community.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aperture.common.utils.CastExcepion;
import com.aperture.community.constant.GradeConstant;
import com.aperture.community.constant.MemberConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.service.MemberGradeService;
import com.aperture.community.member.vo.rspVo.MemberBaseInfoRespVo;
import com.aperture.community.utils.MD5;
import com.sun.xml.internal.ws.message.EmptyMessageImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.MemberDao;
import com.aperture.community.member.entity.MemberEntity;
import com.aperture.community.member.service.MemberService;
import org.springframework.transaction.annotation.Transactional;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private MemberGradeService memberGradeService;

    @Autowired
    private RedisTemplate redisTemplate;

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

        Long gradeId = memberGradeService.gradeInit();
        member.setGradeUid(gradeId);
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

    @Override
    public MemberBaseInfoRespVo getMemberBaseInfo(Long memberId) {
        if(memberId == null || !StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("getMemberBaseInfo error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        String memberBaseInfo = (String) redisTemplate.opsForValue().get("memberBaseInfo");
        if(StringUtils.isEmpty(memberBaseInfo)) {
            MemberBaseInfoRespVo memberBaseInfoFromDB = getMemberBaseInfoFromDB(memberId);
            return memberBaseInfoFromDB;
        }
        // 缓存中有数据
        MemberBaseInfoRespVo memberBaseInfoRespVo = JSON.parseObject(memberBaseInfo, new TypeReference<MemberBaseInfoRespVo>() {
        });
        return memberBaseInfoRespVo;
    }

    private MemberBaseInfoRespVo getMemberBaseInfoFromDB(Long memberId) {
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
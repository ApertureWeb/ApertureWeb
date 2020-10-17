package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.constant.GradeConstant;
import com.aperture.community.constant.MemberConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.entity.MemberCircleRelaEntity;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.service.MemberGradeService;
import com.aperture.community.utils.MD5;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
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
}
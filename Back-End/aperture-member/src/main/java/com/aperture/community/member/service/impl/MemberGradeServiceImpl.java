package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.constant.GradeConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.entity.MemberCircleRelaEntity;
import com.aperture.community.member.entity.MemberEntity;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.service.GradeService;
import com.aperture.community.member.service.MemberService;
import com.aperture.community.member.vo.rspVo.GradeValueRespVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.MemberGradeDao;
import com.aperture.community.member.entity.MemberGradeEntity;
import com.aperture.community.member.service.MemberGradeService;
import org.springframework.transaction.annotation.Transactional;
import sun.print.PSPrinterJob;


@Service("memberGradeService")
public class MemberGradeServiceImpl extends ServiceImpl<MemberGradeDao, MemberGradeEntity> implements MemberGradeService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private GradeService gradeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<MemberGradeEntity> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key).or().eq("current_grade", key);
        }
        IPage<MemberGradeEntity> page = this.page(
                new Query<MemberGradeEntity>().getPage(params), queryWrapper
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public Long gradeInit() {
        MemberGradeEntity memberGradeEntity = new MemberGradeEntity();
        memberGradeEntity.setId(IdMaker.getId());
        memberGradeEntity.setGradeValue(GradeConstant.GradeEnum.GRADE_ONE.getCode());
        memberGradeEntity.setGradeValue(GradeConstant.GradeEnum.GRADE_VALUE_ZERO.getCode());

        this.save(memberGradeEntity);
        return memberGradeEntity.getId();
    }

    // 增加经验
    @Override
    @Transactional
    public void updateGradeValue(Long memberId) {
        GradeValueRespVo gradeValueInfo = gradeService.getGradeValueInfo();
        // TODO: 接收需要增加经验的类型

    }

    // 升级
    @Override
    @Transactional
    public void updateMemberGrade(Long memberId) {
        // TODO: 升级, 发送升级消息


    }

    @Override
    public MemberGradeEntity getGradeByMemberId(Long memberId) {
        MemberGradeEntity memberGrade = this.getOne(new QueryWrapper<MemberGradeEntity>().eq("member_id", memberId));
        return memberGrade;
    }
}
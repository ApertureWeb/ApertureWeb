package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.constant.GradeConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.entity.WatchHistoryEntity;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.vo.rspVo.GradeValueRespVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.GradeDao;
import com.aperture.community.member.entity.GradeEntity;
import com.aperture.community.member.service.GradeService;


@Service("gradeService")
public class GradeServiceImpl extends ServiceImpl<GradeDao, GradeEntity> implements GradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GradeEntity> page = this.page(
                new Query<GradeEntity>().getPage(params),
                new QueryWrapper<GradeEntity>()
        );

        return new PageUtils(page);
    }

    // 添加各个等级的信息
    @Override
    public void saveGrade(GradeEntity grade) {
        if(grade == null) {
            CastExcepion.cast("saveGrade Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        QueryWrapper<GradeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("grade_level", grade.getGradeLevel()).or().eq("name",grade.getName());
        if(this.getOne(queryWrapper) != null) {
            grade.setId(IdMaker.getId());
            this.save(grade);
        }
    }

    @Override
    public GradeValueRespVo getGradeValueInfo() {
        GradeEntity gradeEntity = this.getOne(new QueryWrapper<GradeEntity>().eq("grade_level", GradeConstant.GradeEnum.GRADE_ONE.getCode()));
        GradeValueRespVo gradeValueRespVo = new GradeValueRespVo();
        BeanUtils.copyProperties(gradeEntity, gradeValueRespVo);
        return gradeValueRespVo;
    }
}
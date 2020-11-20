package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.common.map.GradeMap;
import com.aperture.community.member.dao.GradeDao;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.manager.CommonManager;
import com.aperture.community.member.manager.GradeManager;
import com.aperture.community.member.manager.PrimaryIdManager;
import com.aperture.community.member.model.GradeEntity;
import com.aperture.community.member.model.GroupFollowedRelaEntity;
import com.aperture.community.member.model.converter.UmsGradeConverter;
import com.aperture.community.member.model.converter.UmsGroupFollowedRelaConverter;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.GradeParam;
import com.aperture.community.member.model.vo.GradePermissionInfoVo;
import com.aperture.community.member.model.vo.GradeValueInfoVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.GradeService;


@Service("gradeService")
public class GradeServiceImpl extends ServiceImpl<GradeDao, GradeEntity> implements GradeService {

    private GradeManager gradeManager;
    private PrimaryIdManager primaryIdManager;
    private CommonManager commonManager;

    @Autowired
    public GradeServiceImpl(GradeManager gradeManager,
                            PrimaryIdManager primaryIdManager,
                            CommonManager commonManager) {
        this.gradeManager = gradeManager;
        this.primaryIdManager = primaryIdManager;
        this.commonManager = commonManager;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GradeEntity> page = this.page(
                new Query<GradeEntity>().getPage(params),
                new QueryWrapper<GradeEntity>()
        );
        return new PageUtils(page);
    }

    // 查看对应等级信息
    @Override
    public MessageDto<GradeEntity> getGradeAllInfo(Integer gradeLevel) {
        assert gradeLevel != null;
        if(StringUtils.isEmpty(gradeLevel.toString())) {
            CastExcepion.cast("getGradeAllInfo Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        GradeEntity gradeEntity = gradeManager.getGradeMapper().getOne(new QueryWrapper<GradeEntity>().
                eq(GradeMap.GRADE_LEVEL.getValue(), gradeLevel));
        if(gradeEntity == null) {
            return new MessageDto<>("getGradeAllInfo error", null, false);
        }
        return new MessageDto<>("getGradeAllInfo success", gradeEntity, true);
    }

    @Override
    public MessageDto<GradePermissionInfoVo> getGradePermissionInfo(Integer gradeLevel) {
        assert gradeLevel != null;
        if(StringUtils.isEmpty(gradeLevel.toString())) {
            CastExcepion.cast("getGradePermissionInfo Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        GradeEntity gradeEntity = gradeManager.getGradeMapper().getOne(new QueryWrapper<GradeEntity>().
                eq(GradeMap.GRADE_LEVEL.getValue(), gradeLevel));
        GradePermissionInfoVo gradePermissionInfoVo = UmsGradeConverter.INSTANCE.toGradePermissionInfoVo(gradeEntity);
        if(gradePermissionInfoVo == null) {
            return new MessageDto<>("getGradePermissionInfo error", null, false);
        }
        return new MessageDto<>("getGradePermissionInfo success", gradePermissionInfoVo, true);
    }

    @Override
    public MessageDto<GradeValueInfoVo> getGradeValueInfo(Integer gradeLevel) {
        assert gradeLevel != null;
        if(StringUtils.isEmpty(gradeLevel.toString())) {
            CastExcepion.cast("getGradeValueInfo Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        GradeEntity gradeEntity = gradeManager.getGradeMapper().getOne(new QueryWrapper<GradeEntity>().
                eq(GradeMap.GRADE_LEVEL.getValue(), gradeLevel));
        GradeValueInfoVo gradeValueInfoVo = UmsGradeConverter.INSTANCE.toGradeValueInfoVo(gradeEntity);
        if(gradeValueInfoVo == null) {
            return new MessageDto<>("getGradeValueInfo error", null, false);
        }
        return new MessageDto<>("getGradeValueInfo success", gradeValueInfoVo, true);
    }

    @Override
    public MessageDto<Boolean> saveGradeInfo(GradeParam gradeParam) {
        if(gradeParam == null) {
            CastExcepion.cast("saveGradeInfo Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        GradeEntity gradeEntity = UmsGradeConverter.INSTANCE.toGradeEntity(gradeParam);
        boolean save = gradeManager.getGradeMapper().save(gradeEntity);
        if(!save) {
            return new MessageDto<>("saveGradeInfo error", null, false);
        }
        return new MessageDto<>("saveGradeInfo success", null, true);
    }

    @Override
    public MessageDto<Boolean> updateGradeInfo(GradeParam gradeParam) {
        if(gradeParam == null) {
            CastExcepion.cast("updateGradeInfo Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        GradeEntity gradeEntity = UmsGradeConverter.INSTANCE.toGradeEntity(gradeParam);
        boolean update = gradeManager.getGradeMapper().updateById(gradeEntity);
        if(!update) {
            return new MessageDto<>("updateGradeInfo error", null, false);
        }
        return new MessageDto<>("updateGradeInfo success", null, true);
    }



}
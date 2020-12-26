package com.aperture.community.member.service;

import com.aperture.community.member.model.GradeEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.GradeParam;
import com.aperture.community.member.model.vo.GradePermissionInfoVo;
import com.aperture.community.member.model.vo.GradeValueInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-12 21:32:49
 */
public interface GradeService extends IService<GradeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MessageDto<GradeEntity> getGradeAllInfo(Integer gradeLevel);

    MessageDto<GradePermissionInfoVo> getGradePermissionInfo(Integer gradeLevel);

    MessageDto<GradeValueInfoVo> getGradeValueInfo(Integer gradeLevel);

    MessageDto<Boolean> saveGradeInfo(GradeParam gradeParam);

    MessageDto<Boolean> updateGradeInfo(GradeParam gradeParam);
}


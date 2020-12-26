package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.GradeEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.param.GradeParam;
import com.aperture.community.member.model.vo.GradePermissionInfoVo;
import com.aperture.community.member.model.vo.GradeValueInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Auther: JayV
 * @Date: 2020-8-18 15:28
 * @Description:
 */
@Mapper
public interface UmsGradeConverter {
    UmsGradeConverter INSTANCE = Mappers.getMapper(UmsGradeConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "gradeLevel", target = "gradeLevel"),
            @Mapping(source = "gradeName", target = "gradeName"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "topGrade", target = "topGrade"),
            @Mapping(source = "isCreateCircle", target = "isCreateCircle"),
            @Mapping(source = "isVipDiscount", target = "isVipDiscount"),
            @Mapping(source = "createCircleLimit", target = "createCircleLimit"),
            @Mapping(source = "upgradeValue", target = "upgradeValue"),
            @Mapping(source = "signInGrowthValue", target = "signInGrowthValue"),
            @Mapping(source = "commentGrowthValue", target = "commentGrowthValue"),
            @Mapping(source = "publishGrowthValue", target = "publishGrowthValue"),
            @Mapping(source = "onlineTenValue", target = "onlineTenValue"),
            @Mapping(source = "onlineThirtyValue", target = "onlineThirtyValue"),
            @Mapping(source = "onlineSixtyValue", target = "onlineSixtyValue")

    })
    GradeEntity toGradeEntity(GradeParam gradeParam);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "gradeLevel", target = "gradeLevel"),
            @Mapping(source = "gradeName", target = "gradeName"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "isCreateCircle", target = "isCreateCircle"),
            @Mapping(source = "isVipDiscount", target = "isVipDiscount"),
            @Mapping(source = "createCircleLimit", target = "createCircleLimit")

    })
    GradePermissionInfoVo toGradePermissionInfoVo(GradeEntity gradeEntity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "gradeLevel", target = "gradeLevel"),
            @Mapping(source = "gradeName", target = "gradeName"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "upgradeValue", target = "upgradeValue"),
            @Mapping(source = "signInGrowthValue", target = "signInGrowthValue"),
            @Mapping(source = "commentGrowthValue", target = "commentGrowthValue"),
            @Mapping(source = "publishGrowthValue", target = "publishGrowthValue"),
            @Mapping(source = "onlineTenValue", target = "onlineTenValue"),
            @Mapping(source = "onlineThirtyValue", target = "onlineThirtyValue"),
            @Mapping(source = "onlineSixtyValue", target = "onlineSixtyValue")

    })
    GradeValueInfoVo toGradeValueInfoVo(GradeEntity gradeEntity);
}

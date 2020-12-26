package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.MemberEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.param.MemberParam;
import com.aperture.community.member.model.vo.MemberBaseInfoVo;
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
public interface UmsMemberConverter {
    UmsMemberConverter INSTANCE = Mappers.getMapper(UmsMemberConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "mobile", target = "mobile"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "nickname", target = "nickname"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "birthday", target = "birthday"),
            @Mapping(source = "headUrl", target = "headUrl"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "donut", target = "donut"),
            @Mapping(source = "memberPoint", target = "memberPoint"),
            @Mapping(source = "updateTime", target = "updateTime"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "place", target = "place"),
            @Mapping(source = "isVip", target = "isVip"),
            @Mapping(source = "sign", target = "sign"),
            @Mapping(source = "interest", target = "interest"),
            @Mapping(source = "gradeLevel", target = "gradeLevel"),
            @Mapping(source = "isCertificated", target = "isCertificated"),
            @Mapping(source = "memberStatus", target = "memberStatus"),

    })
    MemberEntity toMemberEntity(MemberParam memberParam);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "mobile", target = "mobile"),
            @Mapping(source = "nickname", target = "nickname"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "headUrl", target = "headUrl"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "donut", target = "donut"),
            @Mapping(source = "isVip", target = "isVip"),
            @Mapping(source = "gradeLevel", target = "gradeLevel"),

    })
    MemberBaseInfoVo toMemberBaseInfoVo(MemberEntity memberEntity);


}

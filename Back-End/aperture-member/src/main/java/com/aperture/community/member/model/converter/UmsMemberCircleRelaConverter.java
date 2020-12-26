package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.MemberCircleRelaEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.param.MemberCircleRelaParam;
import com.aperture.community.member.model.vo.MemberCircleRelaVo;
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
public interface UmsMemberCircleRelaConverter {

    UmsMemberCircleRelaConverter INSTANCE = Mappers.getMapper(UmsMemberCircleRelaConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "memberId", target = "memberId"),
            @Mapping(source = "circleId", target = "circleId"),
            @Mapping(source = "pisition", target = "pisition")

    })
    MemberCircleRelaEntity toMemberCircleRelaEntity(MemberCircleRelaParam memberCircleRelaParam);

    @Mappings({
            @Mapping(source = "pisition", target = "pisition")

    })
    MemberCircleRelaVo toMemberCircleRelaVo(MemberCircleRelaEntity memberCircleRelaEntity);

}

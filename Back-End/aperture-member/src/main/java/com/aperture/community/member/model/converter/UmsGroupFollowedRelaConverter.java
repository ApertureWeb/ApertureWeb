package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.GroupFollowedRelaEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.param.GroupFollowedRelaParam;
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
public interface UmsGroupFollowedRelaConverter {

    UmsGroupFollowedRelaConverter INSTANCE = Mappers.getMapper(UmsGroupFollowedRelaConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "groupId", target = "groupId"),
            @Mapping(source = "followedId", target = "followedId")
    })
    GroupFollowedRelaEntity toGroupFollowedRelaEntity(GroupFollowedRelaParam groupFollowedRelaParam);


}

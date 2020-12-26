package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.FollowGroupRelaEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.param.FollowGroupRelaParam;
import com.aperture.community.member.model.vo.FollowGroupRelaVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Auther: JayV
 * @Date: 2020-8-18 15:28
 * @Description:
 */
@Mapper
public interface UmsFollowGroupRelaConverter {
    UmsFollowGroupRelaConverter INSTANCE = Mappers.getMapper(UmsFollowGroupRelaConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "groupId", target = "groupId"),
            @Mapping(source = "groupName", target = "groupName"),
            @Mapping(target = "memberId", ignore = true),
            @Mapping(target = "followCount", ignore = true)
    })
    FollowGroupRelaEntity toFollowGroupRelaEntity(FollowGroupRelaParam followGroupRelaParam);
    List<FollowGroupRelaEntity> toFollowGroupRelaEntityList(List<FollowGroupRelaEntity> followGroupRelaEntities);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "groupId", target = "groupId"),
            @Mapping(source = "groupName", target = "groupName"),
            @Mapping(source = "followCount", target = "followCount")
    })
    FollowGroupRelaVo toFollowGroupRelaVo(FollowGroupRelaEntity followGroupRelaEntity);
    List<FollowGroupRelaVo> toFollowGroupRelaVoList(List<FollowGroupRelaEntity> followGroupRelaEntities);
}

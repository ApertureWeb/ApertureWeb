package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.vo.CollectionListVo;
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
public interface UmsCollectionConverter {

    UmsCollectionConverter INSTANCE = Mappers.getMapper(UmsCollectionConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "favoratesId", target = "favoratesId"),
            @Mapping(source = "memberId", target = "memberId"),
            @Mapping(target = "collectionDate", ignore = true),
            @Mapping(target = "authorName", ignore = true)

    })
    CollectionEntity toCollectionEntity(CollectionParam collectionParam);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "collectionDate", target = "collectionDate"),
            @Mapping(source = "targetId", target = "targetId")

    })
    CollectionListVo toCollectionListVo(CollectionEntity collectionEntity);
    List<CollectionListVo> toCollectionListVoList(List<CollectionEntity> collectionEntityList);


}

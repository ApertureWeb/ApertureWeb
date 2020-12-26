package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.FavoratesEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.param.FavoratesParam;
import com.aperture.community.member.model.vo.FavoratesVo;
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
public interface UmsFavoratesConverter {

    UmsFavoratesConverter INSTANCE = Mappers.getMapper(UmsFavoratesConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "isPublic", target = "isPublic"),
            @Mapping(source = "collectionCount", target = "collectionCount"),
            @Mapping(target = "memberId", ignore = true)
    })
    FavoratesEntity toFavoratesEntity(FavoratesParam favoratesParam);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "isPublic", target = "isPublic"),
            @Mapping(source = "collectionCount", target = "collectionCount")
    })
    FavoratesVo toFavoratesVo(FavoratesEntity favoratesEntity);
    List<FavoratesVo> toFavoratesVoList(List<FavoratesEntity> favoratesEntity);

}

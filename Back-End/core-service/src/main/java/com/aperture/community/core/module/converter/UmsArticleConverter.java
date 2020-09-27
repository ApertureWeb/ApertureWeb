package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.UmsArticle;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.vo.UmsArticleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author HALOXIAO
 * @since 2020-09-23 19:20
 **/
@Mapper
public interface UmsArticleConverter {

    UmsArticleConverter INSTANCE = Mappers.getMapper(UmsArticleConverter.class);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "circleId", target = "circleId"),
            @Mapping(target = "like", ignore = true),
            @Mapping(target = "coins", ignore = true),
            @Mapping(target = "userUid", ignore = true)
    })
    UmsArticle toUmsArticle(UmsArticleParam umsArticleParam);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "circleId", target = "circleId"),
            @Mapping(source = "like", target = "like"),
            @Mapping(source = "coins", target = "coins"),
            @Mapping(source = "userId", target = "userUid"),
            @Mapping(target = "username", ignore = true),
            @Mapping(target = "circleName", ignore = true)
    })
    UmsArticleVO toUmsArticleVO(UmsArticle umsArticle);


}

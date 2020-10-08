package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsArticleEntity;
import com.aperture.community.core.module.param.CmsArticleParam;
import com.aperture.community.core.module.vo.CmsArticleVO;
import com.aperture.community.core.module.vo.CmsArticleViewVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-09-23 19:20
 **/
@Mapper
public interface CmsArticleConverter {

    CmsArticleConverter INSTANCE = Mappers.getMapper(CmsArticleConverter.class);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "circleId", target = "circleId"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "like", ignore = true),
            @Mapping(target = "coins", ignore = true),
            @Mapping(target = "userUid", ignore = true)
    })
    CmsArticleEntity toUmsArticle(CmsArticleParam cmsArticleParam);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "circleId", target = "circleId"),
            @Mapping(source = "userId", target = "userUid"),
            @Mapping(target = "username", ignore = true),
            @Mapping(target = "circleName", ignore = true)
    })
    CmsArticleVO toUmsArticleVO(CmsArticleEntity cmsArticleEntity);

    List<CmsArticleVO> toUmsArticleVOList(List<CmsArticleEntity> umsArticleEntities);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "circleId", target = "circleId"),
            @Mapping(source = "userId", target = "userUid"),
            @Mapping(source = "description", target = "description"),
            @Mapping(target = "username", ignore = true),
            @Mapping(target = "circleName", ignore = true),
            @Mapping(target = "icon", ignore = true)
    })
    CmsArticleViewVO toUmsArticleViewVO(CmsArticleEntity cmsArticleEntity);

    List<CmsArticleViewVO> toUmsArticleViewVOList(List<CmsArticleEntity> cmsArticleEntityList);
}

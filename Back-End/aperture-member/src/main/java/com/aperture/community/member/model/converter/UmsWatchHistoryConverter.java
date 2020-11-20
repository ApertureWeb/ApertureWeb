package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.WatchHistoryEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.param.WatchHistoryParam;
import com.aperture.community.member.model.vo.ArticleWatchHistoryVo;
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
public interface UmsWatchHistoryConverter {

    UmsWatchHistoryConverter INSTANCE = Mappers.getMapper(UmsWatchHistoryConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "targetName", target = "targetName"),
            @Mapping(source = "targetType", target = "targetType"),
            @Mapping(source = "watchEpisode", target = "watchEpisode"),
            @Mapping(source = "watchMinutes", target = "watchMinutes"),
            @Mapping(target = "memberId", ignore = true)
    })
    WatchHistoryEntity toWatchHistoryEntity(WatchHistoryParam watchHistoryParam);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "targetName", target = "targetName"),
    })
    ArticleWatchHistoryVo toArticleWatchHistoryVo(WatchHistoryEntity watchHistoryEntity);
    ArticleWatchHistoryVo toArticleWatchHistoryVo(WatchHistoryParam watchHistoryParam);
    List<ArticleWatchHistoryVo> toArticleWatchHistoryVoList(List<WatchHistoryEntity> watchHistoryEntityList);



}

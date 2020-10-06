package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsCommentEntity;
import com.aperture.community.core.module.param.CmsCommentParam;
import com.aperture.community.core.module.vo.ChildCommentVO;
import com.aperture.community.core.module.vo.CmsCommentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CmsCommentConverter {

    CmsCommentConverter INSTANCE = Mappers.getMapper(CmsCommentConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "commentId", target = "commentId"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "commentDate", ignore = true),
            @Mapping(target = "like", ignore = true)
    })
    CmsCommentEntity toUmsComment(CmsCommentParam cmsCommentParam);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "commentId", target = "commentId"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "commentDate", target = "commentDate"),
            @Mapping(source = "like", target = "like"),
            @Mapping(target = "username", ignore = true),
            @Mapping(target = "icon", ignore = true),
            @Mapping(target = "childComment", ignore = true)
    })
    CmsCommentVO toUmsCommentVO(CmsCommentEntity comment);


    List<CmsCommentVO> toUmsCommentVOs(List<CmsCommentEntity> umsCommentEntities);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "commentId", target = "commentId"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "commentDate", target = "commentDate"),
            @Mapping(source = "like", target = "like"),
            @Mapping(target = "username", ignore = true),
            @Mapping(target = "icon", ignore = true),
            @Mapping(target = "childComment", ignore = true)
    })
    ChildCommentVO toChildCommentVO(CmsCommentVO cmsCommentVO);

    List<ChildCommentVO> toChildCommentVOs(List<CmsCommentEntity> umsCommentEntities);
}

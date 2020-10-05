package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.UmsCommentEntity;
import com.aperture.community.core.module.param.UmsCommentParam;
import com.aperture.community.core.module.vo.ChildCommentVO;
import com.aperture.community.core.module.vo.UmsCommentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UmsCommentConverter {

    UmsCommentConverter INSTANCE = Mappers.getMapper(UmsCommentConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "replyId", target = "replyId"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "commentDate", ignore = true),
            @Mapping(target = "like", ignore = true)
    })
    UmsCommentEntity toUmsComment(UmsCommentParam umsCommentParam);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "replyId", target = "replyId"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "commentDate", target = "commentDate"),
            @Mapping(source = "like", target = "like"),
            @Mapping(target = "username", ignore = true),
            @Mapping(target = "icon", ignore = true),
            @Mapping(target = "childComment", ignore = true)
    })
    UmsCommentVO toUmsCommentVO(UmsCommentEntity comment);


    List<UmsCommentVO> toUmsCommentVOs(List<UmsCommentEntity> umsCommentEntities);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "replyId", target = "replyId"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "commentDate", target = "commentDate"),
            @Mapping(source = "like", target = "like"),
            @Mapping(target = "username", ignore = true),
            @Mapping(target = "icon", ignore = true),
            @Mapping(target = "childComment", ignore = true)
    })
    ChildCommentVO toChildCommentVO(UmsCommentVO umsCommentVO);

    List<ChildCommentVO> toChildCommentVOs(List<UmsCommentEntity> umsCommentEntities);
}

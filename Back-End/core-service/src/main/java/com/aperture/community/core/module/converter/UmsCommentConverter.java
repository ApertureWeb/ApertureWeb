package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.UmsComment;
import com.aperture.community.core.module.param.UmsCommentParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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
    UmsComment toUmsComment(UmsCommentParam umsCommentParam);


}

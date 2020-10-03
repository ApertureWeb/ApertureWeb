package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.UmsReply;
import com.aperture.community.core.module.param.UmsReplyParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UmsReplyConverter {

    UmsReplyConverter INSTANCE = Mappers.getMapper(UmsReplyConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(source = "commentId", target = "commentId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "status", target = "status"),
            @Mapping(target = "comment_date",ignore = true),
            @Mapping(target = "like",ignore = true)

    })
    UmsReply toUmsReply(UmsReplyParam umsReplyParam);

}

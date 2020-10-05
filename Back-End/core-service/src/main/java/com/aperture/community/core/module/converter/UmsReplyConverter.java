package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.UmsReply;
import com.aperture.community.core.module.param.UmsReplyParam;
import com.aperture.community.core.module.vo.UmsReplyVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-10-04 13:48
 **/
@Mapper
public interface UmsReplyConverter {
    UmsReplyConverter INSTANCE = Mappers.getMapper(UmsReplyConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "targetId", target = "targetId"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(source = "content", target = "content")
    })
    UmsReply toUmsReply(UmsReplyParam umsReplyParam);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "commentDate", target = "commentDate"),
            @Mapping(source = "like", target = "like"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(target = "username", ignore = true),
            @Mapping(target = "icon", ignore = true)

    })
    UmsReplyVO toUmsReplyVO(UmsReply umsReply);

    List<UmsReplyVO> toUmsReplyVOs(List<UmsReply> umsReplies);

}

package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsReplyEntity;
import com.aperture.community.core.module.param.CmsReplyParam;
import com.aperture.community.core.module.vo.CmsReplyVO;
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
public interface CmsReplyConverter {
    CmsReplyConverter INSTANCE = Mappers.getMapper(CmsReplyConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "commentId", target = "commentId"),
            @Mapping(source = "rootId", target = "rootId"),
            @Mapping(source = "content", target = "content")
    })
    CmsReplyEntity toUmsReply(CmsReplyParam cmsReplyParam);


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
    CmsReplyVO toUmsReplyVO(CmsReplyEntity cmsReplyEntity);

    List<CmsReplyVO> toUmsReplyVOs(List<CmsReplyEntity> umsReplies);

}

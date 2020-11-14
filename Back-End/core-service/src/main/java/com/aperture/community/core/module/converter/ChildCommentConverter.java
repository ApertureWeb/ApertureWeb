package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsReplyEntity;
import com.aperture.community.core.module.vo.ChildCommentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-11-14 19:43
 **/
@Mapper
public interface ChildCommentConverter {

    ChildCommentConverter INSTANCE = Mappers.getMapper(ChildCommentConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "like", target = "like"),
            @Mapping(source = "commentDate", target = "commentDate"),
            @Mapping(target = "username", ignore = true)
    })
    ChildCommentVO toChildCommentVO(CmsReplyEntity replyEntity);

    List<ChildCommentVO> toChildCommentVOs(List<CmsReplyEntity> replyEntityList);
}

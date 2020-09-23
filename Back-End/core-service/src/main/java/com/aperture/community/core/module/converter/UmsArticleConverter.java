package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.vo.UmsArticleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author HALOXIAO
 * @since 2020-09-23 19:20
 **/
@Mapper
public interface UmsArticleConverter {

    UmsArticleConverter INSTANCE = Mappers.getMapper(UmsArticleConverter.class);



}

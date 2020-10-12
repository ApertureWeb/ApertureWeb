package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsCircleEntity;
import com.aperture.community.core.module.param.CmsCircleParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsCircleConverter {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "image", target = "image"),
            @Mapping(source = "icon", target = "icon"),
            @Mapping(source = "category_id", target = "category_id")
    })
    CmsCircleEntity toCircleEntity(CmsCircleParam cmsCircleParam);

    CmsCircleConverter INSTANCE = Mappers.getMapper(CmsCircleConverter.class);

}

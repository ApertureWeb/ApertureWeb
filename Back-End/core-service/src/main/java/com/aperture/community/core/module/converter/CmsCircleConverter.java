package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsCircleEntity;
import com.aperture.community.core.module.param.CmsCircleParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsCircleConverter {

    CmsCircleConverter INSTANCE = Mappers.getMapper(CmsCircleConverter.class);

    @Mappings({
            @Mapping(source = "id",target = "id")
    })

    CmsCircleEntity toCircleEntity(CmsCircleParam cmsCircleParam);

}

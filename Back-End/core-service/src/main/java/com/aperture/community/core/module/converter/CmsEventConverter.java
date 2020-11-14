package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsEventEntity;
import com.aperture.community.core.module.vo.EventVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CmsEventConverter {

    CmsEventConverter INSTANCE = Mappers.getMapper(CmsEventConverter.class);


    @Mappings({
            @Mapping(source = "like", target = "like"),
            @Mapping(source = "donut", target = "donut"),
            @Mapping(source = "store", target = "store")
    })
    EventVO toCmsEventEntity(CmsEventEntity eventEntity);



}

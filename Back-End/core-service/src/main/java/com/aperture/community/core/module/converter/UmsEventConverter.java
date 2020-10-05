package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.UmsEventEntity;
import com.aperture.community.core.module.vo.EventVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UmsEventConverter {

    UmsEventConverter INSTANCE = Mappers.getMapper(UmsEventConverter.class);


    @Mappings({
            @Mapping(source = "like", target = "like"),
            @Mapping(source = "donut", target = "donut"),
            @Mapping(source = "store", target = "store")
    })
    EventVO toUmsEventEntity(UmsEventEntity eventEntity);


}

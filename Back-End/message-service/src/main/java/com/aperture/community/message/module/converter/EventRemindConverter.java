package com.aperture.community.message.module.converter;

import com.aperture.community.message.module.MsEventRemindEntity;
import com.aperture.community.message.module.dto.EventRemindDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author HALOXIAO
 * @since 2020-10-11 20:53
 **/
@Mapper
public interface EventRemindConverter {

    EventRemindConverter INSTANCE = Mappers.getMapper(EventRemindConverter.class);

    MsEventRemindEntity toEventRemindEntity(EventRemindDto eventRemindDto);

}

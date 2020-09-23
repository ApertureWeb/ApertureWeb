package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.UmsVideo;
import com.aperture.community.core.module.param.UmsVideoParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

/**
 * @author HALOXIAO
 * @since 2020-09-23 19:09
 **/
@Mapper
public interface UmsVideoConverter {

      UmsVideoConverter INSTANCE = Mappers.getMapper(UmsVideoConverter.class);

      @Mappings({
              @Mapping(source = "id",target = "id"),
              @Mapping(source = "title",target = "title"),
              @Mapping(source = "content",target = "content")

      })
     UmsVideo toUmsVideo(UmsVideoParam videoParam);



}

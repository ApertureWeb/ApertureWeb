package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.UmsTagEntity;
import com.aperture.community.core.module.param.UmsTagParam;
import com.aperture.community.core.module.vo.UmsTagVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UmsTagConverter {

    UmsTagConverter INSTANCE = Mappers.getMapper(UmsTagConverter.class);


    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name", target = "name")
    })
    UmsTagVO toUmsTagVO(UmsTagEntity umsTagEntity);

    List<UmsTagVO> toUmsTagVOList(List<UmsTagEntity> umsTagEntityList);


    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name")
    })
    UmsTagEntity toUmsTag(UmsTagParam umsTagParam);


}

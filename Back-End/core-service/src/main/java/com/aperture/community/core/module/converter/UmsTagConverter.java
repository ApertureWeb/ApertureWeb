package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.UmsTag;
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
    UmsTagVO toUmsTagVO(UmsTag umsTag);

    List<UmsTagVO> toUmsTagVOList(List<UmsTag> umsTagList);


    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name")
    })
    UmsTag toUmsTag(UmsTagParam umsTagParam);


}

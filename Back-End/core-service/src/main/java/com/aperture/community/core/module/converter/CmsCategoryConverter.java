package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsCategoryEntity;
import com.aperture.community.core.module.vo.CmsCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CmsCategoryConverter {

    CmsCategoryConverter INSTANCE = Mappers.getMapper(CmsCategoryConverter.class);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "circleCount", target = "circleCount"),
            @Mapping(source = "icon", target = "icon"),
            @Mapping(target = "childCategoryVO", ignore = true)
    })
    CmsCategoryVO toCmsCategoryVO(CmsCategoryEntity cmsCategoryEntity);
    List<CmsCategoryVO> toCmsCategoryVOs(List<CmsCategoryEntity> cmsCategoryEntities);


}

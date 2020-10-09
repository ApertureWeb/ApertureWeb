package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsCategoryEntity;
import com.aperture.community.core.module.param.CmsCategoryParam;
import com.aperture.community.core.module.vo.ChildCategoryVO;
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

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "circleCount", target = "circleCount"),
            @Mapping(source = "icon", target = "icon")
    })
    ChildCategoryVO toChildCategoryVO(CmsCategoryEntity cmsCategoryEntity);


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "getParentCid", target = "parentCid"),
            @Mapping(source = "level", target = "level"),
            @Mapping(source = "sort", target = "sort"),
            @Mapping(source = "icon", target = "icon"),
            @Mapping(source = "showStatus", target = "showStatus"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "circleCount", ignore = true)
    })
    CmsCategoryEntity toCmsCategoryEntity(CmsCategoryParam param);

}

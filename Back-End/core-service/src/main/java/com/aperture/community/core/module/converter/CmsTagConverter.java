package com.aperture.community.core.module.converter;

import com.aperture.community.core.module.CmsTagEntity;
import com.aperture.community.core.module.param.CmsTagParam;
import com.aperture.community.core.module.vo.CmsTagVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CmsTagConverter {

    CmsTagConverter INSTANCE = Mappers.getMapper(CmsTagConverter.class);


    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name", target = "name")
    })
    CmsTagVO toUmsTagVO(CmsTagEntity cmsTagEntity);

    List<CmsTagVO> toUmsTagVOList(List<CmsTagEntity> cmsTagEntityList);


    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name")
    })
    CmsTagEntity toUmsTag(CmsTagParam cmsTagParam);


}

package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.CounterEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.param.CounterParam;
import com.aperture.community.member.model.vo.CounterVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Auther: JayV
 * @Date: 2020-8-18 15:28
 * @Description:
 */
@Mapper
public interface UmsCounterConverter {

    UmsCounterConverter INSTANCE = Mappers.getMapper(UmsCounterConverter.class);

    @Mappings({
            @Mapping(source = "memberId", target = "memberId"),
            @Mapping(source = "follow", target = "follow"),
            @Mapping(source = "fans", target = "fans"),
            @Mapping(source = "like", target = "like"),
            @Mapping(source = "favorates", target = "favorates"),
            @Mapping(source = "circle", target = "circle"),
            @Mapping(source = "works", target = "works"),
            @Mapping(source = "gradeValue", target = "gradeValue"),

    })
    CounterEntity toCounterEntity(CounterParam counterParam);

    @Mappings({
            @Mapping(source = "follow", target = "follow"),
            @Mapping(source = "fans", target = "fans"),
            @Mapping(source = "like", target = "like"),
            @Mapping(source = "favorates", target = "favorates"),
            @Mapping(source = "circle", target = "circle"),
            @Mapping(source = "works", target = "works"),
            @Mapping(source = "gradeValue", target = "gradeValue"),
    })
    CounterVo toCounterVo(CounterEntity counterEntity);


}

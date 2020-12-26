package com.aperture.community.member.model.converter;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.LoginLogEntity;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.param.LoginLogParam;
import com.aperture.community.member.model.vo.LoginStatusVo;
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
public interface UmsLoginLogConverter {

    UmsLoginLogConverter INSTANCE = Mappers.getMapper(UmsLoginLogConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "memberId", target = "memberId"),
            @Mapping(source = "ip", target = "ip"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "loginType", target = "loginType"),
            @Mapping(source = "onlineTime", target = "onlineTime"),
            @Mapping(source = "registTime", target = "registTime"),
            @Mapping(source = "loginStatus", target = "loginStatus"),
            @Mapping(source = "banTime", target = "banTime")
    })
    LoginLogEntity toLoginLogEntity(LoginLogParam loginLogParam);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "ip", target = "ip"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "loginType", target = "loginType"),
            @Mapping(source = "onlineTime", target = "onlineTime"),
            @Mapping(source = "loginStatus", target = "loginStatus"),
    })
    LoginStatusVo toLoginStatusVo(LoginLogEntity loginLogEntity);

}

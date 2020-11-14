package com.aperture.community.core.common;

import com.alibaba.fastjson.JSON;
import com.aperture.community.core.module.identify.UserInfo;

public class TokenResolver {


    public static UserInfo resolveToken(String json) {
        return JSON.parseObject(json, UserInfo.class);
    }


}

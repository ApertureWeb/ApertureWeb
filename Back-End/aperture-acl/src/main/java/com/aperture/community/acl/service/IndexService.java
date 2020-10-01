package com.aperture.community.acl.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;

/**
 * @Auther: JayV
 * @Date: 2020-8-18 15:28
 * @Description:
 */
public interface IndexService  {

    Map<String,Object> getUserInfo(String username);

    List<JSONObject> getMenu(String username);
}

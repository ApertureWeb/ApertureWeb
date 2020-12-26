/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.aperture.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", RESULT_BEAN_STATUS_CODE.SUCCESS);
        put("msg", "success");
    }

    public <T> T getData(String key, TypeReference<T> typeReference) {
        Object data = get(key);
        String s = JSON.toJSONString(data);
        T t = JSON.parseObject(s, typeReference);
        return t;
    }

    public static R error() {
        return error(RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(RESULT_BEAN_STATUS_CODE.UNKNOWN_EXCEPTION, msg);
    }

    public static R error(RESULT_BEAN_STATUS_CODE code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

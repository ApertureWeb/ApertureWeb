package com.aperture.community.message.component.nacos.common.http.param;

import com.aperture.community.message.component.nacos.common.constant.HttpHeaderConsts;
import io.vertx.core.MultiMap;

/**
 * Http header , Multimap type
 *
 * @author HALOXIAO
 * @since 2020-10-30 20:53
 **/
public class Header {

    public static final Header EMPTY = Header.newInstance();
    private final MultiMap multiMap;

    private Header() {
        multiMap = MultiMap.caseInsensitiveMultiMap();
        multiMap.add(HttpHeaderConsts.CONTENT_TYPE.getValue(), MediaType.APPLICATION_JSON.getValue());
        multiMap.add(HttpHeaderConsts.ACCEPT_CHARSET.getValue(), "UTF-8");
        multiMap.add(HttpHeaderConsts.ACCEPT_ENCODING.getValue(), "gzip");
        multiMap.add(HttpHeaderConsts.CONTENT_ENCODING.getValue(), "gzip");
    }

    public static Header newInstance() {
        return new Header();
    }


    public Header addParam(String key, String value) {
        multiMap.add(key, value);
        return this;
    }

    public MultiMap getMultiMap() {
        return multiMap;
    }
}

package com.aperture.community.core.common.map.redis;

public enum RedisContentMap {

//    --------------------------------Event--------------------------------
    /**
     * Event的Redis命名空间
     * 它们的组合为：content:${contentId}:like/store:donut；
     * 相比一般content:like/store/donut:${contentId}的方式,能表达出更多信息.
     * 数据结构为Value
     *
     * */
    PRE_CONTENT_VALUE_NAMESPACE("content:"),
    SUF_CONTENT_LIKE(":like"),
    SUF_CONTENT_STORE(":store"),
    SUF_CONTENT_DONUT(":donut");
//    --------------------------------Event--------------------------------

    String value;

    RedisContentMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}

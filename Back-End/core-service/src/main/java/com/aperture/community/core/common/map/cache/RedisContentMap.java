package com.aperture.community.core.common.map.cache;

public enum RedisContentMap {

//    --------------------------------Event--------------------------------
    /**
     * 存储格式为ZSET,Key为ID,Value为数量
     */

    ARTICLE_CONTENT_LIKE("article:content:like"),
    ARTICLE_CONTENT_DONUT("article:content:donut"),
    ARTICLE_CONTENT_STORE("article:content:store"),
    ARTICLE_COMMENT_LIKE("article:comment:like"),
    ARTICLE_REPLY_LIKE("article:reply:like"),
    //    ---------------------------------------------------------------------
    VIDEO_CONTENT_LIKE("video:content:like"),
    VIDEO_CONTENT_DONUT("video:content:donut"),
    VIDEO_CONTENT_STORE("video:content:store"),
    VIDEO_COMMENT_LIKE("video:comment:like"),
    VIDEO_REPLY_LIKE("video:reply:like");
//    --------------------------------Event--------------------------------

    String value;

    RedisContentMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}

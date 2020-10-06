package com.aperture.community.member.common

/**
 * StoreArticleRela对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum StoreArticleRelaMap {

            MEMBER_ID ("member_id"),
            ARTICLE_ID ("article_id"),
        ;
    String value;

    public StoreArticleRelaMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
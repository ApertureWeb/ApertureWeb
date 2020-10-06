package com.aperture.community.member.common

/**
 * StoreVideoRela对象的字段映射
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */

public enum StoreVideoRelaMap {

            MEMBER_ID ("member_id"),
            VIDEO_ID ("video_id"),
        ;
    String value;

    public StoreVideoRelaMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
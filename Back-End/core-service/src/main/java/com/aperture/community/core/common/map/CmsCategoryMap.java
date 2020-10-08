package com.aperture.community.core.common.map;

/**
 * 目录
 *
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-08 22:18:56
 */
public enum CmsCategoryMap {

    ID("id"),
    NAME("name"),
    PARENT_CID("parent_cid"),
    LEVEL("level"),
    SHOW_STATUS("show_status"),
    SORT("sort"),
    ICON("icon"),
    CIRCLE_COUNT("circle_count"),
    STATUS("status");

    String value;

    CmsCategoryMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

package com.aperture.community.member.manager.mq.messageMap;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-15 19:35
 * @Description:
 */
public enum CollectionMessageMap {

    ADD_COLLECTION("addCollection_");

    String value;

    CollectionMessageMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
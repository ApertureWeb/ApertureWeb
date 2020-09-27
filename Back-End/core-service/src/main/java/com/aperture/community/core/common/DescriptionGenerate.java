package com.aperture.community.core.common;


public class DescriptionGenerate {

    public String getDescription(String content) {
        if (content.length() > 100) {
            return null;
        } else {
            return content;
        }
    }


}

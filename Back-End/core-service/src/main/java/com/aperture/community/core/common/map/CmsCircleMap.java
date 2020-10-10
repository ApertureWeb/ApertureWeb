package com.aperture.community.core.common.map;

/**
 * 
 *
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-10 10:39:15
 */
public enum  CmsCircleMap {

    
        /**
        * 
        */
        ID ("id"),
    
        /**
        * 主题，二级分类id
        */
        CATEGORY_ID ("category_id"),
    
        /**
        * 圈名
        */
        NAME ("name"),
    
        /**
        * （存疑）公开状态，0：未公开   1：已公开
        */
        IS_PUBLIC ("is_public"),
    
        /**
        * 置顶状态，0：置顶   1：未置顶
        */
        IS_TOP ("is_top"),
    
        /**
        * 审核状态，0：未审核   1：审核通过
        */
        STATUS ("status"),
    
        /**
        * 热度

        */
        HOT ("hot"),
    
        /**
        * 会员数
        */
        MENBER_NUM ("menber_num"),
    
        /**
        * 帖子数
        */
        ARTICLE_NUM ("article_num"),
    
        /**
        * 视频数
        */
        VIDEO_NUM ("video_num"),
    
        /**
        * 
        */
        URL ("url"),
    
        /**
        * 封面url
        */
        IMAGE ("image"),
    
        /**
        * 图标url
        */
        ICON ("icon"),
    
        /**
        * 创建时间

        */
        GMT_CREATE ("gmt_create"),
        ;

    String value;

    CmsCircleMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }

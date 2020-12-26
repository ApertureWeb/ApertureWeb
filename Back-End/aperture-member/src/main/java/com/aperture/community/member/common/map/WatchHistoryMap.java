package com.aperture.community.member.common.map;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
public enum  WatchHistoryMap {

    
        /**
        * 观看历史id
        */
        ID ("id"),
    
        /**
        * 观看目标的id
        */
        TARGET_ID ("target_id"),
    
        /**
        * 观看目标的名称
        */
        TARGET_NAME ("target_name"),
    
        /**
        * 目标的类型  0视频  1帖子  2文章
        */
        TARGET_TYPE ("target_type"),
    
        /**
        * 看到哪一集
        */
        WATCH_EPISODE ("watch_episode"),
    
        /**
        * 看了几分钟
        */
        WATCH_MINUTES ("watch_minutes"),
    
        /**
        * 用户id
        */
        MEMBER_ID ("member_id"),
        ;

    String value;

    WatchHistoryMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }

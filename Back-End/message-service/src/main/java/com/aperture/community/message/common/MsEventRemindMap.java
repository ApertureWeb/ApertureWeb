package com.aperture.community.message.common;

/**
 * 
 *
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-11 17:32:34
 */
public enum  MsEventRemindMap {

    
        /**
        * 消息ID
        */
        EVENT_REMIND_ID ("event_remind_id"),
    
        /**
        * 行为类型
        */
        ACTION ("action"),
    
        /**
        * 事件源
        */
        SOURCE_ID ("source_id"),
    
        /**
        * 行为地址
        */
        URL ("url"),
    
        /**
        * 是否已读
        */
        STATE ("state"),
    
        /**
        * 接收者
        */
        RECEIVER_ID ("receiver_id"),
    
        /**
        * 行为发起人
        */
        SENDER_ID ("sender_id"),
    
        /**
        * 事件源的内容（文章/视频就为标题，评论就是省略（前20个字左右））
        */
        SOURCE_CONTENT ("source_content"),
    
        /**
        * 行为发起时间
        */
        REMIND_TIME ("remind_time"),
    
        /**
        * 事件源类型
        */
        SOURCE_TYPE ("source_type"),
        ;

    String value;

    MsEventRemindMap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    }

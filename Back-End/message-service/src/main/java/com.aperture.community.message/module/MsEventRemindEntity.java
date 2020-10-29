package com.aperture.community.message.module;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户Event
 *
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-19 15:41:46
 */
@Data
public class MsEventRemindEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    private Long eventRemindId;


    /**
     * 行为类型
     */
    private Integer action;

    /**
     * 事件源
     */
    private Long sourceId;


    /**
     * 行为地址
     */
    private String url;

    /**
     * 是否已读
     */
    private Integer state;


    /**
     * 接收者
     */
    private Long receiverId;

    /**
     * 行为发起人ID
     */
    private Long senderId;

    /**
     * 行为发起人姓名
     */
    private String senderName;

    /**
     * 事件源的内容（文章/视频就为标题，评论就是省略（前20个字左右））
     */
    private String sourceContent;

    /**
     * 行为发起时间
     */
    private LocalDateTime remindTime;
    /**
     * 事件源类型
     */
    private String sourceType;

}

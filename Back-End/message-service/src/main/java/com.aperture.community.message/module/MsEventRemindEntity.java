package com.aperture.community.message.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-11 17:32:34
 */
@Data
@TableName("ms_event_remind")
@ApiModel(value = "MsEventRemind对象}", description = "comments")
public class MsEventRemindEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableId(value = "event_remind_id", type = IdType.NONE)


    @ApiModelProperty(value = "消息ID")
    private Long eventRemindId;
    /**
     * 行为类型
     */

    @TableField("action")

    @ApiModelProperty(value = "行为类型")
    private String action;
    /**
     * 事件源
     */

    @TableField("source_id")

    @ApiModelProperty(value = "事件源")
    private Long sourceId;

    /**
     * 行为地址
     */
    @TableField("url")

    @ApiModelProperty(value = "行为地址")
    private String url;
    /**
     * 是否已读
     */

    @TableField("state")

    @ApiModelProperty(value = "是否已读")
    private Integer state;
    /**
     * 接收者
     */

    @TableField("receiver_id")

    @ApiModelProperty(value = "接收者")
    private Long receiverId;

    /**
     * 行为发起人
     */
    @TableField("sender_id")
    @ApiModelProperty(value = "行为发起人")
    private Long senderId;

    @TableField("sender_name")
    @ApiModelProperty(value = "行为发起人姓名")
    private String senderName;

    /**
     * 事件源的内容（文章/视频就为标题，评论就是省略（前20个字左右））
     */
    @TableField("source_content")
    @ApiModelProperty(value = "事件源的内容（文章/视频就为标题，评论就是省略（前20个字左右））")
    private String sourceContent;

    /**
     * 行为发起时间
     */
    @TableField("remind_time")
    @ApiModelProperty(value = "行为发起时间")
    private Date remindTime;

    /**
     * 事件源类型
     */
    @TableField("source_type")
    @ApiModelProperty(value = "事件源类型")
    private String sourceType;

}

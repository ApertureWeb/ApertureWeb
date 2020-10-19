package com.aperture.community.message.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户Event
 *
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-19 15:41:46
 */
@Data
@ApiModel(value = "MsEventRemind对象}", description = "comments")
public class MsEventRemindEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */


    @ApiModelProperty(value = "消息ID")
    private Long eventRemindId;
    /**
     * 行为类型
     */


    @ApiModelProperty(value = "行为类型")
    private Integer action;
    /**
     * 事件源
     */


    @ApiModelProperty(value = "事件源")
    private Long sourceId;
    /**
     * 行为地址
     */


    @ApiModelProperty(value = "行为地址")
    private String url;
    /**
     * 是否已读
     */

    @ApiModelProperty(value = "是否已读")
    private Integer state;
    /**
     * 接收者
     */

    @ApiModelProperty(value = "接收者")
    private Long receiverId;
    /**
     * 行为发起人ID
     */


    @ApiModelProperty(value = "行为发起人ID ")
    private Long senderId;
    /**
     * 行为发起人姓名
     */


    @ApiModelProperty(value = "行为发起人姓名")
    private String senderName;
    /**
     * 事件源的内容（文章/视频就为标题，评论就是省略（前20个字左右））
     */


    @ApiModelProperty(value = "事件源的内容（文章/视频就为标题，评论就是省略（前20个字左右））")
    private String sourceContent;
    /**
     * 行为发起时间
     */


    @ApiModelProperty(value = "行为发起时间")
    private Date remindTime;
    /**
     * 事件源类型
     */


    @ApiModelProperty(value = "事件源类型")
    private String sourceType;

}

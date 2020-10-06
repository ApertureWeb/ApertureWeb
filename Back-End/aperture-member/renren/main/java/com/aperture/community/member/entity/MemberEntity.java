package com.aperture.community.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
    import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Data;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:38:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_member" )
@ApiModel(value = "Member映射对象", description = "" )
public class MemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */

                @ApiModelProperty(value = "" )
                                    @TableId(value = "id", type = IdType.NONE)
                        
        private Integer id;
            /**
         * 用户名
         */

                @ApiModelProperty(value = "用户名" )
                                                @TableField(value = "username" )
            
        private String username;
            /**
         * 手机号
         */

                @ApiModelProperty(value = "手机号" )
                                                @TableField(value = "telephone" )
            
        private Integer telephone;
            /**
         * 密码
         */

                @ApiModelProperty(value = "密码" )
                                                @TableField(value = "password" )
            
        private String password;
            /**
         * 昵称
         */

                @ApiModelProperty(value = "昵称" )
                                                @TableField(value = "nickname" )
            
        private String nickname;
            /**
         * 性别
         */

                @ApiModelProperty(value = "性别" )
                                                @TableField(value = "gender" )
            
        private String gender;
            /**
         * 生日
         */

                @ApiModelProperty(value = "生日" )
                                                @TableField(value = "birthday" )
            
        private Date birthday;
            /**
         * 头像
         */

                @ApiModelProperty(value = "头像" )
                                                @TableField(value = "header" )
            
        private String header;
            /**
         * 邮箱
         */

                @ApiModelProperty(value = "邮箱" )
                                                @TableField(value = "email" )
            
        private String email;
            /**
         * 甜甜圈，类比B站硬币
         */

                @ApiModelProperty(value = "甜甜圈，类比B站硬币" )
                                                @TableField(value = "doughnut" )
            
        private Integer doughnut;
            /**
         * 注册时间
         */

                @ApiModelProperty(value = "注册时间" )
                                                @TableField(value = "start_time" )
            
        private Date startTime;
            /**
         * 会员积分
         */

                @ApiModelProperty(value = "会员积分" )
                                                @TableField(value = "integration" )
            
        private Integer integration;
            /**
         * 信息修改日期
         */

                @ApiModelProperty(value = "信息修改日期" )
                                                @TableField(value = "update_time" )
            
        private Date updateTime;
            /**
         * 在线时长(分钟)
         */

                @ApiModelProperty(value = "在线时长(分钟)" )
                                                @TableField(value = "online" )
            
        private Integer online;
            /**
         * 认证用户为认证信息 普通用户为交友宣言
         */

                @ApiModelProperty(value = "认证用户为认证信息 普通用户为交友宣言" )
                                                @TableField(value = "description" )
            
        private String description;
            /**
         * 所在地
         */

                @ApiModelProperty(value = "所在地" )
                                                @TableField(value = "place" )
            
        private String place;
            /**
         * 个性签名
         */

                @ApiModelProperty(value = "个性签名" )
                                                @TableField(value = "sign" )
            
        private String sign;
            /**
         * 爱好
         */

                @ApiModelProperty(value = "爱好" )
                                                @TableField(value = "intrest" )
            
        private String intrest;
            /**
         * 状态
         */

                @ApiModelProperty(value = "状态" )
                                                @TableField(value = "status" )
            
        private String status;
            /**
         * 等级id
         */

                @ApiModelProperty(value = "等级id" )
                                                @TableField(value = "grade_uid" )
            
        private Integer gradeUid;
            /**
         * 用户所在圈子的标签id
         */

                @ApiModelProperty(value = "用户所在圈子的标签id" )
                                                @TableField(value = "sign_uid" )
            
        private Integer signUid;
            /**
         * 关注数
         */

                @ApiModelProperty(value = "关注数" )
                                                @TableField(value = "follow_count" )
            
        private Integer followCount;
            /**
         * 粉丝数
         */

                @ApiModelProperty(value = "粉丝数" )
                                                @TableField(value = "fans_count" )
            
        private Integer fansCount;
    
}

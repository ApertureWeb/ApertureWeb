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
@TableName("ums_member_login_log" )
@ApiModel(value = "MemberLoginLog映射对象", description = "" )
public class MemberLoginLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 登录日志id
         */

                @ApiModelProperty(value = "登录日志id" )
                                    @TableId(value = "id", type = IdType.NONE)
                        
        private Integer id;
            /**
         * 用户id
         */

                @ApiModelProperty(value = "用户id" )
                                                @TableField(value = "member_id" )
            
        private Integer memberId;
            /**
         * 用户创建时间
         */

                @ApiModelProperty(value = "用户创建时间" )
                                                @TableField(value = "create_time" )
            
        private Date createTime;
            /**
         * 用户登录时的ip
         */

                @ApiModelProperty(value = "用户登录时的ip" )
                                                @TableField(value = "ip" )
            
        private String ip;
            /**
         * 用户登录时所在的城市
         */

                @ApiModelProperty(value = "用户登录时所在的城市" )
                                                @TableField(value = "city" )
            
        private String city;
            /**
         * 登录类型[1-web，2-app]
         */

                @ApiModelProperty(value = "登录类型[1-web，2-app]" )
                                                @TableField(value = "login_type" )
            
        private Integer loginType;
    
}

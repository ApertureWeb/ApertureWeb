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
@TableName("ums_member_grade" )
@ApiModel(value = "MemberGrade映射对象", description = "" )
public class MemberGradeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 等级id
         */

                @ApiModelProperty(value = "等级id" )
                                    @TableId(value = "id", type = IdType.NONE)
                        
        private Integer id;
            /**
         * 等级名称
         */

                @ApiModelProperty(value = "等级名称" )
                                                @TableField(value = "name" )
            
        private String name;
            /**
         * 是否为默认等级
         */

                @ApiModelProperty(value = "是否为默认等级" )
                                                @TableField(value = "is_default_grade" )
            
        private Integer isDefaultGrade;
            /**
         * 当前等级升级需要的经验
         */

                @ApiModelProperty(value = "当前等级升级需要的经验" )
                                                @TableField(value = "growth_value" )
            
        private Integer growthValue;
            /**
         * 签到增加的经验
         */

                @ApiModelProperty(value = "签到增加的经验" )
                                                @TableField(value = "sign_in_growth_value" )
            
        private Integer signInGrowthValue;
            /**
         * 发布作品增加的经验
         */

                @ApiModelProperty(value = "发布作品增加的经验" )
                                                @TableField(value = "publish_growth_value" )
            
        private Integer publishGrowthValue;
            /**
         * 评论增加的经验
         */

                @ApiModelProperty(value = "评论增加的经验" )
                                                @TableField(value = "comment_growth_value" )
            
        private Integer commentGrowthValue;
            /**
         * 是否有会员价格优惠特权
         */

                @ApiModelProperty(value = "是否有会员价格优惠特权" )
                                                @TableField(value = "is_vip_discount" )
            
        private Integer isVipDiscount;
            /**
         * 等级描述
         */

                @ApiModelProperty(value = "等级描述" )
                                                @TableField(value = "description" )
            
        private String description;
    
}

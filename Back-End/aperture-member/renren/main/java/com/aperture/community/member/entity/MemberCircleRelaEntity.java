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
@TableName("ums_member_circle_rela" )
@ApiModel(value = "MemberCircleRela映射对象", description = "" )
public class MemberCircleRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */

                @ApiModelProperty(value = "" )
                                    @TableId(value = "id", type = IdType.NONE)
                        
        private Integer id;
            /**
         * 用户id
         */

                @ApiModelProperty(value = "用户id" )
                                                @TableField(value = "member_id" )
            
        private Integer memberId;
            /**
         * 圈子id
         */

                @ApiModelProperty(value = "圈子id" )
                                                @TableField(value = "circle_id" )
            
        private Integer circleId;
            /**
         * 在对应圈子里的位置
         */

                @ApiModelProperty(value = "在对应圈子里的位置" )
                                                @TableField(value = "position" )
            
        private String position;
            /**
         * 创建圈子审核状态(1：通过  0：不通过)
         */

                @ApiModelProperty(value = "创建圈子审核状态(1：通过  0：不通过)" )
                                                @TableField(value = "status" )
            
        private Integer status;
    
}

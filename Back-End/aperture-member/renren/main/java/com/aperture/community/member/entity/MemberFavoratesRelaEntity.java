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
@TableName("ums_member_favorates_rela" )
@ApiModel(value = "MemberFavoratesRela映射对象", description = "" )
public class MemberFavoratesRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 用户id
         */

                @ApiModelProperty(value = "用户id" )
                                    @TableId(value = "member_id", type = IdType.NONE)
                        
        private Integer memberId;
            /**
         * 收藏夹id
         */

                @ApiModelProperty(value = "收藏夹id" )
                                                @TableField(value = "favorates_id" )
            
        private Integer favoratesId;
    
}

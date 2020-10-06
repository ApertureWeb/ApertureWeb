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
@TableName("ums_store_video_rela" )
@ApiModel(value = "StoreVideoRela映射对象", description = "" )
public class StoreVideoRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */

                @ApiModelProperty(value = "" )
                                    @TableId(value = "member_id", type = IdType.NONE)
                        
        private Long memberId;
            /**
         * 
         */

                @ApiModelProperty(value = "" )
                                                @TableField(value = "video_id" )
            
        private Integer videoId;
    
}

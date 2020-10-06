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
@TableName("ums_favorates" )
@ApiModel(value = "Favorates映射对象", description = "" )
public class FavoratesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 收藏夹分类id
         */

                @ApiModelProperty(value = "收藏夹分类id" )
                                    @TableId(value = "id", type = IdType.NONE)
                        
        private Integer id;
            /**
         * 收藏夹名称
         */

                @ApiModelProperty(value = "收藏夹名称" )
                                                @TableField(value = "name" )
            
        private String name;
            /**
         * 收藏夹播放数
         */

                @ApiModelProperty(value = "收藏夹播放数" )
                                                @TableField(value = "play_count" )
            
        private Integer playCount;
            /**
         * 收藏夹里的作品数量
         */

                @ApiModelProperty(value = "收藏夹里的作品数量" )
                                                @TableField(value = "favorates_count" )
            
        private String favoratesCount;
    
}

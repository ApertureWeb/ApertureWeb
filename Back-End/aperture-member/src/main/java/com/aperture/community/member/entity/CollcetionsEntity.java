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
@TableName("ums_collcetions" )
@ApiModel(value = "Collcetions映射对象", description = "" )
public class CollcetionsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 收藏id
         */

                @ApiModelProperty(value = "收藏id" )
                                    @TableId(value = "id", type = IdType.NONE)
                        
        private Integer id;
            /**
         * 收藏名
         */

                @ApiModelProperty(value = "收藏名" )
                                                @TableField(value = "name" )
            
        private String name;
            /**
         * 收藏数量
         */

                @ApiModelProperty(value = "收藏数量" )
                                                @TableField(value = "collection_count" )
            
        private Integer collectionCount;
            /**
         * 收藏日期
         */

                @ApiModelProperty(value = "收藏日期" )
                                                @TableField(value = "collection_date" )
            
        private Date collectionDate;
            /**
         * 播放量
         */

                @ApiModelProperty(value = "播放量" )
                                                @TableField(value = "watch_count" )
            
        private Integer watchCount;
            /**
         * 收藏目标的id
         */

                @ApiModelProperty(value = "收藏目标的id" )
                                                @TableField(value = "target_uid" )
            
        private Integer targetUid;
    
}

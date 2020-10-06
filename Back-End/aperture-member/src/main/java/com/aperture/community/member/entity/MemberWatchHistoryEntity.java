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
@TableName("ums_member_watch_history" )
@ApiModel(value = "MemberWatchHistory映射对象", description = "" )
public class MemberWatchHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 观看历史id
         */

                @ApiModelProperty(value = "观看历史id" )
                                    @TableId(value = "id", type = IdType.NONE)
                        
        private Integer id;
            /**
         * 观看目标的id
         */

                @ApiModelProperty(value = "观看目标的id" )
                                                @TableField(value = "target_id" )
            
        private Integer targetId;
            /**
         * 观看目标的名称
         */

                @ApiModelProperty(value = "观看目标的名称" )
                                                @TableField(value = "target_name" )
            
        private String targetName;
            /**
         * 目标的类型
         */

                @ApiModelProperty(value = "目标的类型" )
                                                @TableField(value = "target_type" )
            
        private Integer targetType;
            /**
         * 观看到哪个时间点
         */

                @ApiModelProperty(value = "观看到哪个时间点" )
                                                @TableField(value = "watch_where" )
            
        private Date watchWhere;
    
}

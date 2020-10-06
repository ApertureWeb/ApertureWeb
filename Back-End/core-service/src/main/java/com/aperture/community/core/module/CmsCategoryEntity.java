package com.aperture.community.core.module;

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
 * @date 2020-10-06 15:46:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_category" )
@ApiModel(value = "Category映射对象", description = "" )
public class CmsCategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 分类id
         */

                @ApiModelProperty(value = "分类id" )
                                    @TableId(value = "id", type = IdType.NONE)
                        
        private Integer id;
            /**
         * 分类名称
         */

                @ApiModelProperty(value = "分类名称" )
                                                @TableField(value = "name" )
            
        private String name;
            /**
         * 父分类id
         */

                @ApiModelProperty(value = "父分类id" )
                                                @TableField(value = "parent_cid" )
            
        private Integer parentCid;
            /**
         * 分类层级
         */

                @ApiModelProperty(value = "分类层级" )
                                                @TableField(value = "level" )
            
        private String level;
            /**
         * 是否显示[0-不显示，1显示]
         */

                @ApiModelProperty(value = "是否显示[0-不显示，1显示]" )
                                                @TableField(value = "show_status" )
            
        private String showStatus;
            /**
         * 排序
         */

                @ApiModelProperty(value = "排序" )
                                                @TableField(value = "sort" )
            
        private String sort;
            /**
         * 图标地址
         */

                @ApiModelProperty(value = "图标地址" )
                                                @TableField(value = "icon" )
            
        private String icon;
            /**
         * 圈子数量
         */

                @ApiModelProperty(value = "圈子数量" )
                                                @TableField(value = "circle_count" )
            
        private String circleCount;
    
}

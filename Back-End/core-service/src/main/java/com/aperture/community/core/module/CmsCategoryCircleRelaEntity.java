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
@TableName("cms_category_circle_rela" )
@ApiModel(value = "CategoryCircleRela映射对象", description = "" )
public class CmsCategoryCircleRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */

                @ApiModelProperty(value = "" )
                                    @TableId(value = "id", type = IdType.NONE)
                        
        private Integer id;
            /**
         * 圈子id
         */

                @ApiModelProperty(value = "圈子id" )
                                                @TableField(value = "circle_id" )
            
        private Integer circleId;
            /**
         * 分类id
         */

                @ApiModelProperty(value = "分类id" )
                                                @TableField(value = "category_id" )
            
        private Integer categoryId;
            /**
         * 圈子名
         */

                @ApiModelProperty(value = "圈子名" )
                                                @TableField(value = "circle_name" )
            
        private String circleName;
            /**
         * 分类名
         */

                @ApiModelProperty(value = "分类名" )
                                                @TableField(value = "category_name" )
            
        private String categoryName;
    
}

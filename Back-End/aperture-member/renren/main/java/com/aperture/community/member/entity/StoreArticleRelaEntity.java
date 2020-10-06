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
@TableName("ums_store_article_rela" )
@ApiModel(value = "StoreArticleRela映射对象", description = "" )
public class StoreArticleRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */

                @ApiModelProperty(value = "" )
                                    @TableId(value = "member_id", type = IdType.NONE)
                        
        private Long memberId;
            /**
         * 文章或帖子id
         */

                @ApiModelProperty(value = "文章或帖子id" )
                                                @TableField(value = "article_id" )
            
        private Integer articleId;
    
}

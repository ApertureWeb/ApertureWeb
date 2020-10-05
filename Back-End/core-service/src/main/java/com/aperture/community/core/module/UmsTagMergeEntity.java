package com.aperture.community.core.module;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * tag和内容的关联
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-24
 */
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_tag_merge")
@ApiModel(value="UmsTagMerge对象", description="tag和内容的关联")
public class UmsTagMergeEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "内容（video/article）的id")
    @TableField("content_id")
    private Long contentId;

    @ApiModelProperty(value = "tag的id")
    @TableField("tag_id")
    private Long tagId;


}

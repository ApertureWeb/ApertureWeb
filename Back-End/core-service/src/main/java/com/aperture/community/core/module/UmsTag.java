package com.aperture.community.core.module;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 标签用来标记视频、帖子
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_tag")
@ApiModel(value="UmsTag对象", description="标签用来标记视频、帖子")
public class UmsTag implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "标签名")
    @TableField("name")
    private String name;


}

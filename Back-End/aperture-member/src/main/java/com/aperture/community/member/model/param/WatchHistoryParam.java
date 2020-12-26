package com.aperture.community.member.model.param;

import com.aperture.community.member.model.validation.ValidationGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Null;


/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@EqualsAndHashCode(callSuper = false)
@Data
public  class  WatchHistoryParam{

    /**
     * 观看历史id
     */
    @Null(groups = ValidationGroup.addGroup.class)
    private Long id;
    /**
     * 观看目标的id
     */
    private Long targetId;
    /**
     * 观看目标的名称
     */
    @Null(groups = ValidationGroup.updateGroup.class)
    private String targetName;
    /**
     * 目标的类型  0视频  1帖子  2文章
     */
    @Null(groups = ValidationGroup.updateGroup.class)
    private Integer targetType;
    /**
     * 看到哪一集
     */
    private Integer watchEpisode;
    /**
     * 看了几分钟
     */
    private Integer watchMinutes;

}
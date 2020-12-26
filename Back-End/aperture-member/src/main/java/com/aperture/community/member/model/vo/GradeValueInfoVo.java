package com.aperture.community.member.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-18 22:35
 * @Description:
 */
@Data
public class GradeValueInfoVo {

    private Long id;

    private Integer gradeLevel;

    private String gradeName;

    private String description;

    private Integer upgradeValue;

    private Integer signInGrowthValue;

    private Integer commentGrowthValue;

    private Integer publishGrowthValue;

    private Integer onlineTenValue;

    private Integer onlineThirtyValue;

    private Integer onlineSixtyValue;

}
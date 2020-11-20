package com.aperture.community.member.model.vo;

import lombok.Data;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-18 22:34
 * @Description:
 */
@Data
public class GradePermissionInfoVo {

    private Long id;

    private Integer gradeLevel;

    private String gradeName;

    private String description;

    private Integer isCreateCircle;

    private Integer isVipDiscount;

    private Integer createCircleLimit;

}
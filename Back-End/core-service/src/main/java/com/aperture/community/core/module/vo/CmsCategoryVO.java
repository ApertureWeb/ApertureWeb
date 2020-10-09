package com.aperture.community.core.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class CmsCategoryVO {

    private Long id;
    private String name;
    private Integer circleCount;
    private String icon;
    private List<ChildCategoryVO> childCategoryVO;

}

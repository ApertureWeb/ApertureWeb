package com.aperture.community.member.model.vo;

import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-17 12:47
 * @Description:
 */
@Data
public class FavoratesVo {

    private Long id;

    private String name;

    private String description;

    private Integer isPublic;

    private Integer collectionCount;

}
package com.aperture.community.core.module.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


/**
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-08 22:18:56
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CmsCategoryParam {


    private Long id;

    @Length(min = 1, max = 10)
    private String name;

    private Long parentCId;

    private Integer level;

    private Integer sort;

    private String icon;

    private Integer showStatus;


}
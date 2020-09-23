package com.aperture.community.core.module.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-09-23 17:20
 **/
@Getter
@Setter
@ToString
public class UmsVideoParam {
    private Integer id;
    private String title;
    private String content;
    private Integer circle;
    private List<Integer> tags;
}

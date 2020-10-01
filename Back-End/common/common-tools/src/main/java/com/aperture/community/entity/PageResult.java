package com.aperture.community.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Auther: JayV
 * @Date: 2020-9-7 20:33
 * @Description:
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PageResult<T> {
    private Long total;
    private List<T> rows;



}
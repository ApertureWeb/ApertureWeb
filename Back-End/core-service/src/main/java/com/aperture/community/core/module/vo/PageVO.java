package com.aperture.community.core.module.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@AllArgsConstructor
@Data
public class PageVO<T> {
    private Long total;
    private Collection<T> list;
}

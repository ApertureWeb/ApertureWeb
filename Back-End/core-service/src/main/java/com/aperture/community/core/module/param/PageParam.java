package com.aperture.community.core.module.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

/**
 * @author HALOXIAO
 * @since 2020-09-23 17:20
 **/
@Getter
@Setter
public class PageParam {
    @Range(min = 1)
    private Integer page;

    @Range(min = 1, max = 10)
    private Integer size;

    public PageParam(@Range(min = 1) Integer page, @Range(min = 1, max = 10) Integer size) {
        this.page = page;
        this.size = size;
    }


}

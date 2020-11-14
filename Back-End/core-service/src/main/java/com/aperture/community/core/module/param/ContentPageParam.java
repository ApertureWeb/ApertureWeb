package com.aperture.community.core.module.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author HALOXIAO
 * @since 2020-11-14 20:40
 **/
@Getter
@Setter
public class ContentPageParam extends PageParam {

    @NotNull
    private boolean heart;

    private Long circleId;

    private Long categoryId;


    public ContentPageParam(@Range(min = 1) Integer page, @Range(min = 1, max = 10) Integer size,boolean heart,Long circleId,Long categoryId) {
        super(page, size);
        this.heart = heart;
        this.circleId = circleId;
        this.categoryId = categoryId;
    }
}

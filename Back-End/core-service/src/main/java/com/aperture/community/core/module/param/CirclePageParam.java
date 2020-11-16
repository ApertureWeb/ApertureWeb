package com.aperture.community.core.module.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;


/**
 * @author HALOXIAO
 * @since 2020-09-27 19:01
 **/
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
public class CirclePageParam extends PageParam {
    private Long circleId;
    public CirclePageParam(@Range(min = 1) Integer page, @Range(min = 1, max = 10) Integer size, Long circleId) {
        super(page, size);
        this.circleId = circleId;
    }


}

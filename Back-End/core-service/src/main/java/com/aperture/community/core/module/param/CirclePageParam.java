package com.aperture.community.core.module.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author HALOXIAO
 * @since 2020-09-27 19:01
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CirclePageParam extends PageParam {
    private Long circleId;
}

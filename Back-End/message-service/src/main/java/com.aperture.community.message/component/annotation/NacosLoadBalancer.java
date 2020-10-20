package com.aperture.community.message.component.annotation;

import java.lang.annotation.*;


/**
 * tag annotation
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NacosLoadBalancer {

}

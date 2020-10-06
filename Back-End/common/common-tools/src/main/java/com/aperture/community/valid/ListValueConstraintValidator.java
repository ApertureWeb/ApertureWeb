package com.aperture.community.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: JayV
 * @Date: 2020-10-5 19:30
 * @Description: 自定义校验器
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private Set<Integer> set = new HashSet<>();

    /**
     * 校验条件
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        // 获取到@ListValue注解里的vals
        int[] vals = constraintAnnotation.vals();
        for(int val : vals) {
            set.add(val);  // 设置校验条件
        }
    }

    /**
     * 判断是否校验成功，
     * @param value：需要校验的值
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);  // 判断是否包含在条件里
    }

}
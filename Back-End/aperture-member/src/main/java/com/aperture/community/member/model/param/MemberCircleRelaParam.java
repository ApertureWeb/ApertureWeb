package com.aperture.community.member.model.param;

import com.aperture.community.member.model.validation.ValidationGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Null;

/**
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@EqualsAndHashCode(callSuper = false)
@Data
public  class  MemberCircleRelaParam{

    @Null(groups = {ValidationGroup.addGroup.class})
    private Long id;

    @Null(groups = {ValidationGroup.addGroup.class})
    private Long circleId;

    private Integer position;

}
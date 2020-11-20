package com.aperture.community.member.model.vo;

import lombok.Data;


/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-17 18:56
 * @Description:
 */
@Data
public class LoginStatusVo {

    private Long id;

    private String ip;

    private String city;

    private Integer loginType;

    private Integer onlineTime;

    private Integer loginStatus;

}
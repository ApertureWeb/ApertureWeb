package com.aperture.community.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: JayV
 * @Date: 2020-10-16 14:50
 * @Description:
 */
@Data
public class LoginLogVo {

    /**
     * 登录日志id
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 用户登录时的ip
     */
    private String ip;
    /**
     * 用户登录时所在的城市
     */
    private String city;
    /**
     * 登录类型[1-web，2-app]
     */
    private Integer loginType;
    // 0：已下线  1：在上线
    private Integer loginStatus;

}
package com.aperture.community.member.vo.rspVo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Auther: JayV
 * @Date: 2020-10-28 16:41
 * @Description:
 */
@Data
public class MemberBaseInfoRespVo {

    /**
     * 用户id
     */
    @TableId
    private Long id;
    /**
     * 昵称
     */
    @NotNull
    private String nickname;
    /**
     * 性别[0：男  1：女]
     */
    private Integer gender;
    /**
     * 头像url
     */
    private String headUrl;
    /**
     * 是否开通了会员
     */
    private Integer isVip;

    /**
     * 等级
     */
    private Integer gradeLevel;
}
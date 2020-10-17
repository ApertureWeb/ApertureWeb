package com.aperture.community.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Auther: JayV
 * @Date: 2020-10-16 19:02
 * @Description:
 */
public class MemberUpdateVo {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private Integer telephone;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别[0：男  1：女]
     */
    private Integer gender;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 头像url
     */
    private String headUrl;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 甜甜圈，类比B站硬币
     */
    private Integer donut;
    /**
     * 会员积分
     */
    private Integer memberPoint;
    /**
     * 信息修改日期
     */
    private Date updateTime;
    /**
     * 认证信息/交由宣言
     */
    private String description;
    /**
     * 所在地
     */
    private String place;
    /**
     * 是否开通了会员
     */
    private Integer isVip;
    /**
     * 个性签名
     */
    private String sign;
    /**
     * 爱好
     */
    private String interest;
    /**
     * -2：封号中  -1：已注销  0：正常
     */
    private Integer memberStatus;
    /**
     * 等级
     */
    private Integer gradeLevel;
    /**
     * 等级id
     */
    private Long gradeUid;
    /**
     * 关注数
     */
    private Integer followCount;
    /**
     * 粉丝数
     */
    private Integer fansCount;
    /**
     * 是否实名认证   0：未认证  1：已认证
     */
    private Integer isCertificated;

}
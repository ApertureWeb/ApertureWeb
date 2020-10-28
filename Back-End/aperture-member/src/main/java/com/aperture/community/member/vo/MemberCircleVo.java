package com.aperture.community.member.vo;

import lombok.Data;

/**
 * @Auther: JayV
 * @Date: 2020-10-16 15:29
 * @Description:
 */
@Data
public class MemberCircleVo {
    /**
     * 会员id
     */
    private Long memebrId;
    /**
     * 圈子id
     */
    private Long circleId;

    private String circleName;

}
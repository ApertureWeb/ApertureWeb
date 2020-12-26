package com.aperture.community.member.model.vo;

import lombok.Data;

/**
 * @Auther: JayV
 * @Email: javajayv@gmail.com
 * @Date: 2020-11-18 17:20
 * @Description:
 */
@Data
public class FollowGroupRelaVo {

    private Long id;

    private String groupName;

    private Long groupId;

    private Integer follow_count;

}
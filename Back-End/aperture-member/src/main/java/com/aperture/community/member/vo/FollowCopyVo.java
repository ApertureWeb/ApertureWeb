package com.aperture.community.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Auther: JayV
 * @Date: 2020-10-16 14:04
 * @Description:
 */
@Data
public class FollowCopyVo {

    /**
     * 被关注的用户id
     */
    private Long followedId;

    /**
     * 所属分组id
     */
    private Long groupId;


}
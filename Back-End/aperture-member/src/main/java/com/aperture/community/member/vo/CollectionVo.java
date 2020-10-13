package com.aperture.community.member.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: JayV
 * @Date: 2020-10-11 21:09
 * @Description:
 */
@Data
public class CollectionVo {


    /**
     * 收藏名
     */
    private String name;

    /**
     * 收藏目标的id
     */
    private Long targetId;
    /**
     * 所属收藏夹id
     */
    private Long favoratesId;

}
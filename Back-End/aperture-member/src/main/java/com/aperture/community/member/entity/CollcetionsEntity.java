package com.aperture.community.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-09 13:01:14
 */
@Data
@TableName("ums_collcetions")
public class CollcetionsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 收藏id
     */
    @TableId
    private Integer id;
    /**
     * 收藏名
     */
    private String name;
    /**
     * 收藏数量
     */
    private Integer collectionCount;
    /**
     * 收藏日期
     */
    private Date collectionDate;
    /**
     * 播放量
     */
    private Integer watchCount;
    /**
     * 收藏目标的id
     */
    private Integer targetUid;

}

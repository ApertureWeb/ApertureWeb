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
@TableName("ums_collections_favorates_rela")
public class CollectionsFavoratesRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer favoratesId;
    /**
     *
     */
    private Integer collectionId;

}

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
@TableName("ums_store_article_rela")
public class StoreArticleRelaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long memberId;
    /**
     * 文章或帖子id
     */
    private Integer articleId;

}

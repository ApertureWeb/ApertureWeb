package com.aperture.community.core.manager;

import com.aperture.community.core.dao.UmsArticleMapper;
import com.aperture.community.core.module.UmsArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-10-04 18:36
 **/
@Service
public class ArticleManager {

    private UmsArticleMapper umsArticleMapper;


    @Autowired
    public ArticleManager(UmsArticleMapper umsArticleMapper) {
        this.umsArticleMapper = umsArticleMapper;
    }

    public UmsArticle getArticle() {
        return null;
    }


}

package com.aperture.community.core.manager;

import com.aperture.community.core.dao.CmsArticleMapper;
import com.aperture.community.core.module.CmsArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HALOXIAO
 * @since 2020-10-04 18:36
 **/
@Service
public class ArticleManager {

    private CmsArticleMapper cmsArticleMapper;


    @Autowired
    public ArticleManager(CmsArticleMapper cmsArticleMapper) {
        this.cmsArticleMapper = cmsArticleMapper;
    }

    public CmsArticleEntity getArticle() {
        return null;
    }


}

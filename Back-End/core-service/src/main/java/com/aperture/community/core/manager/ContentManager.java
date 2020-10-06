package com.aperture.community.core.manager;

import com.aperture.community.core.dao.CmsArticleMapper;
import com.aperture.community.core.dao.CmsVideoMapper;
import org.springframework.stereotype.Service;

@Service
public class ContentManager {

    private CmsArticleMapper cmsArticleMapper;
    private CmsVideoMapper cmsVideoMapper;
    private EventManager eventManager;

    public ContentManager(CmsArticleMapper cmsArticleMapper, CmsVideoMapper cmsVideoMapper) {
        this.cmsArticleMapper = cmsArticleMapper;
        this.cmsVideoMapper = cmsVideoMapper;
    }


    public CmsArticleMapper getUmsArticleMapper() {
        return cmsArticleMapper;
    }

    public CmsVideoMapper getUmsVideoMapper() {
        return cmsVideoMapper;
    }



}

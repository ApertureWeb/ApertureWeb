package com.aperture.community.core.manager;

import com.aperture.community.core.dao.UmsArticleMapper;
import com.aperture.community.core.dao.UmsVideoMapper;
import org.springframework.stereotype.Service;

@Service
public class ContentManager {

    private UmsArticleMapper umsArticleMapper;
    private UmsVideoMapper umsVideoMapper;
    private EventManager eventManager;

    public ContentManager(UmsArticleMapper umsArticleMapper, UmsVideoMapper umsVideoMapper) {
        this.umsArticleMapper = umsArticleMapper;
        this.umsVideoMapper = umsVideoMapper;
    }


    public UmsArticleMapper getUmsArticleMapper() {
        return umsArticleMapper;
    }

    public UmsVideoMapper getUmsVideoMapper() {
        return umsVideoMapper;
    }



}

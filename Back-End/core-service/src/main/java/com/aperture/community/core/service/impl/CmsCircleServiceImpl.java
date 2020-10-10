package com.aperture.community.core.service.impl;

import com.aperture.community.core.dao.CmsCircleMapper;
import com.aperture.community.core.service.CmsCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsCircleServiceImpl implements CmsCircleService {

    private CmsCircleMapper cmsCircleMapper;

    @Autowired
    public CmsCircleServiceImpl(CmsCircleMapper cmsCircleMapper) {
        this.cmsCircleMapper = cmsCircleMapper;
    }

}
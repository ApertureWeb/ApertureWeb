package com.aperture.community.core.service;

import com.aperture.community.core.module.param.CirclePageParam;
import com.aperture.community.core.module.param.CmsArticleParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsArticleVO;
import com.aperture.community.core.module.vo.CmsArticleViewVO;

public interface CmsArticleService {

    CmsArticleVO select(Long id);

    boolean delete(Long id);

    PageVO<CmsArticleViewVO> listPage(CirclePageParam circlePageParam);

    boolean update(CmsArticleParam cmsArticleParam);

    Long save(CmsArticleParam cmsArticleParam) throws Exception;


}
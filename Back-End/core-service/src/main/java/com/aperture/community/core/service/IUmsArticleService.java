package com.aperture.community.core.service;

import com.aperture.community.core.module.param.CirclePageParam;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsArticleVO;

public interface IUmsArticleService {

    UmsArticleVO select(UmsArticleParam umsArticleParam);

    boolean delete(Long id);

    PageVO<UmsArticleVO> listPage(CirclePageParam circlePageParam);

    boolean update(UmsArticleParam umsArticleParam);

    Long save(UmsArticleParam umsArticleParam) throws Exception;


}
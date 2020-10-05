package com.aperture.community.core.service;

import com.aperture.community.core.module.param.CirclePageParam;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsArticleVO;
import com.aperture.community.core.module.vo.UmsArticleViewVO;

public interface IUmsArticleService {

    UmsArticleVO select(Long id);

    boolean delete(Long id);

    PageVO<UmsArticleViewVO> listPage(CirclePageParam circlePageParam);

    boolean update(UmsArticleParam umsArticleParam);

    Long save(UmsArticleParam umsArticleParam) throws Exception;


}
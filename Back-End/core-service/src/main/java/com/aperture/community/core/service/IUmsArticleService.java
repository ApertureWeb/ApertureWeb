package com.aperture.community.core.service;

import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.vo.UmsArticleVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUmsArticleService {

    UmsArticleVO select(UmsArticleParam umsArticleParam);

    boolean delete(Long id);

    List<UmsArticleVO> listPage(PageParam pageParam);

    boolean update(UmsArticleParam umsArticleParam);

    boolean save(UmsArticleParam umsArticleParam);


}
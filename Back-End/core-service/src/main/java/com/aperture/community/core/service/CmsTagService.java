package com.aperture.community.core.service;

import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.CmsTagParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsTagVO;
import org.springframework.stereotype.Service;

@Service
public interface CmsTagService {


     boolean delete(Long id);

     PageVO<CmsTagVO> listPage(PageParam pageParam);

     boolean update(CmsTagParam cmsTagParam);

     boolean save(CmsTagParam cmsTagParam);


}
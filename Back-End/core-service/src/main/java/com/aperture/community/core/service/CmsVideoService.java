package com.aperture.community.core.service;

import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.CmsVideoParam;
import com.aperture.community.core.module.vo.CmsVideoVO;

import java.util.List;

public interface CmsVideoService {

 CmsVideoVO select(CmsVideoParam cmsVideoParam);

 boolean delete(Long id);

 List<CmsVideoVO> listPage(PageParam pageParam);

 boolean update(CmsVideoParam cmsVideoParam);

    boolean save(CmsVideoParam cmsVideoParam);


}
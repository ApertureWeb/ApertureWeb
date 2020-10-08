package com.aperture.community.core.service;

import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.CmsTagParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsTagVO;
import org.springframework.stereotype.Service;

@Service
public interface CmsTagService {


     MessageDto<Boolean> delete(Long id, Long contentId);

     PageVO<CmsTagVO> listPage(PageParam pageParam);


     boolean save(CmsTagParam cmsTagParam);


}
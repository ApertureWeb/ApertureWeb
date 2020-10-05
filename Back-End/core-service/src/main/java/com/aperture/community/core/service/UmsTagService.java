package com.aperture.community.core.service;

import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsTagParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsTagVO;
import org.springframework.stereotype.Service;

@Service
public interface UmsTagService {


     boolean delete(Long id);

     PageVO<UmsTagVO> listPage(PageParam pageParam);

     boolean update(UmsTagParam umsTagParam);

     boolean save(UmsTagParam umsTagParam);


}
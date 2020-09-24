package com.aperture.community.core.service;

import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsTagParam;
import com.aperture.community.core.module.vo.UmsTagVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUmsTagService  {

    UmsTagVO select(UmsTagParam umsTagParam);

     boolean delete(Long id);

     List<UmsTagVO> listPage(PageParam pageParam);

     boolean update(UmsTagParam umsTagParam);

     boolean save(UmsTagParam umsTagParam);


}
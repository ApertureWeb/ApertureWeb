package com.aperture.community.core.service;

import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsVideoParam;
import com.aperture.community.core.module.vo.UmsVideoVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UmsVideoService {

 UmsVideoVO select(UmsVideoParam umsVideoParam);

 boolean delete(Long id);

 List<UmsVideoVO> listPage(PageParam pageParam);

 boolean update(UmsVideoParam umsVideoParam);

    boolean save(UmsVideoParam umsVideoParam);


}
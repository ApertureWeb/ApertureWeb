package com.aperture.community.core.service;


import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CmsCategoryParam;
import com.aperture.community.core.module.vo.CmsCategoryVO;

import java.util.List;

/**
 * 目录
 *
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-08 22:18:56
 */
public interface CmsCategoryService {

    MessageDto<Boolean> addCategory(CmsCategoryParam param);

    MessageDto<Boolean> updateCategory(CmsCategoryParam param);

    List<CmsCategoryVO> listPage();

}


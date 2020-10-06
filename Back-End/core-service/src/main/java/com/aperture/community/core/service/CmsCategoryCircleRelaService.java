package com.aperture.community.core.service;

import com.aperture.community.core.module.CmsCategoryCircleRelaEntity;
import com.aperture.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 15:46:44
 */
public interface CmsCategoryCircleRelaService extends IService<CmsCategoryCircleRelaEntity>  {

    PageUtils queryPage(Map<String, Object> params);
}


package com.aperture.community.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.aperture.community.member.entity.CollcetionsEntity;

import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 13:30:06
 */
public interface CollcetionsService extends IService<CollcetionsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


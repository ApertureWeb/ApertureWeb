package com.aperture.community.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.aperture.community.member.entity.StoreArticleRelaEntity;

import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-06 18:33:11
 */
public interface StoreArticleRelaService extends IService<StoreArticleRelaEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

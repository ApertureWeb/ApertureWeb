package com.aperture.community.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.aperture.community.member.entity.MemberCircleRelaEntity;

import java.util.Map;

/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-09 13:01:14
 */
public interface MemberCircleRelaService extends IService<MemberCircleRelaEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


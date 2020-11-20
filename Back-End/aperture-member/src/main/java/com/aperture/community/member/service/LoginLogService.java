package com.aperture.community.member.service;

import com.aperture.community.member.model.LoginLogEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.LoginLogParam;
import com.aperture.community.member.model.vo.LoginStatusVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 19:26:10
 */
public interface LoginLogService extends IService<LoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MessageDto<LoginLogEntity> getAllLoginLog(Long memberId);

    MessageDto<LoginStatusVo> getLoginStatus(Long memberId);

    MessageDto<Boolean> addLoginLog(Long memberId, LoginLogParam loginLogParam);

    MessageDto<Boolean> updateOnlineTime(Long memberId);

    MessageDto<Boolean> updateLoginLog(Long memberId, LoginLogParam loginLogParam);

    MessageDto<Boolean> removeLoginLog(Long memberId);
}


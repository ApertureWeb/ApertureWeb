package com.aperture.community.member.service;

import com.aperture.community.member.vo.LoginLogVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.aperture.community.member.entity.LoginLogEntity;

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

    void saveLoginLog(LoginLogEntity loginLog);

    void removeLoginLog(Long memberId);

    void updateTime(Long memberId);

    void updateLoginLog(LoginLogVo loginLogVo);

    Integer getLoginStatus(Long memberId);

    LoginLogEntity getLoginLog(Long memberId);
}

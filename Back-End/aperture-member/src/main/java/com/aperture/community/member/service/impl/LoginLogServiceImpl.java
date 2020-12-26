package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.member.common.constatnt.LoginConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.common.map.LoginLogMap;
import com.aperture.community.member.dao.LoginLogDao;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.manager.CommonManager;
import com.aperture.community.member.manager.LoginLogManager;
import com.aperture.community.member.manager.MemberManager;
import com.aperture.community.member.manager.PrimaryIdManager;
import com.aperture.community.member.model.LoginLogEntity;
import com.aperture.community.member.model.converter.UmsLoginLogConverter;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.LoginLogParam;
import com.aperture.community.member.model.vo.LoginStatusVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.LoginLogService;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;


@Service("loginLogService")
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLogEntity> implements LoginLogService {

    private LoginLogManager loginLogManager;
    private CommonManager commonManager;
    private PrimaryIdManager primaryIdManager;

    @Autowired
    public LoginLogServiceImpl(LoginLogManager loginLogManager,
                               CommonManager commonManager,
                               PrimaryIdManager primaryIdManager) {
        this.loginLogManager = loginLogManager;
        this.commonManager = commonManager;
        this.primaryIdManager = primaryIdManager;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<LoginLogEntity> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(key)) {
            queryWrapper.eq(LoginLogMap.ID.getValue(), key).or().eq(LoginLogMap.MEMBER_ID.getValue(), key)
                    .or().eq(LoginLogMap.LOGIN_STATUS.getValue(), key);
        }
        IPage<LoginLogEntity> page = this.page(
                new Query<LoginLogEntity>().getPage(params), queryWrapper
        );

        return new PageUtils(page);
    }

    // 查询用户所有日志信息
    @Override
    public MessageDto<LoginLogEntity> getAllLoginLog(Long memberId) {
        if(memberId == null) {
            CastExcepion.cast("getAllLoginLog Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }

        LoginLogEntity loginLogEntity = loginLogManager.getLoginLogMapper().
                getOne(new QueryWrapper<LoginLogEntity>().eq(LoginLogMap.MEMBER_ID.getValue(), memberId));
        if(loginLogEntity == null) {
            return new MessageDto<>("getAllLoginLog error", null, false);
        }
        return new MessageDto<>("getAllLoginLog success", loginLogEntity, true);
    }


    @Override
    public MessageDto<LoginStatusVo> getLoginStatus(Long memberId) {
        if(memberId == null) {
            CastExcepion.cast("getLoginStatus Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        QueryWrapper<LoginLogEntity> queryWrapper = new QueryWrapper<LoginLogEntity>().select(
                LoginLogMap.ID.getValue(),
                LoginLogMap.IP.getValue(),
                LoginLogMap.CITY.getValue(),
                LoginLogMap.LOGIN_TYPE.getValue(),
                LoginLogMap.ONLINE_TIME.getValue(),
                LoginLogMap.LOGIN_STATUS.getValue()
        ).eq(LoginLogMap.MEMBER_ID.getValue(), memberId);
        LoginLogEntity loginLogEntity = loginLogManager.getLoginLogMapper().getOne(queryWrapper);
        LoginStatusVo loginStatusVo = UmsLoginLogConverter.INSTANCE.toLoginStatusVo(loginLogEntity);
        if(loginStatusVo == null) {
            return new MessageDto<>("getLoginStatus error", null, false);
        }
        return new MessageDto<>("getLoginStatus success", loginStatusVo, true);
    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> addLoginLog(Long memberId, LoginLogParam loginLogParam) {
        if(loginLogParam == null) {
            CastExcepion.cast("addLoginLog Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        LoginLogEntity loginLogEntity = UmsLoginLogConverter.INSTANCE.toLoginLogEntity(loginLogParam);
        loginLogEntity.setId(primaryIdManager.getPrimaryId());
        loginLogEntity.setMemberId(memberId);
        boolean save = loginLogManager.getLoginLogMapper().save(loginLogEntity);
        if(!save) {
            return new MessageDto<>("addLoginLog error", null, false);
        }
        return new MessageDto<>("addLoginLog success", null, true);
    }

    @Override
    @Scheduled(cron = "0/600 * * * * MON-SAT")  //每十分钟执行一次
    public MessageDto<Boolean> updateOnlineTime(Long memberId) {
        if(memberId == null || !StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("updateOnlineTime Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        LoginLogEntity logEntity = loginLogManager.getLoginLogMapper().
                getOne(new QueryWrapper<LoginLogEntity>().eq(LoginLogMap.MEMBER_ID.getValue(), memberId));
        UpdateWrapper<LoginLogEntity> updateWrapper = new UpdateWrapper<>();

        if(logEntity.getLoginType().equals(LoginConstant.LoginEnum.LOGINING.getCode())) {
            updateWrapper.set(LoginLogMap.ONLINE_TIME.getValue(), logEntity.getOnlineTime() + LoginConstant.LoginEnum.TEN_MINUTES.getCode());
        }
        if(logEntity.getBanTime().compareTo(LoginConstant.LoginEnum.BANTIME_ZERO.getCode()) == 0) {
            updateWrapper.set(LoginLogMap.ONLINE_TIME.getValue(), logEntity.getBanTime() - LoginConstant.LoginEnum.TEN_MINUTES.getCode());
        }
        updateWrapper.eq(LoginLogMap.MEMBER_ID.getValue(), memberId);
        boolean updateFlag = loginLogManager.getLoginLogMapper().update(updateWrapper);

        if(!updateFlag) {
            return new MessageDto<>("updateOnlineTime error", null, false);
        }
        return new MessageDto<>("updateOnlineTime success", null, true);
    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> updateLoginLog(Long memberId, LoginLogParam loginLogParam) {
        if(loginLogParam == null) {
            CastExcepion.cast("updateLoginLog Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        LoginLogEntity loginLogEntity = UmsLoginLogConverter.INSTANCE.toLoginLogEntity(loginLogParam);
        loginLogEntity.setMemberId(memberId);
        boolean updateFlag = loginLogManager.getLoginLogMapper().updateById(loginLogEntity);
        if(!updateFlag) {
            return new MessageDto<>("updateLoginLog error", null, false);
        }
        return new MessageDto<>("updateLoginLog success", null, true);
    }

    @Override
    @Transactional
    public MessageDto<Boolean> removeLoginLog(Long memberId) {
        assert memberId != null;
        if(!StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("removeLoginLog Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }

        boolean remove = loginLogManager.getLoginLogMapper().remove(new QueryWrapper<LoginLogEntity>().
                eq(LoginLogMap.MEMBER_ID.getValue(), memberId));
        if(!remove) {
            return new MessageDto<>("removeLoginLog error", null, false);
        }
        return new MessageDto<>("removeLoginLog success", null, true);
    }

}
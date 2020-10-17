package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.constant.LoginConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.entity.MemberCircleRelaEntity;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.vo.LoginLogVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.LoginLogDao;
import com.aperture.community.member.entity.LoginLogEntity;
import com.aperture.community.member.service.LoginLogService;
import org.springframework.transaction.annotation.Transactional;


@Service("loginLogService")
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLogEntity> implements LoginLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<LoginLogEntity> queryWrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key).or().eq("member_id", key)
                    .or().eq("login_type", key);
        }
        IPage<LoginLogEntity> page = this.page(
                new Query<LoginLogEntity>().getPage(params), queryWrapper
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveLoginLog(LoginLogEntity loginLog) {
        if(loginLog == null) {
            CastExcepion.cast("saveLoginLog Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        loginLog.setId(IdMaker.getId());
        loginLog.setRegistTime(new Date());
        loginLog.setOnlineTime(LoginConstant.LoginEnum.ONLINETIME_ZERO.getCode());
        loginLog.setBanTime(LoginConstant.LoginEnum.BANTIME_ZERO.getCode());
        loginLog.setLoginStatus(LoginConstant.LoginEnum.LOGINING.getCode());
        this.save(loginLog);
    }

    @Override
    @Transactional
    public void removeLoginLog(Long memberId) {
        if(memberId == null) {
            CastExcepion.cast("removeLoginLog Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        this.remove(new QueryWrapper<LoginLogEntity>().eq("member_id", memberId));
    }

    @Override
    @Scheduled(cron = "0/600 * * * * MON-SAT")  //每十分钟执行一次
    public void updateTime(Long memberId) {
        if(memberId == null || !StringUtils.isEmpty(memberId.toString())) {
            CastExcepion.cast("updateTime Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        LoginLogEntity logEntity = this.getOne(new QueryWrapper<LoginLogEntity>().eq("member_id", memberId));
        UpdateWrapper<LoginLogEntity> updateWrapper = new UpdateWrapper<LoginLogEntity>().eq("member_id", memberId);

        if(logEntity.getLoginType().equals(LoginConstant.LoginEnum.LOGINING.getCode())) {
            updateWrapper.set("online_time", logEntity.getOnlineTime() + LoginConstant.LoginEnum.TEN_MINUTES.getCode());
        }
        if(logEntity.getBanTime().compareTo(LoginConstant.LoginEnum.BANTIME_ZERO.getCode()) == 0) {
            updateWrapper.set("online_time", logEntity.getBanTime() - LoginConstant.LoginEnum.TEN_MINUTES.getCode());
        }
        this.update(updateWrapper);
    }

    @Override
    public void updateLoginLog(LoginLogVo loginLogVo) {
        if(loginLogVo == null) {
            CastExcepion.cast("updateLoginLog Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        LoginLogEntity loginLogEntity = new LoginLogEntity();
        BeanUtils.copyProperties(loginLogVo, loginLogEntity);
        this.updateById(loginLogEntity);
    }
}
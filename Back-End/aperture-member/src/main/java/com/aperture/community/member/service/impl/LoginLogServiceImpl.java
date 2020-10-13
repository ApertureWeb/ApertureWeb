package com.aperture.community.member.service.impl;

import com.aperture.community.member.feign.IdMaker;
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
        IPage<LoginLogEntity> page = this.page(
                new Query<LoginLogEntity>().getPage(params),
                new QueryWrapper<LoginLogEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveLoginLog(LoginLogEntity loginLog) {
        loginLog.setId(IdMaker.getId());
        loginLog.setCreateTime(new Date());
        this.save(loginLog);
    }
}
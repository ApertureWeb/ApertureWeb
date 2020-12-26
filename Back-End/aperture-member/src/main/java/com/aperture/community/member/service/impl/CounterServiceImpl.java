package com.aperture.community.member.service.impl;

import com.aperture.community.member.dao.CounterDao;
import com.aperture.community.member.manager.CounterManager;
import com.aperture.community.member.model.CounterEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.vo.CounterVo;
import com.aperture.community.member.service.CounterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JavaJayV
 * @since 2020-11-14
 */
@Service
public class CounterServiceImpl extends ServiceImpl<CounterDao, CounterEntity> implements CounterService {

    private CounterManager counterManager;

    @Autowired
    public CounterServiceImpl(CounterManager counterManager) {
        this.counterManager = counterManager;
    }

    @Override
    public MessageDto<CounterVo> getCounter(Long memberId) {
        MessageDto<CounterVo> counter = counterManager.getCounterVo(memberId);
        return counter;
    }
}

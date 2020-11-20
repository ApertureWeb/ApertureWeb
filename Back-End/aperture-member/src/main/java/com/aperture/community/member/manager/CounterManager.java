package com.aperture.community.member.manager;

import com.aperture.community.member.common.map.CounterMap;
import com.aperture.community.member.dao.CounterDao;
import com.aperture.community.member.dao.CounterMapper;
import com.aperture.community.member.model.CounterEntity;
import com.aperture.community.member.model.converter.UmsCounterConverter;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.vo.CounterVo;
import com.aperture.community.member.service.CounterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 21:56
 * @Description:
 */
@Service
@Getter
public class CounterManager {

    private CounterMapper counterMapper;
    private CounterService counterService;

    @Autowired
    public CounterManager(CounterMapper counterMapper,
                          CounterService counterService) {
        this.counterMapper = counterMapper;
        this.counterService = counterService;
    }

    // 从db获取计数
    public MessageDto<CounterVo> getCounterVo(Long memberId) {
        CounterEntity counterEntity = counterMapper.getOne(new QueryWrapper<CounterEntity>().select(
                CounterMap.FANS.getValue(),
                CounterMap.FAVORATES.getValue(),
                CounterMap.FOLLOW.getValue(),
                CounterMap.LIKE.getValue(),
                CounterMap.CIRCLE.getValue(),
                CounterMap.WORKS.getValue())
                .eq(CounterMap.MEMBER_ID.getValue(), memberId));
        if(counterEntity == null) {
            return new MessageDto<>("用户不存在", false);
        }
        CounterVo counterVo = UmsCounterConverter.INSTANCE.toCounterVo(counterEntity);

        return new MessageDto<>("getCounterVo success", counterVo, true);
    }

    // 删除
    public MessageDto<CounterVo> deleteCounterVo(Long memberId) {
        boolean remove = counterMapper.remove(new QueryWrapper<CounterEntity>().
                eq(CounterMap.MEMBER_ID.getValue(), memberId));
        return remove ? new MessageDto<>("deleteCounterVo success", true) : new MessageDto<>("deleteCounterVo error", false);
    }

}
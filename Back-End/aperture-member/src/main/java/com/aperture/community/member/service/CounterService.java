package com.aperture.community.member.service;

import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.vo.CounterVo;
import org.springframework.stereotype.Service;

@Service
public interface  CounterService{


        MessageDto<CounterVo> getCounter(Long memberId);
}
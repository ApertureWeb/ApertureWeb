package com.aperture.community.member.controller;


import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;
import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.vo.CounterVo;
import com.aperture.community.member.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JavaJayV
 * @since 2020-11-14
 */
@RestController
@RequestMapping("/member/Counter")
public class CounterController {

    @Autowired
    private CounterService counterService;

    @GetMapping("/getCounter/{memberId}")
    public R getCounter(@PathVariable("memberId") Long memberId) {
        MessageDto<CounterVo> counterVoMessageDto = counterService.getCounter(memberId);
        return R.ok().put("counterVo", counterVoMessageDto.getData());
    }



}


package com.aperture.community.message.controller;

import java.util.Map;

import com.aperture.community.entity.ResultBean;
import com.aperture.community.message.module.MsEventRemindEntity;
import com.aperture.community.message.module.param.MsEventRemindParam;
import com.aperture.community.message.service.MsEventRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author HALOXIAO
 * @email haloxql@gmail.com
 * @date 2020-10-11 17:32:34
 */
@RestController
@RequestMapping("/mseventremind")
public class MsEventRemindController {
    @Autowired
    private MsEventRemindService msEventRemindService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResultBean list(@RequestParam Map<String, Object> params){
        return null;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{eventRemindId}")
    public ResultBean info(@PathVariable("eventRemindId") Long eventRemindId){
        return null;
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResultBean delete(@RequestBody Long[] eventRemindIds){
        return null;
    }

}

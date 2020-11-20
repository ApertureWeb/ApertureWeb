package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.member.model.LoginLogEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.LoginLogParam;
import com.aperture.community.member.model.vo.LoginStatusVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.service.LoginLogService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;



/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 19:26:10
 */
@RestController
@RequestMapping("member/loginlog")
public class LoginLogController {
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = loginLogService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		LoginLogEntity loginLog = loginLogService.getById(id);
        return R.ok().put("loginLog", loginLog);
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        loginLogService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody LoginLogEntity loginLog){
        loginLogService.save(loginLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody LoginLogEntity loginLog){
        loginLogService.updateById(loginLog);

        return R.ok();
    }

    /**
     * 查询用户日志信息
     */
    @GetMapping("/getLoginLog/{memberId}")
    public R getLoginLog(@PathVariable("memberId") Long memberId){
        MessageDto<LoginLogEntity> messageDto = loginLogService.getAllLoginLog(memberId);
        return R.ok().put("loginLog", messageDto.getData());
    }

    /**
     * 查询用户登录状态
     */
    @GetMapping("/getLoginStatus/{memberId}")
    public R getLoginStatus(@PathVariable("memberId") Long memberId){
        MessageDto<LoginStatusVo> messageDto = loginLogService.getLoginStatus(memberId);

        return R.ok().put("loginStatus", messageDto.getData());
    }

    /**
     * 新增用户登录日志
     */
    @PostMapping("/addLoginLog/{memberId}")
    public R addLoginLog(@PathVariable("memberId") Long memberId,
            @RequestBody LoginLogParam loginLogParam){
        loginLogService.addLoginLog(memberId, loginLogParam);
        return R.ok();
    }


    /**
     * 每十分钟更新在线时长
     */
    @PutMapping("/updateTime/{memberId}")
    public R updateTime(@PathVariable("memberId") Long memberId){
        loginLogService.updateOnlineTime(memberId);
        return R.ok();
    }

    /**
     * 更新登录日志
     */
    @PutMapping("/updateLoginLog/{memberId}")
    public R updateLoginLog(@PathVariable("memberId") Long memberId,
            @RequestBody LoginLogParam loginLogParam){
        loginLogService.updateLoginLog(memberId, loginLogParam);
        return R.ok();
    }

    /**
     * 删除登录日志
     */
    @DeleteMapping("/deleteLoginLog/{memberId}")
    public R deleteLoginLog(@PathVariable("memberId") Long memberId){
        loginLogService.removeLoginLog(memberId);
        return R.ok();
    }



}

package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.member.vo.LoginLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.entity.LoginLogEntity;
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
     * 根据memberId查询登录日志信息
     */
    @GetMapping("/getLoginLog/{memberId}")
    public R getLoginLog(@PathVariable("memberId") Long memberId){
        LoginLogEntity loginLog = loginLogService.getLoginLog(memberId);

        return R.ok().put("loginLog", loginLog);
    }

    /**
     * 查询用户是否在线
     */
    @GetMapping("/getLoginStatus/{memberId}")
    public R getLoginStatus(@PathVariable("memberId") Long memberId){
        Integer loginStatus = loginLogService.getLoginStatus(memberId);

        return R.ok().put("loginStatus", loginStatus);
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
     * 新增用户登录日志
     */
    @PostMapping("/saveLoginLog")
    public R saveLoginLog(@RequestBody LoginLogEntity loginLog){
        loginLogService.saveLoginLog(loginLog);

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
     * 每十分钟更新在线时长
     */
    @PutMapping("/updateTime/{memberId}")
    public R updateTime(@PathVariable("memberId") Long memberId){
        loginLogService.updateTime(memberId);

        return R.ok();
    }

    /**
     * 更新登录日志
     */
    @PutMapping("/updateLoginLog")
    public R updateLoginLog(@RequestBody LoginLogVo loginLogVo){
        loginLogService.updateLoginLog(loginLogVo);
        return R.ok();
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
     * 删除登录日志
     */
    @DeleteMapping("/deleteLoginLog/{memberId}")
    public R deleteLoginLog(@PathVariable("memberId") Long memberId){
        loginLogService.removeLoginLog(memberId);
        return R.ok();
    }



}

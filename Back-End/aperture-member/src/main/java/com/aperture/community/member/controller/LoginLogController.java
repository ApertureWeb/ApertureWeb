package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LoginLogEntity loginLog){
		loginLogService.save(loginLog);

        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/saveLoginLog")
    public R saveLoginLog(@RequestBody LoginLogEntity loginLog){
        loginLogService.saveLoginLog(loginLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LoginLogEntity loginLog){
		loginLogService.updateById(loginLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		loginLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

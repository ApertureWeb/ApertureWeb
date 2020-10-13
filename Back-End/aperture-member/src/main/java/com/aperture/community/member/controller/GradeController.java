package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aperture.community.member.entity.GradeEntity;
import com.aperture.community.member.service.GradeService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;



/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-12 21:32:49
 */
@RestController
@RequestMapping("member/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gradeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		GradeEntity grade = gradeService.getById(id);

        return R.ok().put("grade", grade);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GradeEntity grade){
		gradeService.save(grade);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GradeEntity grade){
		gradeService.updateById(grade);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		gradeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.vo.rspVo.GradeValueRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 查询等级经验信息
     */
    @RequestMapping("/getGradeValueInfo")
    public R getGradeValueInfo(){
        GradeValueRespVo gradeValueInfo = gradeService.getGradeValueInfo();
        return R.ok().put("data", gradeValueInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody GradeEntity grade){
		gradeService.saveGrade(grade);
        return R.ok();
    }


    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody GradeEntity grade){
		gradeService.updateById(grade);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		gradeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

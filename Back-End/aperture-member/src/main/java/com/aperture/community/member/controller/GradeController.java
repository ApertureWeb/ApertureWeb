package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.member.model.GradeEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.GradeParam;
import com.aperture.community.member.model.validation.ValidationGroup;
import com.aperture.community.member.model.vo.GradePermissionInfoVo;
import com.aperture.community.member.model.vo.GradeValueInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/save")
    public R save(@RequestBody GradeEntity grade){
        gradeService.save(grade);
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

    /**
     * 查看对应等级所有信息
     */
    @GetMapping("/getGradeAllInfo")
    public R getGradeAllInfo(@RequestParam Integer gradeLevel) {
        MessageDto<GradeEntity> gradeEntityMessageDto = gradeService.getGradeAllInfo(gradeLevel);
        return R.ok().put("gradeAllInfo", gradeEntityMessageDto.getData());
    }

    /**
     * 查看对应等级权限信息
     */
    @GetMapping("/getGradePermissionInfo")
    public R getGradePermissionInfo(@RequestParam Integer gradeLevel) {
        MessageDto<GradePermissionInfoVo> gradePermissionInfo = gradeService.getGradePermissionInfo(gradeLevel);
        return R.ok().put("gradePermissionInfo", gradePermissionInfo.getData());
    }

    /**
     * 查看对应等级经验信息
     */
    @GetMapping("/getGradeValueInfo")
    public R getGradeValueInfo(@RequestParam Integer gradeLevel) {
        MessageDto<GradeValueInfoVo> gradeValueInfo = gradeService.getGradeValueInfo(gradeLevel);
        return R.ok().put("gradeValueInfo", gradeValueInfo.getData());
    }

    /**
     * 添加等级信息
     */
    @PostMapping("/saveGradeInfo")
    public R saveGradeInfo(@RequestBody @Validated(ValidationGroup.addGroup.class) GradeParam gradeParam) {
        gradeService.saveGradeInfo(gradeParam);
        return R.ok();
    }

    /**
     * 修改等级信息
     */
    @PutMapping("/updateGradeInfo")
    public R updateGradeInfo(@RequestBody @Validated(ValidationGroup.updateGroup.class) GradeParam gradeParam) {
        gradeService.updateGradeInfo(gradeParam);
        return R.ok();
    }

}

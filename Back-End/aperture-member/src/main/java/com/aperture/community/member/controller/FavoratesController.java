package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.aperture.community.member.manager.handler.GlobalBlockHandler;
import com.aperture.community.member.model.FavoratesEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.FavoratesParam;
import com.aperture.community.member.model.validation.ValidationGroup;
import com.aperture.community.member.model.vo.FavoratesVo;
import io.swagger.annotations.OAuth2Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.service.FavoratesService;
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
@RequestMapping("member/favorates")
public class FavoratesController {

    @Autowired
    private FavoratesService favoratesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = favoratesService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		FavoratesEntity favorates = favoratesService.getById(id);

        return R.ok().put("favorates", favorates);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save( @RequestBody FavoratesEntity favoratesEntity){
        favoratesService.save(favoratesEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody FavoratesEntity favorates){
        favoratesService.updateById(favorates);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        favoratesService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }



    /**
     * 收藏夹的收藏+1
     */
    @PutMapping("/addFavoratesCollectionCount")
    public R addFavoratesCollectionCount(@RequestParam Long favoratesId){
        favoratesService.addFavoratesCollection(favoratesId);
        return R.ok();
    }

    /**
     * 收藏夹的收藏-1
     */
    @PutMapping("/subFavoratesCollectionCount")
    public R subFavoratesCollectionCount(@RequestParam Long favoratesId){
        favoratesService.subFavoratesCollection(favoratesId);
        return R.ok();
    }

    /**
     * 查询收藏夹列表
     */
    @GetMapping("/getFavoratesList/{memberId}")
    public R getFavoratesList(@PathVariable("memberId") Long memberId){
        MessageDto<List<FavoratesVo>> favoratesList = favoratesService.getFavoratesList(memberId);
        return R.ok().put("favoratesList", favoratesList.getData());
    }

    /**
     * 新增收藏夹
     */
    @PostMapping("/saveFavorates/{memberId}")
    public R saveFavorates(@PathVariable("memberId") Long memberId,
                            @RequestBody @Validated({ValidationGroup.addGroup.class}) FavoratesParam favoratesParam){
        MessageDto<List<FavoratesVo>> favoratesList = favoratesService.saveFavorates(memberId, favoratesParam);
        return R.ok().put("favoratesList", favoratesList.getData());

    }

    /**
     * 修改收藏夹并返回数据
     */
    @PutMapping("/updateFavorates/{memberId}")
    public R updateFavorates(@PathVariable("memberId") Long memberId,
                @RequestBody @Validated({ValidationGroup.updateGroup.class}) FavoratesParam favoratesParam){
        MessageDto<List<FavoratesVo>> favoratesList = favoratesService.updateFavorates(memberId, favoratesParam);
        return R.ok().put("favoratesList", favoratesList.getData());

    }

    /**
     * 新增默认收藏夹
     */
    @PostMapping("/addDefaultFavorates/{memberId}")
    public R addDefaultFavorates(@PathVariable("memberId") Long memberId){
        MessageDto<List<FavoratesVo>> favoratesList = favoratesService.addDefaultFavorates(memberId);
        return R.ok().put("favoratesList", favoratesList.getData());
    }

    /**
     * 删除
     */
    @DeleteMapping("/removeFavorates/{memberId}")
    public R removeFavorates(@PathVariable("memberId") Long memberId, @RequestParam Long id){
        MessageDto<List<FavoratesVo>> favoratesList = favoratesService.removeFavorates(memberId, id);
        return R.ok().put("favoratesList", favoratesList.getData());

    }

}

package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.aperture.community.member.manager.handler.GlobalBlockHandler;
import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.validation.ValidationGroup;
import com.aperture.community.member.model.vo.CollectionListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.service.CollectionService;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.R;

import javax.validation.Valid;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 19:26:10
 */
@RestController
@RequestMapping("member/collcetions")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collectionService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CollectionEntity collcetions = collectionService.getById(id);

        return R.ok().put("collcetions", collcetions);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody  CollectionEntity collectionEntity){
        collectionService.save(collectionEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody CollectionEntity collectionEntity){
        collectionService.updateById(collectionEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        collectionService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 查询收藏夹收藏列表
     */
    @GetMapping("/getCollections")
    public R getCollections(@RequestParam Long favoratesId){
        MessageDto<List<CollectionListVo>> collectionListVos = collectionService.queryCollections(favoratesId);
        return R.ok().put("collectionListVos", collectionListVos.getData());
    }

    /**
     * 判断视频或文章是否已经收藏
     */
    @GetMapping("/isCollection/{memberId}/{targetId}")
    public R isCollection(@PathVariable("memberId") Long memberId,
                          @PathVariable("targetId") Long targetId){
        MessageDto<Boolean> isCollection = collectionService.isCollection(memberId, targetId);
        return R.ok().put("isCollection", isCollection);
    }

    /**
     * 添加收藏
     */
    @PostMapping("/addCollection")
    @SentinelResource(value = "addCollection", blockHandlerClass = GlobalBlockHandler.class)
    public R addCollection( @Validated({ValidationGroup.addGroup.class}) CollectionParam collectionParam){
        collectionService.addCollection(collectionParam);
        return R.ok();
    }

    /**
     * 复制收藏到收藏夹
     */
    @PostMapping("/copyCollection")
    public R copyCollection(@RequestBody @Validated({ValidationGroup.addGroup.class}) CollectionParam collectionParam){
        collectionService.copyCollection(collectionParam);
        return R.ok();
    }

    /**
     * 移动收藏到收藏夹
     */
    @PostMapping("/moveCollection")
    public R moveCollection(@RequestBody @Validated({ValidationGroup.updateGroup.class}) CollectionParam collectionParam){
        collectionService.moveCollection(collectionParam);
        return R.ok();
    }

    /**
     * 取消收藏
     */
    @DeleteMapping("/calcelCollection")
    public R calcelCollection(@RequestParam Long id){
        collectionService.deleteCollection(id);
        return R.ok();
    }

}

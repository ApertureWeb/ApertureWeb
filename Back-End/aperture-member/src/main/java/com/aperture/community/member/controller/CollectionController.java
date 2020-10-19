package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.aperture.community.member.entity.CollectionEntity;
import com.aperture.community.member.vo.CollectionVo;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.service.CollectionService;
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
     * 查询收藏夹收藏列表
     */
    @GetMapping("/getCollections/{memberId}")
    public R getCollections(@PathVariable("memberId") Long memberId,
                            @RequestParam Map<String, Object> params){
        PageUtils page = collectionService.queryCollections(memberId, params);

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
    public R save(@RequestBody CollectionEntity collectionEntity){
        collectionService.save(collectionEntity);

        return R.ok();
    }

    /**
     * 添加收藏
     */
    @PostMapping("/saveCollection")
    public R saveCollection(@RequestBody CollectionEntity collectionEntity){
        collectionService.saveCollection(collectionEntity);

        return R.ok();
    }

    /**
     * 复制收藏到收藏夹
     */
    @PostMapping("/copyCollection")
    public R copyCollection(@RequestBody CollectionVo collectionVo){
        collectionService.copyCollection(collectionVo);

        return R.ok();
    }

    /**
     * 移动收藏到收藏夹
     */
    @PostMapping("/moveCollection")
    public R moveCollection(@RequestBody CollectionVo collectionVo){
        collectionService.moveCollection(collectionVo);

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
     * 取消收藏
     */
    @DeleteMapping("/deleteCollection")
    public R deleteCollection(@RequestParam Long id){
        collectionService.removeCollection(id);

        return R.ok();
    }




}

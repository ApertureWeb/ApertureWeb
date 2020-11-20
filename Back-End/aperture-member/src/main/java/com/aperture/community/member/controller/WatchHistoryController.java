package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.aperture.community.member.model.WatchHistoryEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.WatchHistoryParam;
import com.aperture.community.member.model.validation.ValidationGroup;
import com.aperture.community.member.model.vo.ArticleWatchHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.service.WatchHistoryService;
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
@RequestMapping("member/watchhistory")
public class WatchHistoryController {

    @Autowired
    private WatchHistoryService watchHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = watchHistoryService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WatchHistoryEntity watchHistory = watchHistoryService.getById(id);
        return R.ok().put("watchHistory", watchHistory);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody WatchHistoryEntity watchHistory){
        watchHistoryService.save(watchHistory);
        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody WatchHistoryEntity watchHistory){
        watchHistoryService.updateById(watchHistory);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        watchHistoryService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 获取某个用户视频观看历史
     */
    @GetMapping("/getVideoWatchHistoryList/{memberId}")
    public R getVideoWatchHistoryList(@PathVariable("memberId") Long memberId){
        MessageDto<List<WatchHistoryEntity>> watchHistoryList = watchHistoryService.getVideoWatchHistoryList(memberId);
        return R.ok().put("watchHistoryList", watchHistoryList);
    }

    /**
     * 获取某个用户帖子观看历史
     */
    @GetMapping("/getArticleWatchHistoryList/{memberId}")
    public R getArticleWatchHistoryList(@PathVariable("memberId") Long memberId){
        MessageDto<List<ArticleWatchHistoryVo>> articleWatchHistoryList = watchHistoryService.getArticleWatchHistoryList(memberId);
        return R.ok().put("articleWatchHistoryList", articleWatchHistoryList);
    }

    /**
     * 新增视频观看历史
     */
    @PostMapping("/addVideoHistory/{memberId}")
    public R addHistory(@PathVariable("memberId") Long memberId,
            @RequestBody @Validated({ValidationGroup.addGroup.class}) WatchHistoryParam watchHistoryParam){
        watchHistoryService.addVideoHistory(memberId, watchHistoryParam);
        return R.ok();
    }

    /**
     * 新增帖子历史
     */
    @PostMapping("/addArticleHistory/{memberId}")
    public R addArticleHistory(@PathVariable("memberId") Long memberId,
            @RequestBody @Validated({ValidationGroup.addGroup.class}) WatchHistoryParam watchHistoryParam){
        watchHistoryService.addArticleHistory(memberId, watchHistoryParam);
        return R.ok();
    }

    /**
     * 更新观看历史
     */
    @PutMapping("/updateVideoWatchHistory")
    public R updateWatchHistory(@RequestBody @Validated({ValidationGroup.updateGroup.class}) WatchHistoryParam watchHistoryParam){
        watchHistoryService.updateVideoWatchHistory(watchHistoryParam);
        return R.ok();
    }



    /**
     * 删除观看历史
     */
    @DeleteMapping("/deleteWatchHistory")
    public R deleteWatchHistory(@RequestParam Long id){
        watchHistoryService.removeById(id);
        return R.ok();
    }

}

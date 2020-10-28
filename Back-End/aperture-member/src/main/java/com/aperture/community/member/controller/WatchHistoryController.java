package com.aperture.community.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.aperture.community.member.vo.WatchHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aperture.community.member.entity.WatchHistoryEntity;
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
     * 根据memberId获取用户观看历史
     */
    @RequestMapping("/getWatchHistoryList/{memberId}")
    public R getWatchHistoryList(@PathVariable("memberId") Long memberId){
        List<WatchHistoryEntity> watchHistoryList = watchHistoryService.getWatchHistoryListByMemberId(memberId);

        return R.ok().put("watchHistoryList", watchHistoryList);
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
     * 新增观看历史
     */
    @PostMapping("/addHistory")
    public R addHistory(@RequestBody WatchHistoryEntity watchHistory){
        watchHistoryService.saveHistory(watchHistory);

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
     * 更新观看历史
     */
    @PutMapping("/updateWatchHistory")
    public R updateWatchHistory(@RequestBody WatchHistoryVo watchHistoryVo){
        watchHistoryService.updateWatchHistory(watchHistoryVo);

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
     * 删除观看历史
     */
    @DeleteMapping("/deleteWatchHistory")
    public R deleteWatchHistory(@RequestParam Long id){
        watchHistoryService.removeById(id);
        return R.ok();
    }

}

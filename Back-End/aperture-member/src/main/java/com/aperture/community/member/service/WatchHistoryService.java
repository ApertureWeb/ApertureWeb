package com.aperture.community.member.service;

import com.aperture.community.member.model.WatchHistoryEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.WatchHistoryParam;
import com.aperture.community.member.model.vo.ArticleWatchHistoryVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 19:26:10
 */
public interface WatchHistoryService extends IService<WatchHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MessageDto<List<ArticleWatchHistoryVo>> getArticleWatchHistoryList(Long memberId);

    MessageDto<List<WatchHistoryEntity>> getVideoWatchHistoryList(Long memberId);

    MessageDto<Boolean> addVideoHistory(Long memberId, WatchHistoryParam watchHistoryParam);

    MessageDto<Boolean> addArticleHistory(Long memberId, WatchHistoryParam watchHistoryParam);

    MessageDto<Boolean> updateVideoWatchHistory(WatchHistoryParam watchHistoryParam);
}


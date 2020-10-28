package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.constant.TargetTypeConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.vo.WatchHistoryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.WatchHistoryDao;
import com.aperture.community.member.entity.WatchHistoryEntity;
import com.aperture.community.member.service.WatchHistoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("watchHistoryService")
public class WatchHistoryServiceImpl extends ServiceImpl<WatchHistoryDao, WatchHistoryEntity> implements WatchHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WatchHistoryEntity> page = this.page(
                new Query<WatchHistoryEntity>().getPage(params),
                new QueryWrapper<WatchHistoryEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveHistory(WatchHistoryEntity watchHistory) {
        if(watchHistory == null) {
            CastExcepion.cast("saveHistory Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        watchHistory.setId(IdMaker.getId());
        this.save(watchHistory);
    }

    @Transactional
    @Override
    public void updateWatchHistory(WatchHistoryVo watchHistoryVo) {
        if(watchHistoryVo == null) {
            CastExcepion.cast("updateWatchHistory Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        WatchHistoryEntity watchHistoryEntity = this.getById(watchHistoryVo.getId());
        BeanUtils.copyProperties(watchHistoryVo, watchHistoryEntity);
        this.updateById(watchHistoryEntity);
    }

    @Override
    public List<WatchHistoryEntity> getWatchHistoryListByMemberId(Long memberId) {
        if(memberId == null) {
            CastExcepion.cast("getWatchHistoryListByMemberId Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        List<WatchHistoryEntity> historyList = baseMapper.selectList(new QueryWrapper<WatchHistoryEntity>().eq("member_id", memberId));
        return historyList;
    }
}
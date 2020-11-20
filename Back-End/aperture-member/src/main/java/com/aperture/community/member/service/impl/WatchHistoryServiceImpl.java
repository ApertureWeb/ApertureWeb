package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.common.map.WatchHistoryMap;
import com.aperture.community.member.dao.WatchHistoryDao;
import com.aperture.community.member.dao.WatchHistoryMapper;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.manager.PrimaryIdManager;
import com.aperture.community.member.manager.WatchHistoryManager;
import com.aperture.community.member.model.WatchHistoryEntity;
import com.aperture.community.member.model.converter.UmsWatchHistoryConverter;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.WatchHistoryParam;
import com.aperture.community.member.model.vo.ArticleWatchHistoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.WatchHistoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("watchHistoryService")
public class WatchHistoryServiceImpl extends ServiceImpl<WatchHistoryDao, WatchHistoryEntity> implements WatchHistoryService {

    private WatchHistoryManager watchHistoryManager;
    private PrimaryIdManager primaryIdManager;

    @Autowired
    public WatchHistoryServiceImpl(WatchHistoryManager watchHistoryManager,
                                   PrimaryIdManager primaryIdManager) {
        this.watchHistoryManager = watchHistoryManager;
        this.primaryIdManager = primaryIdManager;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WatchHistoryEntity> page = this.page(
                new Query<WatchHistoryEntity>().getPage(params),
                new QueryWrapper<WatchHistoryEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public MessageDto<List<WatchHistoryEntity>> getVideoWatchHistoryList(Long memberId) {
        if(memberId == null) {
            CastExcepion.cast("getVideoWatchHistoryList Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }

        List<WatchHistoryEntity> watchHistoryEntities = watchHistoryManager.getWatchHistoryMapper().getBaseMapper().selectList(
                new QueryWrapper<WatchHistoryEntity>().eq(WatchHistoryMap.MEMBER_ID.getValue(), memberId));
        if(watchHistoryEntities == null) {
            return new MessageDto<>("getVideoWatchHistoryList success", null,false);

        }
        return new MessageDto<>("getVideoWatchHistoryList success", watchHistoryEntities, true);
    }

    @Override
    public MessageDto<List<ArticleWatchHistoryVo>> getArticleWatchHistoryList(Long memberId) {
        if(memberId == null) {
            CastExcepion.cast("getArticleWatchHistoryList Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        List<WatchHistoryEntity> watchHistoryEntities = watchHistoryManager.getWatchHistoryMapper().getBaseMapper().selectList(
                new QueryWrapper<WatchHistoryEntity>().select(
                        WatchHistoryMap.ID.getValue(),
                        WatchHistoryMap.TARGET_ID.getValue(),
                        WatchHistoryMap.TARGET_NAME.getValue()
                ).eq(WatchHistoryMap.MEMBER_ID.getValue(), memberId)
        );
        List<ArticleWatchHistoryVo> articleWatchHistoryVos = UmsWatchHistoryConverter.INSTANCE.toArticleWatchHistoryVoList(watchHistoryEntities);
        if(articleWatchHistoryVos == null) {
            return new MessageDto<>("getArticleWatchHistoryList success", null,false);
        }
        return new MessageDto<>("getArticleWatchHistoryList success", articleWatchHistoryVos, true);
    }

    @Override
    public MessageDto<Boolean> addVideoHistory(Long memberId, WatchHistoryParam watchHistoryParam) {
        if(watchHistoryParam == null) {
            CastExcepion.cast("addVideoHistory Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        WatchHistoryEntity watchHistoryEntity = UmsWatchHistoryConverter.INSTANCE.toWatchHistoryEntity(watchHistoryParam);
        watchHistoryEntity.setId(primaryIdManager.getPrimaryId());
        watchHistoryEntity.setMemberId(memberId);
        boolean save = watchHistoryManager.getWatchHistoryMapper().save(watchHistoryEntity);

        if(!save) {
            return new MessageDto<>("addVideoHistory success", null,false);
        }
        return new MessageDto<>("addVideoHistory success", null, true);
    }

    @Override
    public MessageDto<Boolean> addArticleHistory(Long memberId, WatchHistoryParam watchHistoryParam) {
        if(watchHistoryParam == null) {
            CastExcepion.cast("addArticleHistory Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        WatchHistoryEntity watchHistoryEntity = UmsWatchHistoryConverter.INSTANCE.toWatchHistoryEntity(watchHistoryParam);
        watchHistoryEntity.setId(primaryIdManager.getPrimaryId());
        watchHistoryEntity.setMemberId(memberId);
        boolean save = watchHistoryManager.getWatchHistoryMapper().save(watchHistoryEntity);
        if(!save) {
            return new MessageDto<>("addArticleHistory success", null,false);
        }
        return new MessageDto<>("addArticleHistory success", null, true);
    }

    @Override
    public MessageDto<Boolean> updateVideoWatchHistory(WatchHistoryParam watchHistoryParam) {
        if(watchHistoryParam == null) {
            CastExcepion.cast("updateWatchHistory Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        WatchHistoryEntity watchHistoryEntity = UmsWatchHistoryConverter.INSTANCE.toWatchHistoryEntity(watchHistoryParam);
        boolean updateById = watchHistoryManager.getWatchHistoryMapper().updateById(watchHistoryEntity);
        if(!updateById) {
            return new MessageDto<>("updateWatchHistory success", null,false);
        }
        return new MessageDto<>("updateWatchHistory success", null, true);
    }



}
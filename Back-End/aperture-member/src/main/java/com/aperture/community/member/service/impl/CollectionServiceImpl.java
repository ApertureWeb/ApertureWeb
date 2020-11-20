package com.aperture.community.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aperture.common.utils.CastExcepion;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.common.map.CollectionMap;
import com.aperture.community.member.dao.CollectionDao;
import com.aperture.community.member.manager.*;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.converter.UmsCollectionConverter;
import com.aperture.community.member.model.converter.UmsCounterConverter;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.vo.CollectionListVo;
import com.aperture.community.member.manager.mq.messageMap.CollectionMessageMap;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.CollectionService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("collcetionsService")
public class CollectionServiceImpl extends ServiceImpl<CollectionDao, CollectionEntity> implements CollectionService {

    private final CollectionManager collectionManager;
    private final CounterManager counterManager;
    private final PrimaryIdManager primaryIdManager;
    private final CommonManager commonManager;
    private final MQManager mqManager;

    @Autowired
    public CollectionServiceImpl(
            CollectionManager collectionManager,
            CounterManager counterManager,
            PrimaryIdManager primaryIdManager,
            CommonManager commonManager,
            MQManager mqManager) {
        this.collectionManager = collectionManager;
        this.counterManager = counterManager;
        this.primaryIdManager = primaryIdManager;
        this.commonManager = commonManager;
        this.mqManager = mqManager;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectionEntity> page = this.page(
                new Query<CollectionEntity>().getPage(params),
                new QueryWrapper<CollectionEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询当前收藏夹的收藏列表
     * @param favoratesId
     * @return
     */
    @Override
    public MessageDto<List<CollectionListVo>> queryCollections(Long favoratesId) {
        assert favoratesId != null;
        if(StringUtils.isEmpty(favoratesId.toString())) {
            // 打印日志并持久化到磁盘
            CastExcepion.cast("queryCollections Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        QueryWrapper<CollectionEntity> queryWrapper = new QueryWrapper<CollectionEntity>()
                .select(CollectionMap.ID.getValue())
                .select(CollectionMap.NAME.getValue())
                .select(CollectionMap.COLLECTION_DATE.getValue())
                .select(CollectionMap.TARGET_ID.getValue())
                .eq(CollectionMap.FAVORATES_ID.getValue(), favoratesId);
        List<CollectionEntity> collectionEntities = collectionManager.getCollectionMapper().getBaseMapper().selectList(queryWrapper);
        List<CollectionListVo> collectionListVos = UmsCollectionConverter.INSTANCE.toCollectionListVoList(collectionEntities);
        return new MessageDto<>("queryCollections success", collectionListVos, true);
    }

    /**
     * 判断用户是否已经收藏
     * @param memberId
     * @param targetId
     * @return
     */
    @Override
    public MessageDto<Boolean> isCollection(Long memberId, Long targetId) {
        assert memberId != null && targetId != null;
        if(StringUtils.isEmpty(memberId.toString()) || StringUtils.isEmpty(targetId.toString())) {
            CastExcepion.cast("isCollection Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        Integer count = collectionManager.getCollectionMapper().getBaseMapper().selectCount(new QueryWrapper<CollectionEntity>()
                .eq(CollectionMap.MEMBER_ID.getValue(), memberId)
                .eq(CollectionMap.TARGET_ID.getValue(), targetId)
        );
        return new MessageDto<>("", count > 0);
    }

    /**
     * 添加收藏
     * @param collectionParam
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> addCollection(CollectionParam collectionParam) {
        if(collectionParam == null) {
            CastExcepion.cast("collectionParam Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        CollectionEntity collectionEntity = UmsCollectionConverter.INSTANCE.toCollectionEntity(collectionParam);
        collectionEntity.setId(primaryIdManager.getPrimaryId());
        collectionEntity.setCollectionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        collectionEntity.setFavoratesId(collectionParam.getFavoratesId());
        collectionEntity.setMemberId(collectionParam.getMemberId());
        collectionEntity.setName(collectionParam.getName());
        collectionEntity.setTargetId(collectionParam.getTargetId());
        collectionManager.getCollectionMapper().save(collectionEntity);

        // 异步修改收藏夹的收藏量+1
        CompletableFuture.runAsync(() ->
            collectionManager.getFavoratesService().addFavoratesCollection(collectionEntity.getFavoratesId()), commonManager.getThreadPoolExecutor());

        // 异步通知作品的收藏量+1
        commonManager.getRocketMQTemplate().asyncSend(mqManager.getToTargetProperties().getTopic(),
            CollectionMessageMap.ADD_COLLECTION.getValue() + collectionEntity.getTargetId(),
            new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                }
                @Override
                public void onException(Throwable throwable) {
                }
            });
        return new MessageDto<>("addCollection success", true);
    }

    // 复制收藏
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> copyCollection(CollectionParam collectionParam) {
        if(collectionParam == null) {
            CastExcepion.cast("copyCollection Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        CollectionEntity collectionEntity = UmsCollectionConverter.INSTANCE.toCollectionEntity(collectionParam);
        collectionEntity.setId(primaryIdManager.getPrimaryId());
        collectionEntity.setCollectionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        collectionEntity.setFavoratesId(collectionParam.getFavoratesId());
        collectionEntity.setMemberId(collectionParam.getMemberId());
        collectionEntity.setName(collectionParam.getName());
        collectionEntity.setTargetId(collectionParam.getTargetId());
        collectionManager.getCollectionMapper().save(collectionEntity);

        // 异步修改收藏夹的收藏量+1
        CompletableFuture.runAsync(() -> {
            collectionManager.getFavoratesService().addFavoratesCollection(collectionEntity.getFavoratesId());
        }, commonManager.getThreadPoolExecutor());
        return new MessageDto<>("copyCollection success", true);

    }

    // 移动收藏
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> moveCollection(CollectionParam collectionParam) {
        if(collectionParam == null) {
            CastExcepion.cast("moveCollection Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        CollectionEntity collectionEntity = UmsCollectionConverter.INSTANCE.toCollectionEntity(collectionParam);
        collectionEntity.setId(collectionParam.getId());
        collectionEntity.setCollectionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        collectionEntity.setFavoratesId(collectionParam.getFavoratesId());
        collectionEntity.setMemberId(collectionParam.getMemberId());
        collectionEntity.setName(collectionParam.getName());
        collectionEntity.setTargetId(collectionParam.getTargetId());
        collectionManager.getCollectionMapper().save(collectionEntity);
        // 异步修改收藏夹的收藏量+1
        CompletableFuture.runAsync(() -> {
            collectionManager.getFavoratesService().addFavoratesCollection(collectionEntity.getFavoratesId());
        }, commonManager.getThreadPoolExecutor());
        return new MessageDto<>("moveCollection success", true);
    }


    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> deleteCollection(Long id) {
        if(id == null) {
            CastExcepion.cast("deleteCollection Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        boolean remove = collectionManager.getCollectionMapper().remove(new QueryWrapper<CollectionEntity>().
                eq(CollectionMap.ID.getValue(), id));

        // 异步修改收藏夹的收藏量 - 1
        CompletableFuture.runAsync(() -> {
            collectionManager.getFavoratesService().subFavoratesCollection(id);
        }, commonManager.getThreadPoolExecutor());

        return remove ? new MessageDto<>("deleteCollection success", true) : new MessageDto<>("deleteCollection error", false);
    }

}
package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.dao.CollectionDao;
import com.aperture.community.member.entity.CollectionEntity;
import com.aperture.community.member.entity.FavoratesEntity;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.service.FavoratesService;
import com.aperture.community.member.vo.CollectionVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.CollectionService;
import org.springframework.transaction.annotation.Transactional;


@Service("collcetionsService")
public class CollectionServiceImpl extends ServiceImpl<CollectionDao, CollectionEntity> implements CollectionService {

    @Autowired
    private FavoratesService favoratesService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectionEntity> page = this.page(
                new Query<CollectionEntity>().getPage(params),
                new QueryWrapper<CollectionEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveCollection(CollectionEntity entity) {
        if(entity == null) {
            CastExcepion.cast("saveCollection Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        entity.setId(IdMaker.getId());
        entity.setCollectionDate(new Date());
        this.save(entity);
        // 收藏夹收藏的数量+1
        favoratesService.addCount(entity.getFavoratesId());
        // TODO: 异步修改收藏夹的收藏量+1


    }

    // 删除收藏
    @Override
    @Transactional
    public void removeCollection(Long id) {
        if(id == null) {
            CastExcepion.cast("removeCollection Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        CollectionEntity collectionEntity = this.getOne(new QueryWrapper<CollectionEntity>().eq("id", id).select("favorates_id"));
        favoratesService.subCount(collectionEntity.getFavoratesId());
        this.removeById(id);
        // TODO: 异步修改被收藏的作品的收藏量-1

    }

    @Override
    @Transactional
    public void copyCollection(CollectionVo collectionVo) {
        if(collectionVo == null) {
            CastExcepion.cast("copyCollection Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        Long favoratesId = collectionVo.getFavoratesId();
        CollectionEntity collectionEntity = this.getById(collectionVo.getId());
        collectionEntity.setId(IdMaker.getId());
        collectionEntity.setFavoratesId(favoratesId);
        this.save(collectionEntity);
        favoratesService.addCount(favoratesId);
    }

    @Override
    @Transactional
    public void moveCollection(CollectionVo collectionVo) {
        if(collectionVo == null) {
            CastExcepion.cast("moveCollection Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        Long id = collectionVo.getId();
        CollectionEntity collectionEntity = this.getById(id);
        favoratesService.subCount(collectionEntity.getFavoratesId());
        Long favoratesId = collectionVo.getFavoratesId();
        this.update(new UpdateWrapper<CollectionEntity>().eq("id", id).set("favorates_id", favoratesId));
        favoratesService.addCount(favoratesId);
    }


    @Override
    public PageUtils queryCollections(Long memberId, Map<String, Object> params) {
        if(params == null || memberId == null) {
            CastExcepion.cast("queryCollections Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        QueryWrapper<CollectionEntity> queryWrapper = new QueryWrapper<CollectionEntity>();
        String key = (String) params.get("key");
        Long favoratesId = (Long) params.get("favoratesId");
        if(!StringUtils.isEmpty(favoratesId.toString())) {
            queryWrapper.eq("favorates_id", favoratesId);
        }
        if(!StringUtils.isEmpty(key)) {
            queryWrapper.likeRight("name", key);
        }
        IPage<CollectionEntity> page = this.page(
                new Query<CollectionEntity>().getPage(params), queryWrapper
        );
        return new PageUtils(page);
    }
}
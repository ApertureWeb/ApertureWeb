package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.common.utils.RRException;
import com.aperture.community.constant.FavoratesConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.entity.CollectionEntity;
import com.aperture.community.member.entity.WatchHistoryEntity;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.service.CollectionService;
import com.aperture.community.member.vo.FavoratesUpdateVo;
import com.aperture.community.member.vo.WatchHistoryVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.dao.FavoratesDao;
import com.aperture.community.member.entity.FavoratesEntity;
import com.aperture.community.member.service.FavoratesService;
import org.springframework.transaction.annotation.Transactional;


@Service("favoratesService")
public class FavoratesServiceImpl extends ServiceImpl<FavoratesDao, FavoratesEntity> implements FavoratesService {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private FavoratesDao favoratesDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FavoratesEntity> page = this.page(
                new Query<FavoratesEntity>().getPage(params),
                new QueryWrapper<FavoratesEntity>()
        );
        return new PageUtils(page);
    }

    // 新增默认收藏夹
    @Override
    public void saveDefaultFavorates(Long memberId) {
        FavoratesEntity favoratesEntity = new FavoratesEntity();
        favoratesEntity.setId(IdMaker.getId());
        favoratesEntity.setCollectionCount(FavoratesConstant.FavoratesEnum.COLLECTION_ZERO.getCode());
        favoratesEntity.setName(FavoratesConstant.FavoratesEnum.DEFAULT_FAVORATES.getMsg());
        favoratesEntity.setMemberId(memberId);
        favoratesEntity.setIsPublic(FavoratesConstant.FavoratesEnum.PUBLIC.getCode());
        favoratesEntity.setDescription("");
        this.save(favoratesEntity);
    }

    // 新增收藏夹
    @Override
    public void saveFavorates(FavoratesEntity favoratesEntity) {
        favoratesEntity.setId(IdMaker.getId());
        favoratesEntity.setCollectionCount(FavoratesConstant.FavoratesEnum.COLLECTION_ZERO.getCode());
        this.save(favoratesEntity);
    }

    // 修改收藏夹
    @Transactional
    @Override
    public void updateFavorates(FavoratesUpdateVo favoratesUpdateVo) {
        if(favoratesUpdateVo == null) {
            CastExcepion.cast("updateFavorates Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        Long id = favoratesUpdateVo.getId();
        FavoratesEntity favoratesEntity = this.getById(id);
        String name = favoratesUpdateVo.getName();
        if(name != null && !StringUtils.isEmpty(name)) {
            favoratesEntity.setName(name);
        }
        Integer inPublic = favoratesUpdateVo.getInPublic();
        if(inPublic != null && !StringUtils.isEmpty(inPublic.toString())) {
            favoratesEntity.setIsPublic(inPublic);
        }
        this.updateById(favoratesEntity);
    }

    @Transactional
    @Override
    public void addCount(Long favoratesId) {
        if(favoratesId == null) {
            CastExcepion.cast("addCount Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        FavoratesEntity entity = this.getById(favoratesId);
        entity.setCollectionCount(entity.getCollectionCount()+1);
        this.updateById(entity);
    }

    @Transactional
    @Override
    public void subCount(Long favoratesId) {
        if(favoratesId == null) {
            CastExcepion.cast("subCount Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        FavoratesEntity favoratesEntity = this.getById(favoratesId);
        this.update(new UpdateWrapper<FavoratesEntity>().eq("favorates_id", favoratesId).set("collection_count", favoratesEntity.getCollectionCount()-1));
    }

    // 删除收藏夹
    @Transactional
    @Override
    public void deleteById(Long id) {
        if(id == null) {
            CastExcepion.cast("deleteById Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        collectionService.remove(new QueryWrapper<CollectionEntity>().eq("favorates_id", id));
        this.removeById(id);
    }

    @Override
    public List<FavoratesEntity> getFavorates(Long memberId) {
        QueryWrapper<FavoratesEntity> queryWrapper = new QueryWrapper<FavoratesEntity>().eq("member_id", memberId);
        List<FavoratesEntity> favoratesEntityList = favoratesDao.selectList(queryWrapper);
        return favoratesEntityList;
    }
}
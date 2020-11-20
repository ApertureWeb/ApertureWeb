package com.aperture.community.member.service.impl;

import com.aperture.common.utils.CastExcepion;
import com.aperture.community.member.common.constatnt.FavoratesConstant;
import com.aperture.community.entity.RESULT_BEAN_STATUS_CODE;
import com.aperture.community.member.common.map.FavoratesMap;
import com.aperture.community.member.dao.FavoratesDao;
import com.aperture.community.member.feign.IdMaker;
import com.aperture.community.member.manager.CollectionManager;
import com.aperture.community.member.manager.CommonManager;
import com.aperture.community.member.manager.FavoratesManager;
import com.aperture.community.member.manager.PrimaryIdManager;
import com.aperture.community.member.manager.handler.GlobalExceptionHandler;
import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.FavoratesEntity;
import com.aperture.community.member.model.converter.UmsFavoratesConverter;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.FavoratesParam;
import com.aperture.community.member.model.vo.FavoratesVo;
import com.aperture.community.member.service.CollectionService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aperture.common.utils.PageUtils;
import com.aperture.common.utils.Query;

import com.aperture.community.member.service.FavoratesService;
import org.springframework.transaction.annotation.Transactional;


@Service("favoratesService")
public class FavoratesServiceImpl extends ServiceImpl<FavoratesDao, FavoratesEntity> implements FavoratesService {

    private FavoratesManager favoratesManager;
    private PrimaryIdManager primaryIdManager;

    @Autowired
    public FavoratesServiceImpl(FavoratesManager favoratesManager,
                                PrimaryIdManager primaryIdManager) {
        this.favoratesManager = favoratesManager;
        this.primaryIdManager = primaryIdManager;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FavoratesEntity> page = this.page(
                new Query<FavoratesEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    // 收藏夹的收藏数量+1
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> addFavoratesCollection(Long favoratesId) {
        favoratesManager.getFavoratesDao().addFavoratesCollection(favoratesId);
        return new MessageDto<>("addFavoratesCollection success", true);
    }

    // 收藏夹的收藏数量-1
    @Override
    public MessageDto<Boolean> subFavoratesCollection(Long favoratesId) {
        favoratesManager.getFavoratesDao().subFavoratesCollection(favoratesId);
        return new MessageDto<>("addFavoratesCollection success", true);
    }

    // 获取收藏夹列表
    @Override
    public MessageDto<List<FavoratesVo>> getFavoratesList(Long memberId) {
        List<FavoratesEntity> favoratesEntities = favoratesManager.getFavoratesMapper().getBaseMapper().selectList(
                new QueryWrapper<FavoratesEntity>().select(
                        FavoratesMap.ID.getValue(),
                        FavoratesMap.NAME.getValue(),
                        FavoratesMap.DESCRIPTION.getValue(),
                        FavoratesMap.IS_PUBLIC.getValue(),
                        FavoratesMap.COLLECTION_COUNT.getValue()).eq(FavoratesMap.MEMBER_ID.getValue(), memberId));
        List<FavoratesVo> favoratesVoList = UmsFavoratesConverter.INSTANCE.toFavoratesVoList(favoratesEntities);
        return new MessageDto<>("getFavoratesList success", favoratesVoList, true);
    }

    // 新增收藏夹
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<List<FavoratesVo>> saveFavorates(Long memberId, FavoratesParam favoratesParam) {
        Long id = primaryIdManager.getPrimaryId();
        favoratesParam.setId(id);
        FavoratesEntity favoratesEntity = UmsFavoratesConverter.INSTANCE.toFavoratesEntity(favoratesParam);
        favoratesEntity.setMemberId(memberId);
        favoratesEntity.setCollectionCount(0);
        boolean save = favoratesManager.getFavoratesMapper().save(favoratesEntity);
        MessageDto<List<FavoratesVo>> favoratesList = getFavoratesList(memberId);
        if(!save) {
            return new MessageDto<>("saveFavorates error", favoratesList.getData(), false);
        }
        return new MessageDto<>("saveFavorates success", favoratesList.getData(), true);
    }

    // 修改收藏夹
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<List<FavoratesVo>> updateFavorates(Long memberId, FavoratesParam favoratesParam) {
        if(favoratesParam == null) {
            CastExcepion.cast("updateFavorates Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        FavoratesEntity favoratesEntity = UmsFavoratesConverter.INSTANCE.toFavoratesEntity(favoratesParam);
        boolean updateFlag = favoratesManager.getFavoratesMapper().updateById(favoratesEntity);

        MessageDto<List<FavoratesVo>> favoratesList = getFavoratesList(memberId);
        if(!updateFlag) {
            return new MessageDto<>("updateFavorates error", favoratesList.getData(), false);
        }
        return new MessageDto<>("updateFavorates success", favoratesList.getData(), true);
    }

    // 新增默认收藏夹
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<List<FavoratesVo>> addDefaultFavorates(Long memberId) {
        Long favoratesId = primaryIdManager.getPrimaryId();
        FavoratesEntity favoratesEntity = new FavoratesEntity();
        favoratesEntity.setId(favoratesId);
        favoratesEntity.setName(FavoratesConstant.FavoratesEnum.DEFAULT_FAVORATES.getMsg());
        favoratesEntity.setMemberId(memberId);
        favoratesEntity.setDescription(FavoratesConstant.FavoratesEnum.DEFAULT_DESCRIPTION.getMsg());
        favoratesEntity.setIsPublic(FavoratesConstant.FavoratesEnum.PUBLIC.getCode());
        favoratesEntity.setCollectionCount(FavoratesConstant.FavoratesEnum.COLLECTION_ZERO.getCode());
        boolean save = favoratesManager.getFavoratesMapper().save(favoratesEntity);

        MessageDto<List<FavoratesVo>> favoratesList = getFavoratesList(memberId);
        if(!save) {
            return new MessageDto<>("addDefaultFavorates error", favoratesList.getData(), false);
        }
        return new MessageDto<>("addDefaultFavorates success", favoratesList.getData(), true);
    }

    // 删除收藏夹
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public MessageDto<List<FavoratesVo>> removeFavorates(Long memberId, Long id) {
        if(id == null || memberId == null) {
            CastExcepion.cast("removeFavorates Error", RESULT_BEAN_STATUS_CODE.ARGUMENT_EXCEPTION);
        }
        boolean removeFlag = favoratesManager.getFavoratesMapper().removeById(id);
        MessageDto<List<FavoratesVo>> favoratesList = getFavoratesList(memberId);
        if(!removeFlag) {
            return new MessageDto<>("removeFavorates error", favoratesList.getData(), false);
        }
        return new MessageDto<>("removeFavorates success", favoratesList.getData(), true);
    }

}
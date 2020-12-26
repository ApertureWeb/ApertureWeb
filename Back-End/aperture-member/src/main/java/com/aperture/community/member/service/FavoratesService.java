package com.aperture.community.member.service;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.FavoratesEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.FavoratesParam;
import com.aperture.community.member.model.vo.FavoratesVo;
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
public interface FavoratesService extends IService<FavoratesEntity> {

    PageUtils queryPage(Map<String, Object> params);


    MessageDto<Boolean> addFavoratesCollection(Long favoratesId);

    MessageDto<Boolean> subFavoratesCollection(Long favoratesId);

    MessageDto<List<FavoratesVo>> getFavoratesList(Long memberId);

    MessageDto<List<FavoratesVo>> updateFavorates(Long memberId, FavoratesParam favoratesUpdateVo);

    MessageDto<List<FavoratesVo>> saveFavorates(Long memberId, FavoratesParam favoratesParam);

    MessageDto<List<FavoratesVo>> addDefaultFavorates(Long memberId);

    MessageDto<List<FavoratesVo>> removeFavorates(Long memberId,Long id);
}


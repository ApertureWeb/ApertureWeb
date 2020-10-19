package com.aperture.community.member.service;

import com.aperture.community.member.vo.FavoratesUpdateVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.aperture.community.member.entity.FavoratesEntity;

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

    void addCount(Long favoratesId);

    void saveDefaultFavorates(Long memberId);

    void saveFavorates(FavoratesEntity favoratesEntity);

    void updateFavorates(FavoratesUpdateVo favoratesUpdateVo);

    void subCount(Long favoratesId);

    void deleteById(Long id);

    List<FavoratesEntity> getFavorates(Long memberId);
}


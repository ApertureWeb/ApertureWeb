package com.aperture.community.member.service;

import com.aperture.community.member.entity.CollectionEntity;
import com.aperture.community.member.vo.CollectionVo;
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
public interface CollectionService extends IService<CollectionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveCollection(CollectionEntity collectionEntity);

    void removeCollection(Long id);

    void copyCollection(CollectionVo collectionVo);

    void moveCollection(CollectionVo collectionVo);


    PageUtils queryCollections(Long memberId, Map<String, Object> params);
}


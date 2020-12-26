package com.aperture.community.member.service;

import com.aperture.community.member.model.CollectionEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.CollectionParam;
import com.aperture.community.member.model.vo.CollectionListVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;

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

    MessageDto<Boolean> copyCollection(CollectionParam collectionParam);

    MessageDto<Boolean> moveCollection(CollectionParam collectionParam);

    MessageDto<List<CollectionListVo>> queryCollections(Long favoratesId);

    MessageDto<Boolean> isCollection(Long memberId, Long targetId);

    MessageDto<Boolean> addCollection(CollectionParam collectionParam);

    MessageDto<Boolean> deleteCollection(Long id);
}


package com.aperture.community.core.dao;

import com.aperture.community.core.module.CmsTagMergeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * tag和内容的关联 Mapper 接口
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-24
 */
@Mapper
public interface CmsTagMergeDao extends BaseMapper<CmsTagMergeEntity> {

}
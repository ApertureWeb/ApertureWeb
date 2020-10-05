package com.aperture.community.core.dao;

import com.aperture.community.core.module.UmsTagEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 标签用来标记视频、帖子 Mapper 接口
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-24
 */
@Mapper
public interface UmsTagDao extends BaseMapper<UmsTagEntity> {

}

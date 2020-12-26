package com.aperture.community.member.dao;

import com.aperture.community.member.model.FavoratesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@Mapper
public interface FavoratesDao extends BaseMapper<FavoratesEntity> {
    void addFavoratesCollection(@Param("favoratesId") Long favoratesId);

    void subFavoratesCollection(@Param("favorates") Long favoratesId);
}

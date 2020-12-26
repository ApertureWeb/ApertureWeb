package com.aperture.community.member.dao;

import com.aperture.community.member.model.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * @Auther: JayV
 * @Date: 2020-10-16 13:20
 * @Description:
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}

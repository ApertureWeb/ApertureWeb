package com.aperture.community.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.aperture.community.member.entity.MemberEntity;

import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-12 21:32:49
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveMemberInfo(MemberEntity member);

    void updateMemberInfo(MemberEntity member);
}


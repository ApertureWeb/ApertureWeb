package com.aperture.community.member.service;

import com.aperture.community.member.model.MemberEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.respVo.MemberBaseInfoRespVo;
import com.aperture.community.member.model.vo.MemberBaseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;

import java.util.List;
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

    MessageDto<MemberEntity> getMemberAllInfo(Long memberId);

    MessageDto<MemberBaseInfoVo> getMemberBaseInfo(Long memberId);

    MessageDto<List<MemberBaseInfoVo>> getMemberBaseInfoByIdList(List<Long> memberIdList);


    void saveMemberInfo(MemberEntity member);

    void updateMemberInfo(MemberEntity member);


}


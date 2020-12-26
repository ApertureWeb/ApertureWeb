package com.aperture.community.member.service;

import com.aperture.community.member.model.FollowGroupRelaEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.FollowGroupRelaParam;
import com.aperture.community.member.model.vo.FollowGroupRelaVo;
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
public interface FollowGroupRelaService extends IService<FollowGroupRelaEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MessageDto<List<FollowGroupRelaVo>> getFollowGroupList(Long memberId);

    MessageDto<Boolean> addFollowGroup(Long memberId, FollowGroupRelaParam followGroupRelaParam);

    MessageDto<Boolean> removeFollowGroup(Long memberId, Long groupId);

    MessageDto<Boolean> addFollowCount(Long groupId);

    MessageDto<Boolean> subFollowCount(Long groupId);

    MessageDto<Long> getDefaultGroupId(Long memberId);

    MessageDto<Long> getSpecialGroupId(Long memberId);
}


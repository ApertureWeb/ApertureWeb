package com.aperture.community.member.service;

import com.aperture.community.member.model.GroupFollowedRelaEntity;
import com.aperture.community.member.model.dto.MessageDto;
import com.aperture.community.member.model.param.GroupFollowedRelaParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 19:26:10
 */
public interface GroupFollowedRelaService extends IService<GroupFollowedRelaEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MessageDto<List<GroupFollowedRelaEntity>> getGroupFollowList(Long memberId);

    MessageDto<Boolean> addFollow(Long memberId, GroupFollowedRelaParam groupFollowedRelaParam);

    MessageDto<Boolean> cancelFollow(Long memberId, GroupFollowedRelaParam groupFollowedRelaParam);

    MessageDto<Boolean> setFollowtoGroup(Long memberId, GroupFollowedRelaParam groupFollowedRelaParam);
}


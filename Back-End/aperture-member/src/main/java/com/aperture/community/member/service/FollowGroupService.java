package com.aperture.community.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.aperture.community.member.entity.FollowGroupEntity;

import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 19:26:10
 */
public interface FollowGroupService extends IService<FollowGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addfollowCount(Long groupId);

    void saveFollowGroup(FollowGroupEntity followGroup);

    void subFollowCount(Long groupId);

    void removeFollowGroup(Long groupId);
}


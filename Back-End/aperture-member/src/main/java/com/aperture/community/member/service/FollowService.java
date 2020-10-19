package com.aperture.community.member.service;

import com.aperture.community.member.vo.FollowCopyVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aperture.common.utils.PageUtils;
import com.aperture.community.member.entity.FollowEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author JavaJayV
 * @email 285075313@qq.com
 * @date 2020-10-11 19:26:10
 */
public interface FollowService extends IService<FollowEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveFollow(FollowEntity follow);

    void removeFollow(Long id);

    void removeBatchByGroupId(Long groupId);

    void updateFollow(Long memberId, FollowCopyVo followCopyVo);

    List<FollowEntity> getfollowList(Long groupId);

}


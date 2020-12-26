package com.aperture.community.member.manager;

import com.aperture.community.member.dao.GroupFollowedRelaMapper;
import com.aperture.community.member.dao.FollowGroupRelaMapper;
import com.aperture.community.member.service.FollowGroupRelaService;
import com.aperture.community.member.service.GroupFollowedRelaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;

/**
 * @Auther: JayV
 * @Date: 2020-11-14 21:47
 * @Description:
 */
@Service
@Getter
public class FollowManager {

    private FollowGroupRelaMapper followGroupRelaMapper;
    private FollowGroupRelaService followGroupRelaService;

    private GroupFollowedRelaMapper groupFollowedRelaMapper;
    private GroupFollowedRelaService groupFollowedRelaService;

    @Autowired
    public FollowManager(FollowGroupRelaMapper followGroupRelaMapper,
                         GroupFollowedRelaMapper groupFollowedRelaMapper,
                         GroupFollowedRelaService groupFollowedRelaService,
                         FollowGroupRelaService followGroupRelaService) {
        this.followGroupRelaMapper = followGroupRelaMapper;
        this.groupFollowedRelaMapper = groupFollowedRelaMapper;
        this.groupFollowedRelaService = groupFollowedRelaService;
        this.followGroupRelaService = followGroupRelaService;
    }

}
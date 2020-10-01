package com.aperture.community.acl.service;

import com.aperture.community.acl.entity.ScsUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

public interface  IScsUserService extends IService<ScsUser>{


    void saveUser(ScsUser user);

    IPage<ScsUser> queryUserPage(Long page, Long limit, ScsUser userQueryVo);

    ScsUser selectByUsername(String username);

}
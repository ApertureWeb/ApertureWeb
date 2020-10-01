package com.aperture.community.acl.service.impl;

import com.aperture.community.acl.entity.ScsUser;
import com.aperture.community.acl.mapper.ScsUserMapper;
import com.aperture.community.acl.service.IScsUserService;
import com.aperture.entity.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JavaJayV
 * @since 2020-09-25
 */
@Service
public class ScsUserServiceImpl extends ServiceImpl<ScsUserMapper, ScsUser> implements IScsUserService {

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void saveUser(ScsUser user) {
        Long userId = restTemplate.getForObject(IdWorker.url, long.class);
        user.setId(userId);
        baseMapper.insert(user);
    }

    /**
     * 根据userName查询用户分页列表
     * @param page
     * @param limit
     * @param userQueryVo
     * @return
     */
    @Override
    public IPage<ScsUser> queryUserPage(Long page, Long limit, ScsUser userQueryVo) {
        Page<ScsUser> userPage = new Page<>(page, limit);
        QueryWrapper<ScsUser> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username", userQueryVo.getUsername());
        }
        IPage<ScsUser> pageModel = baseMapper.selectPage(userPage, wrapper);

        return pageModel;
    }

    @Override
    public ScsUser selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<ScsUser>().eq("username", username));
    }
}

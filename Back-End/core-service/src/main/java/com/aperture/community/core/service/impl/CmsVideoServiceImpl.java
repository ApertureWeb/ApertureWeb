package com.aperture.community.core.service.impl;

import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.CmsVideoParam;
import com.aperture.community.core.module.vo.CmsVideoVO;
import com.aperture.community.core.service.CmsVideoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-23
 */
@Service
public class CmsVideoServiceImpl implements CmsVideoService {
    @Override
    public CmsVideoVO select(CmsVideoParam cmsVideoParam) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<CmsVideoVO> listPage(PageParam pageParam) {
        return null;
    }

    @Override
    public boolean update(CmsVideoParam cmsVideoParam) {
        return false;
    }

    @Override
    public boolean save(CmsVideoParam cmsVideoParam) {


        return false;
    }
}

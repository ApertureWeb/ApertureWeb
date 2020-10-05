package com.aperture.community.core.service.impl;

import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsVideoParam;
import com.aperture.community.core.module.vo.UmsVideoVO;
import com.aperture.community.core.service.UmsVideoService;
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
public class UmsVideoServiceImpl  implements UmsVideoService {
    @Override
    public UmsVideoVO select(UmsVideoParam umsVideoParam) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<UmsVideoVO> listPage(PageParam pageParam) {
        return null;
    }

    @Override
    public boolean update(UmsVideoParam umsVideoParam) {
        return false;
    }

    @Override
    public boolean save(UmsVideoParam umsVideoParam) {


        return false;
    }
}

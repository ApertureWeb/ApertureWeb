package com.aperture.community.core.service.impl;

import com.aperture.community.core.module.UmsTag;
import com.aperture.community.core.dao.UmsTagMapper;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsTagParam;
import com.aperture.community.core.module.vo.UmsTagVO;
import com.aperture.community.core.service.IUmsTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签用来标记视频、帖子 服务实现类
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-24
 */
@Service
public class UmsTagServiceImpl extends ServiceImpl<UmsTagMapper, UmsTag> implements IUmsTagService {
    @Override
    public UmsTagVO select(UmsTagParam umsTagParam) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<UmsTagVO> listPage(PageParam pageParam) {
        return null;
    }

    @Override
    public boolean update(UmsTagParam umsTagParam) {
        return false;
    }

    @Override
    public boolean save(UmsTagParam umsTagParam) {
        return false;
    }
}

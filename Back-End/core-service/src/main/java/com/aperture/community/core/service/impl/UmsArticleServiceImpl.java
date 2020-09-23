package com.aperture.community.core.service.impl;

import com.aperture.community.core.module.UmsArticle;
import com.aperture.community.core.mapper.UmsArticleMapper;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.vo.UmsArticleVO;
import com.aperture.community.core.service.IUmsArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-23
 */
@Service
public class UmsArticleServiceImpl extends ServiceImpl<UmsArticleMapper, UmsArticle> implements IUmsArticleService {
    @Override
    public UmsArticleVO select(UmsArticleParam umsArticleParam) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<UmsArticleVO> listPage(PageParam pageParam) {
        return null;
    }

    @Override
    public boolean update(UmsArticleParam umsArticleParam) {
        return false;
    }

    @Override
    public boolean save(UmsArticleParam umsArticleParam) {
        return false;
    }
}

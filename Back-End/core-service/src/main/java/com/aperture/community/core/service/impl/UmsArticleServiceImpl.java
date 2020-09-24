package com.aperture.community.core.service.impl;

import com.aperture.community.core.controller.UmsArticleController;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.module.UmsArticle;
import com.aperture.community.core.mapper.UmsArticleMapper;
import com.aperture.community.core.module.converter.UmsArticleConverter;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.vo.UmsArticleVO;
import com.aperture.community.core.service.IUmsArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-23
 */
public class UmsArticleServiceImpl extends ServiceImpl<UmsArticleMapper, UmsArticle> implements IUmsArticleService {

    private PrimaryIdManager primaryIdManager;
    private SecurityContext securityContext;

    @Autowired
    public UmsArticleServiceImpl(PrimaryIdManager primaryIdManager) {
        this.primaryIdManager = primaryIdManager;
    }


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
    public Integer save(UmsArticleParam umsArticleParam) {
        UmsArticle article = UmsArticleConverter.INSTANCE.toUmsArticle(umsArticleParam);
        article.setId(primaryIdManager.getPrimaryId());
        article.setCoins(0);
        article.setLike(0);
        return null;
    }
}

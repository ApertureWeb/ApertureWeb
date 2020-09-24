package com.aperture.community.core.service.impl;

import com.aperture.community.core.dao.UmsTagMapper;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.module.UmsArticle;
import com.aperture.community.core.dao.UmsArticleMapper;
import com.aperture.community.core.module.converter.UmsArticleConverter;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.vo.UmsArticleVO;
import com.aperture.community.core.service.IUmsArticleService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
        UmsArticle article = UmsArticleConverter.INSTANCE.toUmsArticle(umsArticleParam);
        if (umsArticleParam.getTags() != null) {
        }
        return update(article, new UpdateWrapper<UmsArticle>().eq("id", umsArticleParam.getId()));
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

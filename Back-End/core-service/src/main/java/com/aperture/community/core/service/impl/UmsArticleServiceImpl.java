package com.aperture.community.core.service.impl;

import com.aperture.community.core.dao.UmsArticleMapper;
import com.aperture.community.core.dao.UmsTagMergeMapper;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.manager.UserManager;
import com.aperture.community.core.module.UmsArticle;
import com.aperture.community.core.module.converter.UmsArticleConverter;
import com.aperture.community.core.module.dto.UserDto;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.vo.UmsArticleVO;
import com.aperture.community.core.service.IUmsArticleService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-23
 */
@Service
public class UmsArticleServiceImpl extends ServiceImpl<UmsArticleMapper, UmsArticle> implements IUmsArticleService {

    private PrimaryIdManager primaryIdManager;
    private UmsTagMergeMapper umsTagMergeMapper;
    private UserManager userManager;

    @Autowired
    public UmsArticleServiceImpl(PrimaryIdManager primaryIdManager, UmsTagMergeMapper umsTagMergeMapper,UserManager userManager) {
        this.primaryIdManager = primaryIdManager;
        this.umsTagMergeMapper = umsTagMergeMapper;
        this.userManager = userManager;

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
//            umsTagMergeMapper.delete(new QueryWrapper<>().select());

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

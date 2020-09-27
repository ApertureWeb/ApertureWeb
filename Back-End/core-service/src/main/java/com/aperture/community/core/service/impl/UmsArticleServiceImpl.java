package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.UmsArticleMap;
import com.aperture.community.core.dao.UmsArticleMapper;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.manager.TagManager;
import com.aperture.community.core.module.UmsArticle;
import com.aperture.community.core.module.UmsTagMerge;
import com.aperture.community.core.module.converter.UmsArticleConverter;
import com.aperture.community.core.module.param.CirclePageParam;
import com.aperture.community.core.module.param.UmsArticleParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsArticleVO;
import com.aperture.community.core.module.vo.UmsArticleViewVO;
import com.aperture.community.core.service.IUmsArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-23
 */
@Service
public class UmsArticleServiceImpl implements IUmsArticleService {

    private PrimaryIdManager primaryIdManager;
    private TagManager tagManager;
    private UmsArticleMapper umsArticleMapper;


    @Autowired
    public UmsArticleServiceImpl(PrimaryIdManager primaryIdManager, TagManager tagManager, UmsArticleMapper umsArticleMapper) {
        this.primaryIdManager = primaryIdManager;
        this.tagManager = tagManager;
        this.umsArticleMapper = umsArticleMapper;
    }


    @Override
    public UmsArticleVO select(UmsArticleParam umsArticleParam) {
        UmsArticle umsArticle = umsArticleMapper.getOne(new QueryWrapper<UmsArticle>().
                select(UmsArticleMap.ID.getValue(),
                        UmsArticleMap.TITLE.getValue(),
                        UmsArticleMap.COINS.getValue(),
                        UmsArticleMap.LIKE.getValue(),
                        UmsArticleMap.CONTENT.getValue(),
                        UmsArticleMap.USER_ID.getValue(),
                        UmsArticleMap.CIRCLE_ID.getValue())
                .eq(UmsArticleMap.ID.getValue(), umsArticleParam.getId()));
        UmsArticleVO articleVO = UmsArticleConverter.INSTANCE.toUmsArticleVO(umsArticle);
        // 需要user名和circle名
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return umsArticleMapper.removeById(id);
    }

    @Override
    public PageVO<UmsArticleViewVO> listPage(CirclePageParam circlePageParam) {
        IPage<UmsArticle> page = umsArticleMapper.page(new Page<>(circlePageParam.getPage(), circlePageParam.getSize()),
                new QueryWrapper<UmsArticle>().
                        select(UmsArticleMap.ID.getValue(),
                                UmsArticleMap.TITLE.getValue(),
                                UmsArticleMap.DESCRIPTION.getValue(),
                                UmsArticleMap.LIKE.getValue(),
                                UmsArticleMap.COINS.getValue(),
                                UmsArticleMap.CIRCLE_ID.getValue(),
                                UmsArticleMap.USER_ID.getValue()
                        ).
                        eq(UmsArticleMap.CIRCLE_ID.getValue(), circlePageParam.getCircleId()));
        List<UmsArticleViewVO> resultList = UmsArticleConverter.INSTANCE.toUmsArticleViewVOList(page.getRecords());
        long total = page.getSize();
        return new PageVO<>(total, resultList);
    }


    @Override
    public boolean update(UmsArticleParam umsArticleParam) {
        UmsArticle article = UmsArticleConverter.INSTANCE.toUmsArticle(umsArticleParam);
        if (umsArticleParam.getTags() != null) {

        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(UmsArticleParam umsArticleParam) throws Exception {
//        TODO 确定权限
        Long id;
        if (umsArticleParam.getTags() != null) {
            Queue<Long> ids = primaryIdManager.getPrimaryIdBatch(umsArticleParam.getTags().size() + 1);
            id = ids.remove();
            List<UmsTagMerge> umsTagMerges = new ArrayList<>(umsArticleParam.getTags().size());
            umsArticleParam.getTags().forEach(p -> {
                umsTagMerges.add(new UmsTagMerge(ids.remove(), id, p));
            });
            if (!tagManager.addContentTag(umsTagMerges)) {
                throw new RuntimeException("数据库执行异常");
            }
        } else {
            id = primaryIdManager.getPrimaryId();
        }

        UmsArticle article = UmsArticleConverter.INSTANCE.toUmsArticle(umsArticleParam);
        article.setId(id);
        article.setCoins(0);
        article.setLike(0);
        return id;
    }


}

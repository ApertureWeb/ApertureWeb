package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.map.CmsArticleMap;
import com.aperture.community.core.dao.CmsArticleMapper;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.manager.TagManager;
import com.aperture.community.core.module.CmsArticleEntity;
import com.aperture.community.core.module.CmsTagMergeEntity;
import com.aperture.community.core.module.converter.CmsArticleConverter;
import com.aperture.community.core.module.param.CirclePageParam;
import com.aperture.community.core.module.param.CmsArticleParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsArticleVO;
import com.aperture.community.core.module.vo.CmsArticleViewVO;
import com.aperture.community.core.service.CmsArticleService;
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
public class CmsArticleServiceImpl implements CmsArticleService {

    private PrimaryIdManager primaryIdManager;
    private TagManager tagManager;
    private CmsArticleMapper cmsArticleMapper;


    @Autowired
    public CmsArticleServiceImpl(PrimaryIdManager primaryIdManager, TagManager tagManager, CmsArticleMapper cmsArticleMapper) {
        this.primaryIdManager = primaryIdManager;
        this.tagManager = tagManager;
        this.cmsArticleMapper = cmsArticleMapper;
    }


    @Override
    public CmsArticleVO select(Long id) {
        CmsArticleEntity cmsArticleEntity = cmsArticleMapper.getOne(new QueryWrapper<CmsArticleEntity>().
                select(CmsArticleMap.ID.getValue(),
                        CmsArticleMap.TITLE.getValue(),
                        CmsArticleMap.CONTENT.getValue(),
                        CmsArticleMap.USER_ID.getValue(),
                        CmsArticleMap.CIRCLE_ID.getValue())
                .eq(CmsArticleMap.ID.getValue(), id));

        CmsArticleVO articleVO = CmsArticleConverter.INSTANCE.toUmsArticleVO(cmsArticleEntity);

        // 需要user名和circle名
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return cmsArticleMapper.removeById(id);
    }

    @Override
    public PageVO<CmsArticleViewVO> listPage(CirclePageParam circlePageParam) {
        IPage<CmsArticleEntity> page = cmsArticleMapper.page(new Page<>(circlePageParam.getPage(), circlePageParam.getSize()),
                new QueryWrapper<CmsArticleEntity>().
                        select(CmsArticleMap.ID.getValue(),
                                CmsArticleMap.TITLE.getValue(),
                                CmsArticleMap.DESCRIPTION.getValue(),
                                CmsArticleMap.CIRCLE_ID.getValue(),
                                CmsArticleMap.USER_ID.getValue()
                        ).
                        eq(CmsArticleMap.CIRCLE_ID.getValue(), circlePageParam.getCircleId()));
        List<CmsArticleViewVO> resultList = CmsArticleConverter.INSTANCE.toUmsArticleViewVOList(page.getRecords());
        long total = page.getSize();
        return new PageVO<>(total, resultList);
    }


    @Override
    public boolean update(CmsArticleParam cmsArticleParam) {
        CmsArticleEntity article = CmsArticleConverter.INSTANCE.toUmsArticle(cmsArticleParam);
        if (cmsArticleParam.getTags() != null) {

        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(CmsArticleParam cmsArticleParam) throws Exception {
//        TODO 确定权限
        Long id;
        if (cmsArticleParam.getTags() != null) {
            Queue<Long> ids = primaryIdManager.getPrimaryIdBatch(cmsArticleParam.getTags().size() + 1);
            id = ids.remove();
            List<CmsTagMergeEntity> umsTagMergeEntities = new ArrayList<>(cmsArticleParam.getTags().size());
            cmsArticleParam.getTags().forEach(p -> {
                umsTagMergeEntities.add(new CmsTagMergeEntity(ids.remove(), id, p));
            });
            if (!tagManager.addContentTag(umsTagMergeEntities)) {
                throw new RuntimeException("数据库执行异常");
            }
        } else {
            id = primaryIdManager.getPrimaryId();
        }
        CmsArticleEntity article = CmsArticleConverter.INSTANCE.toUmsArticle(cmsArticleParam);
        article.setId(id);
        article.setCoins(0);
        article.setLike(0);
        return id;
    }


}

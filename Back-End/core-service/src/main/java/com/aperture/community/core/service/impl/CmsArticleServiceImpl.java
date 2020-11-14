package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.map.CmsArticleMap;
import com.aperture.community.core.common.map.CmsCircleMap;
import com.aperture.community.core.common.status.CircleStatus;
import com.aperture.community.core.common.status.ContentStatus;
import com.aperture.community.core.common.status.EventStatus;
import com.aperture.community.core.dao.CmsArticleMapper;
import com.aperture.community.core.dao.CmsCircleMapper;
import com.aperture.community.core.manager.ContentManager;
import com.aperture.community.core.manager.EventManager;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.manager.TagManager;
import com.aperture.community.core.module.CmsArticleEntity;
import com.aperture.community.core.module.CmsCircleEntity;
import com.aperture.community.core.module.CmsTagEntity;
import com.aperture.community.core.module.converter.CmsArticleConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.CirclePageParam;
import com.aperture.community.core.module.param.CmsArticleParam;
import com.aperture.community.core.module.param.ContentPageParam;
import com.aperture.community.core.module.vo.CmsArticleVO;
import com.aperture.community.core.module.vo.CmsArticleViewVO;
import com.aperture.community.core.module.vo.EventVO;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.service.CmsArticleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    private final PrimaryIdManager primaryIdManager;
    private final TagManager tagManager;
    private final ContentManager contentManager;
    private final EventManager eventManager;
    private final CmsCircleMapper cmsCircleMapper;

    private final RestTemplate restTemplate;

    @Autowired
    public CmsArticleServiceImpl(PrimaryIdManager primaryIdManager, TagManager tagManager, ContentManager contentManager, EventManager eventManager, CmsCircleMapper cmsCircleMapper, RestTemplate restTemplate) {
        this.primaryIdManager = primaryIdManager;
        this.tagManager = tagManager;
        this.eventManager = eventManager;
        this.contentManager = contentManager;
        this.cmsCircleMapper = cmsCircleMapper;
        this.restTemplate = restTemplate;
    }


    @Transactional()
    @Override
    public CmsArticleVO select(Long id) {
        CmsArticleEntity cmsArticleEntity = contentManager.getCmsArticleMapper().getOne(new QueryWrapper<CmsArticleEntity>().
                select(CmsArticleMap.ID.getValue(),
                        CmsArticleMap.TITLE.getValue(),
                        CmsArticleMap.CONTENT.getValue(),
                        CmsArticleMap.USER_ID.getValue(),
                        CmsArticleMap.CIRCLE_ID.getValue())
                .eq(CmsArticleMap.ID.getValue(), id));
        CmsArticleVO articleVO = CmsArticleConverter.INSTANCE.toUmsArticleVO(cmsArticleEntity);
        String name = restTemplate.getForObject("UserService", String.class);
        CmsCircleEntity cmsCircleEntity = cmsCircleMapper.getOne(new QueryWrapper<CmsCircleEntity>().select(CmsCircleMap.NAME.getValue()).eq(CmsCircleMap.ID.getValue(), cmsArticleEntity.getCircleId()));
        articleVO.setCircleName(cmsCircleEntity.getName());
        articleVO.setUsername(name);
        return articleVO;
    }

    @Override
    public boolean delete(Long id) {
        return contentManager.getCmsArticleMapper().removeById(id);
    }

    @Override
    public PageVO<CmsArticleViewVO> listPage(CirclePageParam circlePageParam) {
        IPage<CmsArticleEntity> page = contentManager.getCmsArticleMapper().page(new Page<>(circlePageParam.getPage(), circlePageParam.getSize()),
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
        return cmsArticleParam.getTags() != null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MessageDto<Long> save(CmsArticleParam cmsArticleParam) {
        Long id = null;
        if (cmsArticleParam.getTags() != null) {
//        TODO check
            int size = tagManager.getUmsTagMapper().count(new QueryWrapper<CmsTagEntity>().in("id", cmsArticleParam.getTags()).last("LIMIT " + cmsArticleParam.getTags().size()));
            if (size != cmsArticleParam.getTags().size()) {
                return new MessageDto<>("Tag不存在", false);
            }
        } else {
            id = primaryIdManager.getPrimaryId();
        }
        CmsArticleEntity article = CmsArticleConverter.INSTANCE.toUmsArticle(cmsArticleParam);
        article.setId(id);
        contentManager.getCmsArticleMapper().save(article);
        return new MessageDto<>("success", id, true);
    }

    @Override
    public MessageDto<PageVO<CmsArticleViewVO>> optionalGet(@Validated ContentPageParam contentPageParam) {
        CmsArticleMapper articleMapper = contentManager.getCmsArticleMapper();
        IPage<CmsArticleEntity> articlePage = articleMapper.page(new Page<>(contentPageParam.getPage(), contentPageParam.getSize()), new QueryWrapper<CmsArticleEntity>().select(
                CmsArticleMap.ID.getValue(),
                CmsArticleMap.TITLE.getValue(),
                CmsArticleMap.CONTENT.getValue(),
                CmsArticleMap.DESCRIPTION.getValue(),
                CmsArticleMap.USER_ID.getValue(),
                CmsArticleMap.CIRCLE_ID.getValue(),
                CmsArticleMap.ICON.getValue()
        ).eq(CmsArticleMap.STATUS.getValue(), ContentStatus.NORMAL.getValue()));
        List<CmsArticleEntity> articleEntityList = articlePage.getRecords();
        //获取用户姓名
        String usernames = restTemplate.getForObject("", String.class);
        Map<Long, CmsCircleEntity> cmsCircleEntityMap = cmsCircleMapper.list(new QueryWrapper<CmsCircleEntity>().select(
                CmsCircleMap.ID.getValue(),
                CmsCircleMap.NAME.getValue()
        ).eq(CmsCircleMap.STATUS.getValue(), CircleStatus.NORMAL.getValue()))
                .stream().collect(Collectors.toMap(CmsCircleEntity::getId, value -> value));
        MessageDto<Map<Long, EventVO>> msg = eventManager.getEventVOMap(articleEntityList.stream().map(CmsArticleEntity::getId).collect(Collectors.toSet()), EventStatus.ARTICLE);
        return null;
    }
}

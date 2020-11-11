package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.TokenStore;
import com.aperture.community.core.common.status.CommentStatus;
import com.aperture.community.core.common.status.ContentType;
import com.aperture.community.core.common.map.CmsArticleMap;
import com.aperture.community.core.common.map.CmsCommentMap;
import com.aperture.community.core.common.map.CmsVideoMap;
import com.aperture.community.core.manager.InteractCommentManager;
import com.aperture.community.core.manager.ContentManager;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.module.CmsArticleEntity;
import com.aperture.community.core.module.CmsCommentEntity;
import com.aperture.community.core.module.CmsReplyEntity;
import com.aperture.community.core.module.CmsVideoEntity;
import com.aperture.community.core.module.converter.CmsCommentConverter;
import com.aperture.community.core.module.converter.CmsReplyConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.identify.UserInfo;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.CmsCommentParam;
import com.aperture.community.core.module.param.CmsReplyParam;
import com.aperture.community.core.module.vo.ChildCommentVO;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsCommentVO;
import com.aperture.community.core.service.CmsCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论功能 服务实现类
 * </p>
 *
 * @author HALOXIAO
 * @since 2020-09-27
 */
@Service
public class CmsCommentServiceImpl implements CmsCommentService {

    private final Long ROOT_STATUS_NON_INSIDE_REPLY = 0L;

    private InteractCommentManager interactCommentManager;
    private PrimaryIdManager primaryIdManager;
    private ContentManager contentManager;


    @Autowired
    public CmsCommentServiceImpl(InteractCommentManager interactCommentManager, PrimaryIdManager primaryIdManager,
                                 ContentManager contentManager) {
        this.interactCommentManager = interactCommentManager;
        this.primaryIdManager = primaryIdManager;
        this.contentManager = contentManager;
    }

    @Override
    public CmsCommentVO select(CmsCommentParam cmsCommentParam) {

        return null;
    }

    @Override
    public MessageDto delete(Long id) {
        UserInfo userInfo = TokenStore.getUserInfo();
        CmsCommentEntity cmsCommentEntity = interactCommentManager.getUmsCommentMapper().getOne(new QueryWrapper<CmsCommentEntity>()
                .select(CmsCommentMap.USER_ID.getValue(), CmsCommentMap.TARGET_ID.getValue()).eq(CmsCommentMap.ID.getValue(), id));
        Long commenterId = cmsCommentEntity.getUserId();
        Long contentId = cmsCommentEntity.getTargetId();
        Long authorId = null;
        CmsArticleEntity articleEntity = contentManager.getCmsArticleMapper().getOne(new QueryWrapper<CmsArticleEntity>()
                .select(CmsArticleMap.USER_ID.getValue()).eq(CmsArticleMap.ID.getValue(), contentId));
        //需要判断是Article还是Video
        if (articleEntity.getUserUid() == null) {
            CmsVideoEntity videoEntity = contentManager.getCmsVideoMapper().getOne(new QueryWrapper<CmsVideoEntity>()
                    .select(CmsVideoMap.USER_ID.getValue()).eq(CmsVideoMap.ID.getValue(), contentId));
            if (videoEntity.getUserId() != null) {
                authorId = videoEntity.getUserId();
            }
        } else {
            authorId = articleEntity.getUserUid();
        }
        MessageDto result = null;
        if (authorId != null && userInfo.getId().equals(authorId)) {
            result = interactCommentManager.getUmsCommentMapper().update(new UpdateWrapper<CmsCommentEntity>().set(CmsCommentMap.STATUS.getValue(), CommentStatus.FLOD)
                    .eq(CmsCommentMap.ID.getValue(), id)
                    .eq(CmsCommentMap.STATUS.getValue(), CommentStatus.NORMAL.getValue())) ? new MessageDto("success", true) : new MessageDto("删除失败", false);
        }
        if (userInfo.getId().equals(commenterId)) {
            result = interactCommentManager.getUmsCommentMapper().removeById(id) ? new MessageDto("success", true) : new MessageDto("删除失败", false);
        }
        if (result == null) {
            return new MessageDto("无权删除", false);
        }
        //文章所有者只能zhe折叠，comment所有者可以自行删除
        return result;
    }


    @Override
    public PageVO<CmsCommentVO> commentPage(PageParam pageParam, Integer contentId, boolean isHeat) {
        assert contentId != null;
        assert pageParam != null;
        boolean asc = false;
        String field;
        //判断按热度还是按时间
        if (isHeat) {
            field = CmsCommentMap.LIKE.getValue();
            asc = true;
        } else {
            field = CmsCommentMap.COMMENT_DATE.getValue();
        }
        //获取本页的Comment
        IPage<CmsCommentEntity> page = interactCommentManager.getUmsCommentMapper().page(new Page<>(pageParam.getPage(), pageParam.getSize()),
                new QueryWrapper<CmsCommentEntity>()
                        .select(CmsCommentMap.ID.getValue()
                                , CmsCommentMap.USER_ID.getValue()
                                , CmsCommentMap.TARGET_ID.getValue()
                                , CmsCommentMap.CONTENT.getValue()
                                , CmsCommentMap.COMMENT_DATE.getValue()
                                , CmsCommentMap.LIKE.getValue()
                                , CmsCommentMap.STATUS.getValue()
                        )
                        .eq(CmsCommentMap.TARGET_ID.getValue(), contentId)
                        .ne(CmsCommentMap.STATUS.getValue(), CommentStatus.REVIEW)
                        .orderBy(false, asc, field)
        );
        //获取本页comment所对应的Reply


        List<CmsCommentEntity> umsCommentEntities = page.getRecords();

        List<CmsCommentVO> result = CmsCommentConverter.INSTANCE.toUmsCommentVOs(umsCommentEntities);
        long size = page.getTotal();
        return null;
    }

    public MessageDto<Boolean> sendReplay(CmsReplyParam cmsReplyParam) {
        CmsReplyEntity cmsReplyEntity = CmsReplyConverter.INSTANCE.toUmsReply(cmsReplyParam);
        cmsReplyEntity.setCommentDate(LocalDateTime.now());
        cmsReplyEntity.setLike(0);
        cmsReplyEntity.setStatus(0);
//        需要check User和发送消息
        return interactCommentManager.sendReply(cmsReplyEntity);
    }

    public MessageDto<Boolean> send() {
        return null;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean sendComment(CmsCommentParam cmsCommentParam, ContentType type) {
        assert type != null;
        Long id = primaryIdManager.getPrimaryId();
        CmsCommentEntity comment = CmsCommentConverter.INSTANCE.toUmsComment(cmsCommentParam);
        //通知消息组件
        //获取user信息

        if (type.equals(ContentType.ARTICLE)) {
            CmsArticleEntity article = contentManager.getCmsArticleMapper().getOne(new QueryWrapper<CmsArticleEntity>().select("1").
                    eq(CmsArticleMap.ID.getValue(), comment.getTargetId()));
            if (article == null) {
                throw new IllegalArgumentException("找不到目标文章");
            }
        } else {
            CmsVideoEntity video = contentManager.getCmsVideoMapper().getOne(new QueryWrapper<CmsVideoEntity>().select("1").
                    eq(CmsVideoMap.ID.getValue(), comment.getTargetId()));
            if (video == null) {
                throw new IllegalArgumentException("找不到目标视频");
            }
        }
        LocalDateTime nowTime = LocalDateTime.now();
        comment.setCommentDate(nowTime);
        comment.setStatus(CommentStatus.NORMAL.getValue());
        comment.setId(id);
        return interactCommentManager.getUmsCommentMapper().save(comment);
    }


    private List<ChildCommentVO> getChildCommentVO(List<CmsCommentEntity> comments, Long contentId) {
        List<Long> ids = comments.stream().map(CmsCommentEntity::getId).collect(Collectors.toList());
        List<CmsCommentEntity> cComments = interactCommentManager.getUmsCommentMapper().list(new QueryWrapper<CmsCommentEntity>()
                .select(CmsCommentMap.ID.getValue()
                        , CmsCommentMap.USER_ID.getValue()
                        , CmsCommentMap.TARGET_ID.getValue()
                        , CmsCommentMap.CONTENT.getValue()
                        , CmsCommentMap.COMMENT_DATE.getValue()
                        , CmsCommentMap.LIKE.getValue()
                        , CmsCommentMap.STATUS.getValue()
                )
                .eq(CmsCommentMap.TARGET_ID.getValue(), contentId)
                .ne(CmsCommentMap.STATUS.getValue(), CommentStatus.REVIEW)
        );
        CmsCommentConverter.INSTANCE.toChildCommentVOs(cComments);
        return null;
    }

// --------------------------Here is reply--------------------------


}

package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.CommentStatus;
import com.aperture.community.core.common.ContentType;
import com.aperture.community.core.common.map.UmsArticleMap;
import com.aperture.community.core.common.map.UmsCommentMap;
import com.aperture.community.core.common.map.UmsVideoMap;
import com.aperture.community.core.manager.InteractCommentManager;
import com.aperture.community.core.manager.ContentManager;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.module.UmsArticleEntity;
import com.aperture.community.core.module.UmsCommentEntity;
import com.aperture.community.core.module.UmsReplyEntity;
import com.aperture.community.core.module.UmsVideoEntity;
import com.aperture.community.core.module.converter.UmsCommentConverter;
import com.aperture.community.core.module.converter.UmsReplyConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsCommentParam;
import com.aperture.community.core.module.param.UmsReplyParam;
import com.aperture.community.core.module.vo.ChildCommentVO;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsCommentVO;
import com.aperture.community.core.service.UmsCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class UmsCommentServiceImpl implements UmsCommentService {

    private final Long ROOT_STATUS_NON_INSIDE_REPLY = 0L;

    private InteractCommentManager interactCommentManager;
    private PrimaryIdManager primaryIdManager;
    private ContentManager contentManager;


    @Autowired
    public UmsCommentServiceImpl(InteractCommentManager interactCommentManager, PrimaryIdManager primaryIdManager,
                                 ContentManager contentManager) {
        this.interactCommentManager = interactCommentManager;
        this.primaryIdManager = primaryIdManager;
        this.contentManager = contentManager;
    }

    @Override
    public UmsCommentVO select(UmsCommentParam umsCommentParam) {
        return null;
    }

    @Override
    public boolean delete(Long id) {


        return false;
    }

    @Override
    public PageVO<UmsCommentVO> commentPage(PageParam pageParam, Integer contentId, boolean isHeat) {
        assert contentId != null;
        assert pageParam != null;
        boolean asc = false;
        String field;
        //判断按热度还是按时间
        if (isHeat) {
            field = UmsCommentMap.LIKE.getValue();
            asc = true;
        } else {
            field = UmsCommentMap.COMMENT_DATE.getValue();
            asc = false;
        }
        IPage<UmsCommentEntity> page = interactCommentManager.getUmsCommentMapper().page(new Page<>(pageParam.getPage(), pageParam.getSize()),
                new QueryWrapper<UmsCommentEntity>()
                        .select(UmsCommentMap.ID.getValue()
                                , UmsCommentMap.USER_ID.getValue()
                                , UmsCommentMap.TARGET_ID.getValue()
                                , UmsCommentMap.CONTENT.getValue()
                                , UmsCommentMap.COMMENT_DATE.getValue()
                                , UmsCommentMap.LIKE.getValue()
                                , UmsCommentMap.STATUS.getValue()
                        )
                        .eq(UmsCommentMap.TARGET_ID.getValue(), contentId)
                        .ne(UmsCommentMap.STATUS.getValue(), CommentStatus.REVIEW)
                        .orderBy(false, asc, field)
        );
        List<UmsCommentEntity> umsCommentEntities = page.getRecords();
        List<UmsCommentVO> result = UmsCommentConverter.INSTANCE.toUmsCommentVOs(umsCommentEntities);
        long size = page.getTotal();

        return null;
    }

    public MessageDto<Boolean> sendReplay(UmsReplyParam umsReplyParam) {
        UmsReplyEntity umsReplyEntity = UmsReplyConverter.INSTANCE.toUmsReply(umsReplyParam);
        umsReplyEntity.setCommentDate(LocalDateTime.now());
        umsReplyEntity.setLike(0);
        umsReplyEntity.setStatus(0);
//        需要check User和发送消息
        return interactCommentManager.sendReply(umsReplyEntity);
    }

    public MessageDto<Boolean> send() {
        return null;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean sendComment(UmsCommentParam umsCommentParam, ContentType type) {
        assert type != null;
        Long id = primaryIdManager.getPrimaryId();
        UmsCommentEntity comment = UmsCommentConverter.INSTANCE.toUmsComment(umsCommentParam);
        //通知消息组件
        //获取user信息

        if (type.equals(ContentType.ARTICLE)) {
            UmsArticleEntity article = contentManager.getUmsArticleMapper().getOne(new QueryWrapper<UmsArticleEntity>().select("1").
                    eq(UmsArticleMap.ID.getValue(), comment.getTargetId()));
            if (article == null) {
                throw new IllegalArgumentException("找不到目标文章");
            }
        } else {
            UmsVideoEntity video = contentManager.getUmsVideoMapper().getOne(new QueryWrapper<UmsVideoEntity>().select("1").
                    eq(UmsVideoMap.ID.getValue(), comment.getTargetId()));
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


    private List<ChildCommentVO> getChildCommentVO(List<UmsCommentEntity> comments, Long contentId) {
        List<Long> ids = comments.stream().map(UmsCommentEntity::getId).collect(Collectors.toList());
        List<UmsCommentEntity> cComments = interactCommentManager.getUmsCommentMapper().list(new QueryWrapper<UmsCommentEntity>()
                .select(UmsCommentMap.ID.getValue()
                        , UmsCommentMap.USER_ID.getValue()
                        , UmsCommentMap.TARGET_ID.getValue()
                        , UmsCommentMap.CONTENT.getValue()
                        , UmsCommentMap.COMMENT_DATE.getValue()
                        , UmsCommentMap.LIKE.getValue()
                        , UmsCommentMap.STATUS.getValue()
                )
                .eq(UmsCommentMap.TARGET_ID.getValue(), contentId)
                .ne(UmsCommentMap.STATUS.getValue(), CommentStatus.REVIEW)
        );
        UmsCommentConverter.INSTANCE.toChildCommentVOs(cComments);
        return null;
    }

// --------------------------Here is reply--------------------------




}

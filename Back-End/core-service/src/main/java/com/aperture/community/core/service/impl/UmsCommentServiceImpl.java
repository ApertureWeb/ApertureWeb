package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.CommentStatus;
import com.aperture.community.core.common.ContentType;
import com.aperture.community.core.common.map.UmsArticleMap;
import com.aperture.community.core.common.map.UmsCommentMap;
import com.aperture.community.core.common.map.UmsVideoMap;
import com.aperture.community.core.dao.UmsArticleMapper;
import com.aperture.community.core.dao.UmsCommentMapper;
import com.aperture.community.core.dao.UmsVideoMapper;
import com.aperture.community.core.manager.ContentManager;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.module.UmsArticle;
import com.aperture.community.core.module.UmsComment;
import com.aperture.community.core.module.UmsVideo;
import com.aperture.community.core.module.converter.UmsCommentConverter;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsCommentParam;
import com.aperture.community.core.module.vo.ChildCommentVO;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsCommentVO;
import com.aperture.community.core.service.IUmsCommentService;
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
public class UmsCommentServiceImpl implements IUmsCommentService {

    private final Long ROOT_STATUS_NON_INSIDE_REPLY = 0L;

    private UmsCommentMapper umsCommentMapper;
    private PrimaryIdManager primaryIdManager;
    private ContentManager contentManager;


    @Autowired
    public UmsCommentServiceImpl(UmsCommentMapper umsCommentMapper, PrimaryIdManager primaryIdManager,
                                 ContentManager contentManager) {
        this.umsCommentMapper = umsCommentMapper;
        this.primaryIdManager = primaryIdManager;
        this.contentManager = contentManager;
    }

    @Override
    public UmsCommentVO select(UmsCommentParam umsCommentParam) {
        return null;
    }

    @Override
    public boolean delete(Long id) {

        umsCommentMapper.remove(new QueryWrapper<>());

        return false;
    }

    @Override
    public PageVO<UmsCommentVO> listPage(PageParam pageParam, Integer contentId, boolean isHeat) {
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
        IPage<UmsComment> page = umsCommentMapper.page(new Page<>(pageParam.getPage(), pageParam.getSize()),
                new QueryWrapper<UmsComment>()
                        .select(UmsCommentMap.ID.getValue()
                                , UmsCommentMap.USER_ID.getValue()
                                , UmsCommentMap.TARGET_ID.getValue()
                                , UmsCommentMap.CONTENT.getValue()
                                , UmsCommentMap.COMMENT_DATE.getValue()
                                , UmsCommentMap.LIKE.getValue()
                                , UmsCommentMap.STATUS.getValue()
                                , UmsCommentMap.ROOT_ID.getValue()
                                , UmsCommentMap.REPLY_ID.getValue()
                        )
                        .eq(UmsCommentMap.REPLY_ID.getValue(), 0)
                        .eq(UmsCommentMap.TARGET_ID.getValue(), contentId)
                        .ne(UmsCommentMap.STATUS.getValue(), CommentStatus.REVIEW)
                        .orderBy(false, asc, field)
        );
        List<UmsComment> umsComments = page.getRecords();
        List<UmsCommentVO> result = UmsCommentConverter.INSTANCE.toUmsCommentVOs(umsComments);
        long size = page.getTotal();

        return null;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean sendComment(UmsCommentParam umsCommentParam, ContentType type) {
        assert type != null;
        Long id = primaryIdManager.getPrimaryId();
        UmsComment comment = UmsCommentConverter.INSTANCE.toUmsComment(umsCommentParam);
        //通知消息组件
        //获取user信息

        if (type.equals(ContentType.ARTICLE)) {
            UmsArticle article = contentManager.getUmsArticleMapper().getOne(new QueryWrapper<UmsArticle>().select("1").
                    eq(UmsArticleMap.ID.getValue(), comment.getTargetId()));
            if (article == null) {
                throw new IllegalArgumentException("找不到目标文章");
            }
        } else {
            UmsVideo video = contentManager.getUmsVideoMapper().getOne(new QueryWrapper<UmsVideo>().select("1").
                    eq(UmsVideoMap.ID.getValue(), comment.getTargetId()));
            if (video == null) {
                throw new IllegalArgumentException("找不到目标视频");
            }
        }
        comment = checkCommentStatus(comment);
        LocalDateTime nowTime = LocalDateTime.now();
        comment.setCommentDate(nowTime);
        comment.setStatus(CommentStatus.NORMAL.getValue());
        comment.setId(id);
        return umsCommentMapper.save(comment);
    }

    /**
     * 检测replyId和RootId，同时管理楼中楼状态
     */
    private UmsComment checkCommentStatus(UmsComment commentParam) {
        assert commentParam != null;
        if (commentParam.getReplyId() != 0) {
            UmsComment comment = umsCommentMapper.getOne(new QueryWrapper<UmsComment>().select(UmsCommentMap.ID.getValue())
                    .eq(UmsCommentMap.ID.getValue(), commentParam.getReplyId()));
            if (comment == null) {
                throw new IllegalArgumentException("找不到目标评论");
            }
        }
        if (commentParam.getRootId() != 0) {
            UmsComment comment = umsCommentMapper.getOne(new QueryWrapper<UmsComment>().select(UmsCommentMap.ID.getValue(), UmsCommentMap.ROOT_ID.getValue())
                    .eq(UmsCommentMap.ID.getValue(), commentParam.getRootId()));
            if (comment == null) {
                commentParam.setRootId(ROOT_STATUS_NON_INSIDE_REPLY);
            } else {
                commentParam.setRootId(commentParam.getRootId());
            }
        }
        return commentParam;
    }


    private List<ChildCommentVO> getChildCommentVO(List<UmsComment> comments, Long contentId) {
        List<Long> ids = comments.stream().map(UmsComment::getId).collect(Collectors.toList());
        List<UmsComment> cComments = umsCommentMapper.list(new QueryWrapper<UmsComment>()
                .select(UmsCommentMap.ID.getValue()
                        , UmsCommentMap.USER_ID.getValue()
                        , UmsCommentMap.TARGET_ID.getValue()
                        , UmsCommentMap.CONTENT.getValue()
                        , UmsCommentMap.COMMENT_DATE.getValue()
                        , UmsCommentMap.LIKE.getValue()
                        , UmsCommentMap.STATUS.getValue()
                        , UmsCommentMap.ROOT_ID.getValue()
                        , UmsCommentMap.REPLY_ID.getValue()
                )
                .eq(UmsCommentMap.TARGET_ID.getValue(), contentId)
                .ne(UmsCommentMap.STATUS.getValue(), CommentStatus.REVIEW)
                .in(UmsCommentMap.REPLY_ID.getValue(), ids)
        );
        UmsCommentConverter.INSTANCE.toChildCommentVOs(cComments);
        return null;
    }


}

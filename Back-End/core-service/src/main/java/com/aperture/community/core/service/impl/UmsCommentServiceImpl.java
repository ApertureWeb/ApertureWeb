package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.CommentStatus;
import com.aperture.community.core.common.ContentType;
import com.aperture.community.core.common.map.UmsArticleMap;
import com.aperture.community.core.common.map.UmsCommentMap;
import com.aperture.community.core.common.map.UmsVideoMap;
import com.aperture.community.core.dao.UmsArticleMapper;
import com.aperture.community.core.dao.UmsCommentMapper;
import com.aperture.community.core.dao.UmsVideoMapper;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.module.UmsArticle;
import com.aperture.community.core.module.UmsComment;
import com.aperture.community.core.module.UmsVideo;
import com.aperture.community.core.module.converter.UmsCommentConverter;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsCommentParam;
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
    private UmsArticleMapper umsArticleMapper;
    private UmsVideoMapper umsVideoMapper;


    @Autowired
    public UmsCommentServiceImpl(UmsCommentMapper umsCommentMapper, PrimaryIdManager primaryIdManager,
                                 UmsArticleMapper umsArticleMapper, UmsVideoMapper umsVideoMapper) {
        this.umsCommentMapper = umsCommentMapper;
        this.primaryIdManager = primaryIdManager;
        this.umsArticleMapper = umsArticleMapper;
        this.umsVideoMapper = umsVideoMapper;
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
    public PageVO<UmsCommentVO> listPage(PageParam pageParam, Integer contentId) {
        assert contentId != null;
        assert pageParam != null;
        IPage<UmsComment> page = umsCommentMapper.page(new Page<>(pageParam.getPage(), pageParam.getSize()),
                new QueryWrapper<UmsComment>()
                        .select(UmsCommentMap.ID.getValue()
                                , UmsCommentMap.USER_ID.getValue()
                                , UmsCommentMap.CONTENT.getValue()
                                , UmsCommentMap.COMMENT_DATE.getValue()
                                , UmsCommentMap.LIKE.getValue()

                        )
                        .eq(UmsCommentMap.REPLY_ID.getValue(), 0)
                        .eq(UmsCommentMap.TARGET_ID.getValue(), contentId)


        );
        List<UmsComment> umsComments = page.getRecords();
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
            UmsArticle article = umsArticleMapper.getOne(new QueryWrapper<UmsArticle>().select("1").
                    eq(UmsArticleMap.ID.getValue(), comment.getTargetId()));
            if (article == null) {
                throw new IllegalArgumentException("找不到目标文章");
            }
        } else {
            UmsVideo video = umsVideoMapper.getOne(new QueryWrapper<UmsVideo>().select("1").
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

}

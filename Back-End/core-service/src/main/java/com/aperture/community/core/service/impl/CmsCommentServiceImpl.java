package com.aperture.community.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.aperture.community.core.common.TokenStore;
import com.aperture.community.core.common.map.CmsArticleMap;
import com.aperture.community.core.common.map.CmsCommentMap;
import com.aperture.community.core.common.map.CmsReplyMap;
import com.aperture.community.core.common.map.CmsVideoMap;
import com.aperture.community.core.common.status.CommentStatus;
import com.aperture.community.core.common.status.ContentType;
import com.aperture.community.core.common.status.ReplyStatus;
import com.aperture.community.core.common.utils.NetWorkUtils;
import com.aperture.community.core.manager.ContentManager;
import com.aperture.community.core.manager.InteractCommentManager;
import com.aperture.community.core.manager.PrimaryIdManager;
import com.aperture.community.core.module.*;
import com.aperture.community.core.module.converter.ChildCommentConverter;
import com.aperture.community.core.module.converter.CmsCommentConverter;
import com.aperture.community.core.module.converter.CmsReplyConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.dto.UserDto;
import com.aperture.community.core.module.identify.UserInfo;
import com.aperture.community.core.module.param.CmsCommentParam;
import com.aperture.community.core.module.param.CmsReplyPageParam;
import com.aperture.community.core.module.param.CmsReplyParam;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.vo.ChildCommentVO;
import com.aperture.community.core.module.vo.CmsCommentVO;
import com.aperture.community.core.module.vo.CmsReplyVO;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.service.CmsCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
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


    private InteractCommentManager interactCommentManager;
    private PrimaryIdManager primaryIdManager;
    private ContentManager contentManager;
    private RestTemplate restTemplate;


    @Autowired
    public CmsCommentServiceImpl(InteractCommentManager interactCommentManager, PrimaryIdManager primaryIdManager,
                                 ContentManager contentManager, RestTemplate restTemplate) {
        this.interactCommentManager = interactCommentManager;
        this.primaryIdManager = primaryIdManager;
        this.contentManager = contentManager;
        this.restTemplate = restTemplate;
    }

    @Override
    public CmsCommentVO select(CmsCommentParam cmsCommentParam) {

        return null;
    }

    @Override
    public MessageDto delete(Long id) {
        UserInfo userInfo = TokenStore.getUserInfo();
        CmsCommentEntity cmsCommentEntity = interactCommentManager.getCmsCommentMapper().getOne(new QueryWrapper<CmsCommentEntity>()
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
            result = interactCommentManager.getCmsCommentMapper().update(new UpdateWrapper<CmsCommentEntity>().set(CmsCommentMap.STATUS.getValue(), CommentStatus.FLOD)
                    .eq(CmsCommentMap.ID.getValue(), id)
                    .eq(CmsCommentMap.STATUS.getValue(), CommentStatus.NORMAL.getValue())) ? new MessageDto("success", true) : new MessageDto("删除失败", false);
        }
        if (userInfo.getId().equals(commenterId)) {
            result = interactCommentManager.getCmsCommentMapper().removeById(id) ? new MessageDto("success", true) : new MessageDto("删除失败", false);
        }
        if (result == null) {
            return new MessageDto("无权删除", false);
        }
        //文章所有者只能折叠，comment所有者可以自行删除
        return result;
    }


    /**
     * 按页获取全部comment，每个comment下面按放三个回复（按热度）
     */
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
        IPage<CmsCommentEntity> page = interactCommentManager.getCmsCommentMapper().page(new Page<>(pageParam.getPage(), pageParam.getSize()),
                new QueryWrapper<CmsCommentEntity>()
                        .select(CmsCommentMap.ID.getValue()
                                , CmsCommentMap.USER_ID.getValue()
                                , CmsCommentMap.TARGET_ID.getValue()
                                , CmsCommentMap.CONTENT.getValue()
                                , CmsCommentMap.COMMENT_DATE.getValue()
                                , CmsCommentMap.LIKE.getValue()
                                , CmsCommentMap.STATUS.getValue())
                        .eq(CmsCommentMap.TARGET_ID.getValue(), contentId)
                        .ne(CmsCommentMap.STATUS.getValue(), CommentStatus.REVIEW.getValue())
                        .orderBy(false, asc, field)
        );
        List<CmsCommentEntity> umsCommentEntities = page.getRecords();
        //存放当前页的Comment的Map形式
        Map<Long, CmsCommentVO> commentEntityMap = umsCommentEntities.stream().collect(Collectors.toMap(CmsCommentEntity::getId, CmsCommentConverter.INSTANCE::toCmsCommentVO));
        Set<Long> commentId = umsCommentEntities.stream().map(CmsCommentEntity::getId).collect(Collectors.toSet());
        //TODO 需要基准测试来决定是否开个线程来实现
        //获取回复，每个评论下最多三个，按热度排序
        Map<Long, List<CmsReplyEntity>> replyMap = new HashMap<>((int) ((commentId.size() * 3 / 0.75) + 1));
        Set<Long> replyUserIdSet = new HashSet<>((int) ((commentId.size() * 3 / 0.75) + 1));
        commentId.forEach(p -> {
            List<CmsReplyEntity> innerReplys = interactCommentManager.getCmsReplyMapper().list(new QueryWrapper<CmsReplyEntity>().select(
                    CmsReplyMap.ID.getValue(),
                    CmsReplyMap.LIKE.getValue(),
                    CmsReplyMap.CONTENT.getValue(),
                    CmsReplyMap.USER_ID.getValue(),
                    CmsReplyMap.COMMENT_DATE.getValue(),
                    CmsReplyMap.COMMENT_ID.getValue()
            ).orderByDesc(CmsReplyMap.LIKE.getValue()).ne(CmsReplyMap.STATUS.getValue(), ReplyStatus.REVIEW.getValue())
                    .eq(CmsReplyMap.COMMENT_ID.getValue(), p).last(true, "LIMIT 3"));
            replyMap.put(p, innerReplys);
            replyUserIdSet.addAll(innerReplys.stream().map(CmsReplyEntity::getUserId).collect(Collectors.toSet()));
        });
        //TODO 确定下远程调用 #replyUserIdSet
        String usernames = restTemplate.postForObject("User-Service", replyUserIdSet, String.class);
        List<UserDto> userList = JSON.parseArray(usernames, UserDto.class);
        //存放用户信息，key为UserId
        Map<Long, UserDto> userMap = userList == null ? new HashMap<>(2) : userList.stream().collect(Collectors.toMap(UserDto::getId, value -> value));
        //填充用户信息
        commentEntityMap.values().forEach(e -> {
            e.setUsername(userMap.get(e.getUserId()).getName());
            e.setChildComment(ChildCommentConverter.INSTANCE.toChildCommentVOs(replyMap.get(e.getId())));
            //回复信息填充，最多三个
            e.getChildComment().forEach(c -> c.setUsername(userMap.get(c.getUserId()).getName()));
        });

        return new PageVO<>(page.getTotal(), commentEntityMap.values());
    }

    public MessageDto<Boolean> sendReplay(CmsReplyParam cmsReplyParam) {
        CmsReplyEntity cmsReplyEntity = CmsReplyConverter.INSTANCE.toCmsReply(cmsReplyParam);
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
        CmsCommentEntity comment = CmsCommentConverter.INSTANCE.toCmsComment(cmsCommentParam);
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
        return interactCommentManager.getCmsCommentMapper().save(comment);
    }


    private List<ChildCommentVO> getChildCommentVO(List<CmsCommentEntity> comments, Long contentId) {
        List<Long> ids = comments.stream().map(CmsCommentEntity::getId).collect(Collectors.toList());
        List<CmsCommentEntity> cComments = interactCommentManager.getCmsCommentMapper().list(new QueryWrapper<CmsCommentEntity>()
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
    //需要RR级别的隔离
    public MessageDto sendReply(CmsReplyParam cmsReplyParam) {
        CmsReplyEntity replyEntity = CmsReplyConverter.INSTANCE.toCmsReply(cmsReplyParam);
        replyEntity.setCommentDate(LocalDateTime.now());
        replyEntity.setLike(0);
        replyEntity.setStatus(ReplyStatus.NORMAL.getValue());
        UserInfo userInfo = TokenStore.getUserInfo();
        TokenStore.cleanUserInfo();
        replyEntity.setUserId(userInfo.getId());
        //TODO 用户行为，需要通知目标用户
        //确保CommentId存在
        CmsCommentEntity commentEntity = interactCommentManager.getCmsCommentMapper().getOne(new QueryWrapper<CmsCommentEntity>()
                .select("1")
                .eq(CmsCommentMap.ID.getValue(), cmsReplyParam.getCommentId())
        );
        if (commentEntity == null) {
            new MessageDto("目标评论不存在", false);
        }
        return interactCommentManager.getCmsReplyMapper().save(replyEntity) ? new MessageDto("success", true) : new MessageDto("发生了意外", false);
    }

    /**
     * 查看回复
     */
    public MessageDto<List<CmsReplyVO>> replyPage(CmsReplyPageParam pageParam) {
        //按页获取回复数据
        Page<CmsReplyEntity> replyPage = interactCommentManager.getCmsReplyMapper().page(new Page<>(pageParam.getPage(), pageParam.getSize()), new QueryWrapper<CmsReplyEntity>()
                .select(
                        CmsReplyMap.ID.getValue(),
                        CmsReplyMap.CONTENT.getValue(),
                        CmsReplyMap.COMMENT_DATE.getValue(),
                        CmsReplyMap.USER_ID.getValue(),
                        CmsReplyMap.LIKE.getValue(),
                        CmsReplyMap.ROOT_ID.getValue(),
                        CmsReplyMap.STATUS.getValue()
                ).eq(CmsReplyMap.COMMENT_ID.getValue(), pageParam.getCommentId())
                .ne(CmsReplyMap.STATUS.getValue(), ReplyStatus.REVIEW.getValue())
        );
        List<CmsReplyEntity> replyEntityList = replyPage.getRecords();
        Set<Long> userIdList = replyEntityList.stream().map(CmsReplyEntity::getUserId).collect(Collectors.toSet());
        String nameStr = restTemplate.postForObject(NetWorkUtils.HTTP_PREFIX, userIdList, String.class);
        //用户信息
        Map<Long, UserDto> userDtoMap = Objects.requireNonNullElse(JSON.parseArray(nameStr, UserDto.class), new ArrayList<UserDto>(1))
                .stream().collect(Collectors.toMap(UserDto::getId, value -> value));
        List<CmsReplyVO> result = CmsReplyConverter.INSTANCE.toCmsReplyVOs(replyEntityList);
        result.forEach(replyVO -> {
            UserDto userDto = userDtoMap.get(replyVO.getUserId());
            replyVO.setUsername(userDto.getName());
            replyVO.setIcon(userDto.getIcon());
        });
        return new MessageDto<>("success", result, true);
    }

    /**
     * 查看对话
     */
    public MessageDto<List<CmsReplyVO>> innerReplyPage(CmsReplyPageParam pageParam) {
        //按页获取回复数据
        Page<CmsReplyEntity> replyPage = interactCommentManager.getCmsReplyMapper()
                .page(new Page<>(pageParam.getPage(), pageParam.getSize()), new QueryWrapper<CmsReplyEntity>()
                        .select(
                                CmsReplyMap.ID.getValue(),
                                CmsReplyMap.CONTENT.getValue(),
                                CmsReplyMap.COMMENT_DATE.getValue(),
                                CmsReplyMap.USER_ID.getValue(),
                                CmsReplyMap.LIKE.getValue(),
                                CmsReplyMap.ROOT_ID.getValue(),
                                CmsReplyMap.STATUS.getValue()
                        ).eq(CmsReplyMap.COMMENT_ID.getValue(), pageParam.getCommentId())
                        .eq(CmsReplyMap.ROOT_ID.getValue(), pageParam.getRootId())
                        .ne(CmsReplyMap.STATUS.getValue(), ReplyStatus.REVIEW.getValue())
                );
        List<CmsReplyEntity> replyEntityList = replyPage.getRecords();
        Set<Long> userIdSet = replyEntityList.stream().map(CmsReplyEntity::getUserId).collect(Collectors.toSet());
        //TODO Service
        String userStr = restTemplate.postForObject("", userIdSet, String.class);
        Map<Long, UserDto> userDtoMap = Objects.requireNonNullElse(JSON.parseArray(userStr, UserDto.class), new ArrayList<UserDto>(2))
                .stream().collect(Collectors.toMap(UserDto::getId, value -> value));
        List<CmsReplyVO> result = CmsReplyConverter.INSTANCE.toCmsReplyVOs(replyEntityList);
        result.forEach(p -> {
            UserDto userDto = userDtoMap.get(p.getUserId());
            p.setIcon(userDto.getIcon());
            p.setUsername(userDto.getName());
        });
        return new MessageDto<>("success", result, true);
    }


}

package com.aperture.community.core.manager;

import com.aperture.community.core.dao.UmsCommentMapper;
import com.aperture.community.core.dao.UmsReplyMapper;
import com.aperture.community.core.module.UmsComment;
import com.aperture.community.core.module.UmsReply;
import org.springframework.stereotype.Service;

@Service
public class CommentManager {

    private UmsReplyMapper umsReplyMapper;

    private UmsCommentMapper umsCommentMapper;

    public CommentManager(UmsReplyMapper umsReplyMapper, UmsCommentMapper umsCommentMapper) {
        this.umsCommentMapper = umsCommentMapper;
        this.umsReplyMapper = umsReplyMapper;
    }

    /**
     * 检查评论是否存在
     * */
    public boolean checkCommentExist(Long id) {

        return false;
    }


    public boolean sendReply(UmsReply umsReply) {
        return umsReplyMapper.save(umsReply);
    }

    public boolean sendComment(UmsComment umsComment) {
        return umsCommentMapper.save(umsComment);
    }


    public UmsReplyMapper getUmsReplyMapper() {
        return umsReplyMapper;
    }

    public UmsCommentMapper getUmsCommentMapper() {
        return umsCommentMapper;
    }
}

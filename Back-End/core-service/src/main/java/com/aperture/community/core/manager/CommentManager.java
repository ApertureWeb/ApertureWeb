package com.aperture.community.core.manager;

import com.aperture.community.core.dao.CmsCommentMapper;
import com.aperture.community.core.dao.CmsReplyMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentManager {

    private CmsReplyMapper cmsReplyMapper;

    private CmsCommentMapper cmsCommentMapper;

    public CommentManager(CmsReplyMapper cmsReplyMapper, CmsCommentMapper cmsCommentMapper) {
        this.cmsCommentMapper = cmsCommentMapper;
        this.cmsReplyMapper = cmsReplyMapper;
    }

    /**
     * 检查评论是否存在
     * */
    public boolean checkCommentExist(Long id) {

        return false;
    }



    public CmsReplyMapper getUmsReplyMapper() {
        return cmsReplyMapper;
    }

    public CmsCommentMapper getUmsCommentMapper() {
        return cmsCommentMapper;
    }
}

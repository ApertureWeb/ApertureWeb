package com.aperture.community.core.manager;

import com.aperture.community.core.common.map.UmsCommentMap;
import com.aperture.community.core.dao.UmsCommentMapper;
import com.aperture.community.core.dao.UmsReplyMapper;
import com.aperture.community.core.module.UmsComment;
import com.aperture.community.core.module.UmsReply;
import com.aperture.community.core.module.dto.MessageDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HALOXIAO
 * @since 2020-10-04 14:20
 **/
@Service
public class InteractCommentManager {

    private UmsCommentMapper umsCommentMapper;

    private UmsReplyMapper umsReplyMapper;

    @Autowired
    public InteractCommentManager(UmsCommentMapper umsCommentMapper, UmsReplyMapper umsReplyMapper) {
        this.umsCommentMapper = umsCommentMapper;
        this.umsReplyMapper = umsReplyMapper;
    }


    /**
     * 发送回复
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> sendReply(UmsReply umsReply) {
        assert umsReply != null;
        assert umsReply.getCommentId() != null;
        UmsComment umsComment = umsCommentMapper.getOne(new QueryWrapper<UmsComment>().select("1").eq(UmsCommentMap.ID.getValue(), umsReply.getCommentId()));
        if (umsComment == null) {
            new MessageDto<>("评论不存在或已被删除", false);
        }
        if (!umsReplyMapper.save(umsReply)) {
            return new MessageDto<>("发生意外", false);
        }
        return new MessageDto<>("success", true);
    }



    public UmsCommentMapper getUmsCommentMapper() {
        return umsCommentMapper;
    }

    public UmsReplyMapper getUmsReplyMapper() {
        return umsReplyMapper;
    }
}

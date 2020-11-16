package com.aperture.community.core.manager;

import com.aperture.community.core.common.map.CmsCommentMap;
import com.aperture.community.core.dao.CmsCommentMapper;
import com.aperture.community.core.dao.CmsReplyMapper;
import com.aperture.community.core.module.CmsCommentEntity;
import com.aperture.community.core.module.CmsReplyEntity;
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

    private CmsCommentMapper cmsCommentMapper;

    private CmsReplyMapper cmsReplyMapper;

    @Autowired
    public InteractCommentManager(CmsCommentMapper cmsCommentMapper, CmsReplyMapper cmsReplyMapper) {
        this.cmsCommentMapper = cmsCommentMapper;
        this.cmsReplyMapper = cmsReplyMapper;
    }


    /**
     * 发送回复
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageDto<Boolean> sendReply(CmsReplyEntity cmsReplyEntity) {
        assert cmsReplyEntity != null;
        assert cmsReplyEntity.getCommentId() != null;
        CmsCommentEntity cmsCommentEntity = cmsCommentMapper.getOne(new QueryWrapper<CmsCommentEntity>().select("1").eq(CmsCommentMap.ID.getValue(), cmsReplyEntity.getCommentId()));
        if (cmsCommentEntity == null) {
            new MessageDto<>("评论不存在或已被删除", false);
        }
        if (!cmsReplyMapper.save(cmsReplyEntity)) {
            return new MessageDto<>("发生意外", false);
        }
        return new MessageDto<>("success", true);
    }



    public CmsCommentMapper getCmsCommentMapper() {
        return cmsCommentMapper;
    }

    public CmsReplyMapper getCmsReplyMapper() {
        return cmsReplyMapper;
    }
}

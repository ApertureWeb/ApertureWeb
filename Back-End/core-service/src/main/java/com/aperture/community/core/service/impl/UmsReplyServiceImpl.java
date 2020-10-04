package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.map.UmsReplyMap;
import com.aperture.community.core.manager.InteractCommentManager;
import com.aperture.community.core.module.UmsReply;
import com.aperture.community.core.module.converter.UmsReplyConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsReplyParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsReplyVO;
import com.aperture.community.core.service.IUmsReplyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-10-04 16:57
 **/
public class UmsReplyServiceImpl implements IUmsReplyService {

    public InteractCommentManager interactCommentManager;

    @Autowired
    public UmsReplyServiceImpl(InteractCommentManager interactCommentManager) {
        this.interactCommentManager = interactCommentManager;
    }

    public MessageDto<PageVO<UmsReplyVO>> replyPage(@Valid PageParam<UmsReplyParam> pageParam) {
        IPage<UmsReply> iPage = interactCommentManager.getUmsReplyMapper().page(new Page<>(pageParam.getPage(), pageParam.getSize()),
                new QueryWrapper<UmsReply>().select(
                        UmsReplyMap.ID.getValue(),
                        UmsReplyMap.LIKE.getValue(),
                        UmsReplyMap.CONTENT.getValue(),
                        UmsReplyMap.USER_ID.getValue(),
                        UmsReplyMap.STATUS.getValue(),
                        UmsReplyMap.ROOT_ID.getValue(),
                        UmsReplyMap.COMMENT_DATE.getValue()
                ));
        List<UmsReply> result = iPage.getRecords();
        long size = iPage.getTotal();
//        UmsReplyConverter.INSTANCE.

        return null;
    }

}

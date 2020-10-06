package com.aperture.community.core.service.impl;

import com.aperture.community.core.common.map.CmsReplyMap;
import com.aperture.community.core.manager.InteractCommentManager;
import com.aperture.community.core.module.CmsReplyEntity;
import com.aperture.community.core.module.converter.CmsReplyConverter;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.CmsReplyParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsReplyVO;
import com.aperture.community.core.service.CmsReplyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

/**
 * @author HALOXIAO
 * @since 2020-10-04 16:57
 **/
@Service
public class CmsReplyServiceImpl implements CmsReplyService {

    public InteractCommentManager interactCommentManager;


    @Autowired
    public CmsReplyServiceImpl(InteractCommentManager interactCommentManager) {
        this.interactCommentManager = interactCommentManager;
    }

    public MessageDto<PageVO<CmsReplyVO>> replyPage(@Valid PageParam<CmsReplyParam> pageParam) {
        IPage<CmsReplyEntity> iPage = interactCommentManager.getUmsReplyMapper().page(new Page<>(pageParam.getPage(), pageParam.getSize()),
                new QueryWrapper<CmsReplyEntity>().select(
                        CmsReplyMap.ID.getValue(),
                        CmsReplyMap.LIKE.getValue(),
                        CmsReplyMap.CONTENT.getValue(),
                        CmsReplyMap.USER_ID.getValue(),
                        CmsReplyMap.STATUS.getValue(),
                        CmsReplyMap.ROOT_ID.getValue(),
                        CmsReplyMap.COMMENT_DATE.getValue()
                ));
        List<CmsReplyEntity> result = iPage.getRecords();
        long size = iPage.getTotal();
//        TODO 查找User的Name和各种信息
        CmsReplyConverter.INSTANCE.toUmsReplyVOs(result);
        return null;
    }

}

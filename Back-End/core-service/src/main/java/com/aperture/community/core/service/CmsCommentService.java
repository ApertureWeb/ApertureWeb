package com.aperture.community.core.service;

import com.aperture.community.core.common.status.ContentType;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.CmsCommentParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.CmsCommentVO;

public interface CmsCommentService {

 CmsCommentVO select(CmsCommentParam CmsCommentParam);

 boolean delete(Long id);

 PageVO<CmsCommentVO> commentPage(PageParam pageParam, Integer contentId, boolean isHeat);


 boolean sendComment(CmsCommentParam CmsCommentParam, ContentType type);

}
package com.aperture.community.core.service;

import com.aperture.community.core.common.ContentType;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsCommentParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsCommentVO;
import org.springframework.stereotype.Service;

@Service
public interface  IUmsCommentService{

 UmsCommentVO select(UmsCommentParam UmsCommentParam);

 boolean delete(Long id);

 PageVO<UmsCommentVO> listPage(PageParam pageParam, Integer contentId);


 boolean sendComment(UmsCommentParam UmsCommentParam, ContentType type);

}
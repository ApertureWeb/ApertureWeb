package com.aperture.community.core.service;

import com.aperture.community.core.common.ContentType;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.param.PageParam;
import com.aperture.community.core.module.param.UmsCommentParam;
import com.aperture.community.core.module.param.UmsReplyParam;
import com.aperture.community.core.module.vo.PageVO;
import com.aperture.community.core.module.vo.UmsCommentVO;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public interface  IUmsCommentService{

 UmsCommentVO select(UmsCommentParam UmsCommentParam);

 boolean delete(Long id);

 PageVO<UmsCommentVO> listPage(PageParam pageParam, Integer contentId, boolean isHeat);


 boolean sendComment(UmsCommentParam UmsCommentParam, ContentType type);


 MessageDto<Boolean> sendReply(UmsReplyParam umsReplyParam) throws RemoteException;

}
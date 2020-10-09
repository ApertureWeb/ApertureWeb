package com.aperture.community.core.service;

import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    MessageDto<ImageVO> uploadImage(MultipartFile file);


}

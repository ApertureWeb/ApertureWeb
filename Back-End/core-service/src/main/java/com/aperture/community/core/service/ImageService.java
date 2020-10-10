package com.aperture.community.core.service;

import com.aperture.community.core.common.status.ImageType;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    MessageDto<ImageVO> uploadImage(MultipartFile file, boolean isWatermark);

    MessageDto<Boolean> pictureHandle(MultipartFile file, ImageType type);

    MessageDto<Boolean> Imagecheck(MultipartFile file) throws IOException;


}

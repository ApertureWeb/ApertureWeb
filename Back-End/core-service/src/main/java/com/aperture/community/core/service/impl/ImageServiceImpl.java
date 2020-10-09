package com.aperture.community.core.service.impl;

import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.ImageVO;
import com.aperture.community.core.service.ImageService;
import org.springframework.web.multipart.MultipartFile;

public class ImageServiceImpl implements ImageService {

    @Override
    public MessageDto<ImageVO> uploadImage(MultipartFile file) {
        //  ①需要进行图片压缩
        //  ②存放到对应路径
        //  ③建立图片与文章的连接
        return null;
    }
}

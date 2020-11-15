package com.aperture.community.core.service.impl;

import com.aliyun.oss.OSSClient;
import com.aperture.community.core.common.status.ImageType;
import com.aperture.community.core.config.properties.OSSProperties;
import com.aperture.community.core.module.dto.MessageDto;
import com.aperture.community.core.module.vo.ImageVO;
import com.aperture.community.core.service.ImageService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 */
public class ImageServiceImpl implements ImageService {

    private final OSSProperties ossProperties;


    @Autowired
    public ImageServiceImpl(OSSProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Override
    public MessageDto<ImageVO> uploadImage(MultipartFile file, boolean isWatermark) {

        return null;
    }


    /**
     *
     */
    @Override
    public MessageDto<Boolean> imageCheck(MultipartFile file) throws IOException {
        if (file.getSize() > 3 * FileUtils.ONE_MB) {
            return new MessageDto<>("图像过大", false);
        }
        String type = file.getContentType();
        if (type == null) {
            return new MessageDto<>("图片错误", false);
        }
        if (type.equals(ImageType.PNG.getValue())) {
//            Thumbnails.of(file.getInputStream()).toFile();
        }
        return null;
    }

    @Override
    public MessageDto<Boolean> pictureHandle(MultipartFile file, ImageType type) {

        return null;
    }
}

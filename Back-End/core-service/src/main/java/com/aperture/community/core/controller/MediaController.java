package com.aperture.community.core.controller;

import com.aperture.community.core.service.ImageService;
import com.aperture.community.entity.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author HALOXIAO
 * @since 2020-10-17 10:19
 **/
@RestController
public class MediaController {

    @Autowired
    ImageService imageService;


}

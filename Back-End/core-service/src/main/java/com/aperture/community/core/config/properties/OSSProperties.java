package com.aperture.community.core.config.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HALOXIAO
 * @since 2020-10-17 13:13
 **/
@RestController
@RefreshScope
@Getter
public class OSSProperties {


    @Value("${AccessKeyId}}")
    private String accessKeyId;
    @Value("${AccessKeySecret}")
    private String accessKeySecret;
    @Value("${BucketName}")
    private String bucketName;
    @Value("${Host}")
    private String host;
    @Value("${EndPoint}")
    private String endPoint;
    @Value("${Dir}")
    private String dir;
}

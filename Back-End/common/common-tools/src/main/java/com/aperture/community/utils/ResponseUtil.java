package com.aperture.community.utils;

import com.aperture.community.response.ResultBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: JayV
 * @Date: 2020-9-22 21:53
 * @Description: 未授权统一处理工具类
 */
public class ResponseUtil {

    /**
     * 未授权，返回响应信息
     * @param response
     * @param r
     */
    public static void out(HttpServletResponse response, ResultBean r) {
        ObjectMapper mapper = new ObjectMapper(); // JSON-entity类转换器
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE); // 设置响应体格式为UTF-8
        try {
            mapper.writeValue(response.getWriter(), r);  // 将响应对象 r 转换为json对象，并将该json数据填充到字符输出流Writer中
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
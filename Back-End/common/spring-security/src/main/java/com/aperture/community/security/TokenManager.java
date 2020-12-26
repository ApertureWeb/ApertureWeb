package com.aperture.community.security;

import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther: JayV
 * @Date: 2020-9-22 21:21
 * @Description: token管理类
 */
@Component
public class TokenManager {

    private long tokenExpiration = 24 * 60 * 60 * 60;
    private String tokenSignKey = "aperture";  // 自定义签名

    /**
     * 根据JWT获取token值
     *
     * @param userName
     * @return
     */
    public String createToken(String userName) {
        // 通过Jwts设置token的关联用户名、签名、过期时间、解压方式
        String token = Jwts.builder().setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.ES512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /**
     * 通过token获取到对应的用户
     *
     * @param token
     * @return
     */
    public String getUserFromToken(String token) {
        // 从jwt的主体信息中获取到user
        String user = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return user;
    }

}
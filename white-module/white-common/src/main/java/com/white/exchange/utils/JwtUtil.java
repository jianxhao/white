package com.white.exchange.utils;

import cn.hutool.core.util.ObjectUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author xh
 * @create 2023-08-13  10:07
 */

public class JwtUtil {

    private static final Long JWT_TTL = 30 * 24 * 60 * 60 * 1000L; // default one hours

    private static final String JWT_KEY = "jyh"; // set key clear text

    /**
     *  生成UUID
     */
    private static String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * 生成JWT
     * @param subject token中存放的数据（JSON格式）
     * @return jwt
     */
    public static String createJWT(String subject){
        return createJwt(subject, null);
    }
    /**
     * 生成JWT
     * @param subject token中存放的数据（JSON格式）
     * @param ttlMillis 过期时间，默认 1 小时
     * @return jwt
     */
    public static String createJwt(String subject, Long ttlMillis){
        return createJwt(subject, ttlMillis, null);
    }

    public static String createJwt(String subject, Long ttlMillis, TimeUnit timeUnit){
        if(ObjectUtil.isNotNull(timeUnit)){
            ttlMillis = TimeUnit.MILLISECONDS.convert(ttlMillis, timeUnit);
        }
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttlMillis, getUUID());
        return jwtBuilder.compact();
    }

    /**
     * 解析
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm =SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        // 获取当前时间
        Long currentMillis = System.currentTimeMillis();
        Date now = new Date(currentMillis);
        if(ObjectUtil.isNull(ttlMillis)){
            ttlMillis = JWT_TTL;
        }
        Long endMillis = currentMillis + ttlMillis;
        // 过期时间
        Date end = new Date(endMillis);

        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("xh")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(end);
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return SecretKey
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

}

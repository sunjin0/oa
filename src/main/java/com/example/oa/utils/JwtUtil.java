package com.example.oa.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static String SECRET = "secret"; // 密钥

    // 创建JWT
    public static String createToken(String info) {
        return Jwts.builder()
                .setSubject(info)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    // 验证JWT
    public static Claims verifyToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    // 从JWT中获取用户信息
    public static String getInfo(String token) {
        return verifyToken(token).getSubject();
    }

    // 判断JWT是否过期
    public static boolean isTokenExpired(String token) {
        return verifyToken(token).getExpiration().before(new Date());
    }

}
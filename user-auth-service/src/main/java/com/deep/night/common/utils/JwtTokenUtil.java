package com.deep.night.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtTokenUtil {

    public static String createToken(String email, String tokenSecret, String tokenExpirationTime){

        // 로그인 검증 후 하루동안 인증 가능한 토큰 값 생성
        Key key = Keys.hmacShaKeyFor(tokenSecret.getBytes());
        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(tokenExpirationTime)))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return token;
    }

    public static String isJwtValid(String jwtToken, String secretKey) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        String subject = null;

        try {

            subject = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken).getBody()
                    .getSubject();

        } catch (Exception ex) {
            log.error("jwt error : {}", ex.getMessage());
        }

        return subject;
    }
}

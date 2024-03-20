package com.deep.night.user;

import com.deep.night.config.response.Result;
import com.deep.night.user.dto.AuthUserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthUserService {

    @Value("${token.expiration_time}")
    private String tokenExpirationTime;

    @Value("${token.secret}")
    private String tokenSecret;

    public Result<String> signInToToken(AuthUserDto.SignInReq signInReq){
        // 로그인 검증 후 하루동안 인증 가능한 토큰 값 생성
        Key key = Keys.hmacShaKeyFor(tokenSecret.getBytes());
        String token = Jwts.builder()
            .setSubject(signInReq.getEmail())
            .setExpiration(new Date(System.currentTimeMillis() +
                    Long.parseLong(tokenExpirationTime)))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();

        return new Result<>(token);
    }
}

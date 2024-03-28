package com.deep.night.user;

import com.deep.night.common.utils.JwtTokenUtil;
import com.deep.night.repository.UserRepository;
import com.deep.night.user.dto.AuthDto;
import com.deep.night.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.DuplicateFormatFlagsException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.deep.night.config.response.Result;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${token.expiration_time}")
    private String tokenExpirationTime;

    @Value("${token.secret}")
    private String tokenSecre;

    @Autowired
    private PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final RedisTemplate<String, String> redisTemplate;

    public Result<AuthDto.SignInRes> signIn(AuthDto.SignInReq signInReq){

        Optional.ofNullable(userRepository.findByEmail(signInReq.getEmail())).ifPresent(user -> {
            throw new DuplicateFormatFlagsException(String.format("Username :"+signInReq.getEmail()));
        });

        User saveUser = userRepository.save(signInReq.toEntity(encoder.encode(signInReq.getDnUserPassword())));
        return new Result<>(new AuthDto.SignInRes(saveUser));
    }

    public AuthDto.SignUpRes signUp(AuthDto.SignUpReq signUpReq){
        User user = userRepository.findByEmail(signUpReq.getEmail());

        if(ObjectUtils.isEmpty(user)){
            throw new DuplicateFormatFlagsException(String.format("Username :" + signUpReq.getEmail()));
        }

        // password일치 하는지 여부 확인
        if(!encoder.matches(signUpReq.getDnUserPassword(), user.getDnUserPassword())){
            throw new DuplicateFormatFlagsException("비밀번호가 틀립니다.");// encoder.matches는 암호화된 문자를 입력된 문자와 비교해주는 메서드이다
        }

        String accessToken = JwtTokenUtil.createToken(signUpReq.getEmail(), tokenSecre, String.valueOf(Integer.parseInt(tokenExpirationTime)));
        String refreshToken= JwtTokenUtil.createToken(signUpReq.getEmail(), tokenSecre, String.valueOf(Integer.parseInt(tokenExpirationTime)*14));

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken, signUpReq.getEmail(), Integer.parseInt(tokenExpirationTime)*14, TimeUnit.SECONDS);

        return AuthDto.SignUpRes.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthDto.SignUpRes generate(String refreshToken){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String refreshTokenFlag = valueOperations.get(refreshToken);

        if(ObjectUtils.isEmpty(refreshTokenFlag)){
            throw new DuplicateFormatFlagsException("토큰 값이 유효하지 않습니다.");
        }

        String subject =JwtTokenUtil.isJwtValid(refreshToken, tokenSecre);

        if(ObjectUtils.isEmpty(subject)){
            throw new DuplicateFormatFlagsException("토큰 VALUE 값이 존재 하지 않습니다.");
        }

        User user = userRepository.findByEmail(subject);

        if(ObjectUtils.isEmpty(user)){
            throw new DuplicateFormatFlagsException(String.format("Username :" + user.getEmail()));
        }

        String accessToken = JwtTokenUtil.createToken(user.getEmail(), tokenSecre, String.valueOf(Integer.parseInt(tokenExpirationTime)));

        return AuthDto.SignUpRes.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

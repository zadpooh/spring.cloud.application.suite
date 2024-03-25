package com.deep.night.user;

import com.deep.night.common.utils.JwtTokenUtil;
import com.deep.night.config.EncrypterConfig;
import com.deep.night.config.response.ErrorCode;
import com.deep.night.config.response.Result;
import com.deep.night.repository.UserRepository;
import com.deep.night.user.dto.AuthUserDto;
import com.deep.night.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.DuplicateFormatFlagsException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    @Value("${token.expiration_time}")
    private String tokenExpirationTime;

    @Value("${token.secret}")
    private String tokenSecre;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public Result<AuthUserDto.Res> signIn(AuthUserDto.SignInReq signInReq){

        Optional.ofNullable(userRepository.findByEmail(signInReq.getEmail())).ifPresent(user -> {
            throw new DuplicateFormatFlagsException(String.format("Username :"+signInReq.getEmail()));
            //throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME,String.format("Username :"+request.getUserName()));
        });

        User saveUser2 = userRepository.save(signInReq.toEntity(encoder.encode(signInReq.getDnUserPassword())));
        return new Result<>(new AuthUserDto.Res(saveUser2));
    }

    public Result<String> signUp(AuthUserDto.SignUpReq signUpReq){
        User user = userRepository.findByEmail(signUpReq.getEmail());

        if(ObjectUtils.isEmpty(user)){
            throw new DuplicateFormatFlagsException(String.format("Username :" + signUpReq.getEmail()));
        }
        // password일치 하는지 여부 확인
        if(!encoder.matches(signUpReq.getDnUserPassword(), user.getDnUserPassword())){
            throw new DuplicateFormatFlagsException("비밀번호가 틀립니다.");// encoder.matches는 암호화된 문자를 입력된 문자와 비교해주는 메서드이다
        }

        return new Result<>(JwtTokenUtil.createToken(signUpReq.getEmail(), tokenSecre, tokenExpirationTime));
    }
}

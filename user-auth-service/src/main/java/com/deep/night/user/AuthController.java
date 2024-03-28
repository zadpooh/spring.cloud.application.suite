package com.deep.night.user;

import com.deep.night.client.UserClient;
import com.deep.night.config.response.Result;
import com.deep.night.user.dto.AuthDto;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

@EnableAutoConfiguration
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    @Value("${token.expiration_time}")
    private String tokenExpirationTime;

    @PostMapping("/sign-in")
    public Result<AuthDto.SignInRes> signIn(@RequestBody final AuthDto.SignInReq signInReq){
        return authService.signIn(signInReq);
    }

    @PostMapping("/sign-up")
    public Result<AuthDto.SignUpRes> signUp(@RequestBody final AuthDto.SignUpReq signUpReq
            , HttpServletResponse response){

        AuthDto.SignUpRes signUpRes = authService.signUp(signUpReq);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", signUpRes.getRefreshToken())
                .path("/")
                .sameSite("None")
                .httpOnly(false)
                .secure(false)
                .maxAge(Integer.parseInt(tokenExpirationTime)*14)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return new Result<>(signUpRes);
    }

    @PostMapping("/generate")
    public AuthDto.SignUpRes generate(@RequestHeader("refreshToken") String refreshToken){
        return authService.generate(refreshToken);
    }

}

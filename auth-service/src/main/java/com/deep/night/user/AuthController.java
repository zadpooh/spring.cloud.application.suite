package com.deep.night.user;

import com.deep.night.config.response.Result;
import com.deep.night.user.dto.AuthDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@EnableAutoConfiguration
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/user/sign-in")
    public Result<AuthDto.SignInRes> signIn(@RequestBody final AuthDto.SignInReq signInReq){

        log.info("signInReq : {}", signInReq);

        return authService.signIn(signInReq);
    }

    @PostMapping("/user/sign-up")
    public Result<Map> getBoardDetail(@RequestBody final AuthDto.SignUpReq signUpReq){
        //return UserService.getBoard(dnBoardId);
        log.info("signUpReq : {}", signUpReq);
        Map map = authService.signUp(signUpReq);

        log.info("signUpRes : {}", map);

        return new Result<>(map);
    }

}

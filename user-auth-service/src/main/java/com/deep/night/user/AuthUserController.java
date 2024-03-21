package com.deep.night.user;

import com.deep.night.config.response.Result;
import com.deep.night.user.dto.AuthUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @PostMapping("/user/sign-in")
    public Result getBoardDetail(@RequestBody final AuthUserDto.SignInReq signInReq){

        log.info("signUpReq : {}", signInReq);

        return authUserService.signIn(signInReq);
    }

    @PostMapping("/user/sign-up")
    public Result getBoardDetail(@RequestBody final AuthUserDto.SignUpReq signUpReq){
        //return UserService.getBoard(dnBoardId);
        log.info("signUpReq : {}", signUpReq);

        return authUserService.signUpToToken(signUpReq);
    }

}

package com.deep.night.user;

import com.deep.night.config.response.Result;
import com.deep.night.repository.UserRepository;
import com.deep.night.user.dto.AuthUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@EnableAutoConfiguration
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @PostMapping("/user/sign-in")
    public Result getBoardDetail(@RequestBody final AuthUserDto.SignInReq signInReq){
        //return UserService.getBoard(dnBoardId);
        log.info("signInReq : {}", signInReq);

        return authUserService.signInToToken(signInReq);
    }

}

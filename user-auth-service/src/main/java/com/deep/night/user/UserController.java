package com.deep.night.user;

import com.deep.night.config.response.Result;
import com.deep.night.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@EnableAutoConfiguration
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService UserService;

    @GetMapping("/detail/{dnUserId}")
    public Result<UserDto.Res> getUserDetail(@PathVariable(name = "dnUserId") int dnUserId){
        return UserService.getUserDetail(dnUserId);
    }

}

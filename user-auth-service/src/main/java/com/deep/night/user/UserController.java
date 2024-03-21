package com.deep.night.user;

import com.deep.night.client.dto.BoardDto;
import com.deep.night.config.response.Result;
import com.deep.night.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @GetMapping("/detail/{dnBoardId}")
    public Result<BoardDto.Res> getBoardDetail(@PathVariable(name = "dnBoardId") int dnBoardId,
                                               @RequestHeader("X-Authorization-Id") String email){
        log.info("email : {}", email);

        return UserService.getBoard(dnBoardId);
    }

}

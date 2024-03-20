package com.deep.night.user;

import com.deep.night.client.dto.BoardDto;
import com.deep.night.config.response.Result;
import com.deep.night.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<BoardDto.Res> getBoardDetail(@PathVariable(name = "dnBoardId") int dnBoardId){
        return UserService.getBoard(dnBoardId);
    }

}

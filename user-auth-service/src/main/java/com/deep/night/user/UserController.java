package com.deep.night.user;

import com.deep.night.client.dto.BoardDto;
import com.deep.night.config.response.Result;
import com.deep.night.repository.UserRepository;
import com.deep.night.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@Slf4j
public class UserController {

//    @Value("${server.port}")
//    private String serverPort;
//
//    @Value("${logging.file.name}")
//    private String loggingFileName;

    @Autowired
    UserService UserService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/board/detail/{dnBoardId}")
    public Result<BoardDto.Res> getBoardDetail(@PathVariable(name = "dnBoardId") int dnBoardId){
        return UserService.getBoard(dnBoardId);
    }

}

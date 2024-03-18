package com.deep.night.demo.board;

import com.deep.night.demo.board.domain.Board;
import com.deep.night.demo.board.dto.BoardDto;
import com.deep.night.demo.config.response.Result;
import com.deep.night.demo.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@EnableAutoConfiguration
@RestController
//@RequestMapping("/board")
@Slf4j
public class BoardController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${logging.file.name}")
    private String loggingFileName;

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/list")
    public Result list() throws Exception{
        return new Result<>(serverPort);
    }

    @GetMapping("/board/detail/{id}")
    public Result detail(@PathVariable(name="id") final int id,
                         @RequestHeader HttpHeaders header) throws Exception {
        log.info("header : {}", header);
        Board boardDetail = boardRepository.findById(id).orElse(Board.builder().build());
        return new Result<>(new BoardDto.Res(boardDetail));
    }

    @GetMapping("/board/create")
    public Result detail(@RequestBody final BoardDto.Req req) throws Exception {
        boardRepository.save(req.toEntity());
        return new Result<>("ok");
    }

    @GetMapping("/server")
    public Result server() throws Exception{
        return new Result<>(loggingFileName);
    }
}

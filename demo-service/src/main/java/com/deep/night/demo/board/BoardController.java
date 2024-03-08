package com.deep.night.demo.board;

import com.deep.night.demo.config.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${logging.file.name}")
    private String loggingFileName;

//    @Autowired
//    AlarmProducer alarmProducer;

    @GetMapping("/list")
    public Result list() throws Exception{
//        for(int i =0; i < 100; i++) {
//            alarmProducer.send(AlarmEvent.builder()
//                    .args("args"+i)
//                    .type("type"+i)
//                    .userId("swpark"+i)
//                    .eventName("eventName"+i).build());
//        }
        return new Result<>(serverPort);
    }

    @GetMapping("/server")
    public Result server() throws Exception{
        return new Result<>(loggingFileName);
    }
}

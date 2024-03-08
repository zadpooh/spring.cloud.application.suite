package com.deep.night.demo;

import com.deep.night.demo.config.response.Result;
import com.deep.night.demo.kafka.producer.AlarmProducer;
import com.deep.night.demo.vo.AlarmEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@Slf4j
public class DemoController {

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

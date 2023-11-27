package com.deep.night.demo;

import com.deep.night.demo.config.response.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class DemoController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${logging.file.name}")
    private String loggingFileName;

    private Environment env;

    @GetMapping("/list")
    public Result list() throws Exception{
        return new Result<>(serverPort);
    }

    @GetMapping("/server")
    public Result server() throws Exception{
        return new Result<>(loggingFileName);
    }
}

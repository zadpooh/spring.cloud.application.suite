package com.deep.night.client;

import com.deep.night.config.response.Result;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "mailgun", url = "http://localhost:7733/api/demo")
@Qualifier("board")
public interface BoardClient {
    @GetMapping("/board/detail/{dnBoardId}")
    Result getBoardDetail(@PathVariable(name = "dnBoardId") int dnBoardId);

}

package com.deep.night.client;

import com.deep.night.config.response.Result;
import com.deep.night.user.dto.AuthDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "user.service")
@Qualifier("userFeignClient")
public interface UserClient {

//    @PostMapping("/sign-in")
//    Result<AuthDto.User> signIn(@RequestBody AuthDto.User userReq);
//
//    @PostMapping("/find/mail")
//    Result<AuthDto.User> findByEmail(@RequestParam("email") String email);

}

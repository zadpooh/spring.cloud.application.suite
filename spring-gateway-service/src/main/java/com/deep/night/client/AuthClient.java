package com.deep.night.client;

import com.deep.night.client.dto.AuthDto;
import com.deep.night.config.response.Result;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "auth-service", url = "http://localhost:7733/api/auth")
@Qualifier("auth")
public interface AuthClient {
    @PostMapping("/generate")
    Result<AuthDto.SignUpRes> generate(@RequestHeader("refreshToken") String refreshToken);
}

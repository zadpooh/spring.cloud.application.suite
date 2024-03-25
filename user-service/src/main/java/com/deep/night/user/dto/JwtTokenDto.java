package com.deep.night.user.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class JwtTokenDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;

}

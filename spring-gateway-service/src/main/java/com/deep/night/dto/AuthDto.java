package com.deep.night.dto;


import lombok.*;

public class AuthDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpRes {
        private String accessToken;
        private String refreshToken;

    }

}
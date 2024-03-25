package com.deep.night.client.dto;

import lombok.*;

public class AuthDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq {
        private String email;
        private String dnUserPassword;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpRes {
        private String accessToken;
        private String refreshToken;
    }

}
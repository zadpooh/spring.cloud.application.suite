package com.deep.night.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthUserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignInReq {
        private String email;
        private String dnUserPassword;

        @Builder
        public SignInReq(String email, String dnUserPassword) {
            this.email = email;
            this.dnUserPassword = dnUserPassword;
        }

//        public Board toEntity() {
//            return Board.builder()
//
//                    .build();
//        }

    }

}
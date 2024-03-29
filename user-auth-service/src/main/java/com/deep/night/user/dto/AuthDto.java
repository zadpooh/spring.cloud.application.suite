package com.deep.night.user.dto;

import com.deep.night.user.entity.User;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    public static class SignInReq {

        private String dnUserPassword;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String birthday;
        private String nation;
        private String address;
        private String city;
        private String province;
        private String zipcode;
        private LocalDateTime signInDate;
        private LocalDateTime lastLoginDate;
        private LocalDateTime passwordChageDate;

        public User toEntity(String dnUserPassword) {
            return User.builder()
                    .dnUserPassword(dnUserPassword)
                    .firstName(this.firstName)
                    .lastName(this.lastName)
                    .email(this.email)
                    .phoneNumber(this.phoneNumber)
                    .birthday(this.birthday)
                    .nation(this.nation)
                    .address(this.address)
                    .city(this.city)
                    .province(this.province)
                    .zipcode(this.zipcode)
                    .build();
        }
    }

    @Getter
    public static class SignInRes {
        private String email;
        public SignInRes(User user) {
            this.email = user.getEmail();
        }
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
package com.deep.night.user.dto;

import com.deep.night.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

public class UserDto {

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

        public User toEntity() {
            return User.builder()
                    .dnUserPassword(this.dnUserPassword)
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
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Res {

        private String dnUserPassword;
        private String email;
        private String firstName;
        private String lastName;
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

        public Res(User user) {
            this.dnUserPassword = user.getDnUserPassword();
            this.email = user.getEmail();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.phoneNumber = user.getPhoneNumber();
            this.birthday = user.getBirthday();
            this.nation = user.getNation();
            this.address = user.getAddress();
            this.city = user.getCity();
            this.province = user.getProvince();
            this.zipcode = user.getZipcode();
            this.signInDate = user.getSignInDate();
            this.lastLoginDate = user.getLastLoginDate();
            this.passwordChageDate = user.getPasswordChageDate();
        }
    }

}

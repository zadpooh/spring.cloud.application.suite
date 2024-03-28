package com.deep.night.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "DN_USER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DN_USER_ID", nullable = false)
    @Comment("기본키")
    private int dnUserId;

    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    @Comment("이메일")
    private String email;

    @Column(name = "DN_USER_PASSWORD", nullable = false, length = 100)
    @Comment("비밀번호")
    private String dnUserPassword;

    @Column(name = "FIRST_NAME", nullable = false, length = 10)
    @Comment("성")
    private String firstName;

    @Column(name = "LAST_NAME", length = 30)
    @Comment("이름")
    private String lastName;

    @Column(name = "PHONE_NUMBER", length = 50)
    @Comment("핸드폰번호")
    private String phoneNumber;

    @Column(name = "BIRTHDAY", length = 20)
    @Comment("생년월일")
    private String birthday;

    @Column(name = "NATION", length = 50)
    @Comment("국가")
    private String nation;

    @Column(name = "ADDRESS", length = 100)
    @Comment("주소")
    private String address;

    @Column(name = "CITY", length = 50)
    @Comment("도시")
    private String city;

    @Column(name = "PROVINCE", length = 50)
    @Comment("지방")
    private String province;

    @Column(name = "ZIPCODE", length = 20)
    @Comment("우편번호")
    private String zipcode;

    @CreationTimestamp
    @Column(name = "SIGN_IN_DATE", nullable = false, updatable = false)
    @Comment("가입날짜")
    private LocalDateTime signInDate;

    @CreationTimestamp
    @Column(name = "LAST_LOGIN_DATE", nullable = false)
    @Comment("마지막 로그인 날짜")
    private LocalDateTime lastLoginDate;

    @CreationTimestamp
    @Column(name = "PASSWORD_CHAGE_DATE", nullable = false, updatable = false)
    @Comment("패스워드 변경 날짜")
    private LocalDateTime passwordChageDate;

}
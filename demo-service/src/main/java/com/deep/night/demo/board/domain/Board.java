package com.deep.night.demo.board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "DN_BOARD")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DN_BOARD_ID")
    @Comment("기본키")
    private int dnBoardId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "CREATE_USER_ID", nullable = false)
    private String createUserId;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_USER_ID")
    private String updateUserId;

//    public void updateMyAccount(AccountDto.MyAccountReq dto) {
//        this.address1 = dto.getAddress1();
//        this.address2 = dto.getAddress2();
//        this.zip = dto.getZip();
//    }

}

package com.deep.night.board.dto;

import com.deep.night.board.domain.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

public class BoardDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req {

        private String title;
        private String content;
        private LocalDateTime createDate;
        private String createUserId;

        @Builder
        public Req(String title, String content, LocalDateTime createDate, String createUserId) {
            this.title = title;
            this.content = content;
            this.createDate = createDate;
            this.createUserId = createUserId;
        }

//        public Board toEntity() {
//            return Board.builder()
//                    .title(this.title)
//                    .content(this.content)
//                    .createDate(this.createDate)
//                    .createUserId(this.createUserId)
//                    .build();
//        }
    }

    @Getter
    public static class Res {

        private int dnBoardId;
        private String title;
        private String content;
        private LocalDateTime createDate;
        private String createUserId;
        private LocalDateTime updateDate;
        private String updateUserId;

        public Res(Board dnBoard) {
            this.dnBoardId = dnBoard.getDnBoardId();
            this.title = dnBoard.getTitle();
            this.content = dnBoard.getContent();
            this.createDate = dnBoard.getCreateDate();
            this.createUserId = dnBoard.getCreateUserId();
            this.updateDate = dnBoard.getUpdateDate();
            this.updateUserId = dnBoard.getUpdateUserId();
        }
    }

//    @Getter
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    public static class BoardReq {
//        private String email;
//        private String address2;
//        private String zip;
//
//        @Builder
//        public BoardReq(String email, String fistName, String lastName, String password, String address1, String address2, String zip) {
//            this.email = email;
//            this.address2 = address2;
//            this.zip = zip;
//        }
//
//        public Account toEntity() {
//            return Account.builder()
//                    .email(this.email)
//                    ...
//                    .address2(this.address2)
//                    .zip(this.zip)
//                    .build();
//        }
//    }

}

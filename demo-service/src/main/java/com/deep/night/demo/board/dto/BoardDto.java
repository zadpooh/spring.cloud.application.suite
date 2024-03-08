package com.deep.night.demo.board.dto;

import com.deep.night.demo.board.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

public class BoardDto {

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

package com.deep.night.client.dto;

import lombok.Data;

import java.time.LocalDateTime;

public class BoardDto {

    @Data
    public static class Res {
        private int dnBoardId;
        private String title;
        private String content;
        private LocalDateTime createDate;
        private String createUserId;
        private LocalDateTime updateDate;
        private String updateUserId;
    }
}

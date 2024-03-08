package com.deep.night.demo.vo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmEvent {
    private String userId;
    private String type;
    private String args;
    private String eventName;
}

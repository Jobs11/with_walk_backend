package com.example.with_walk.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreetDTO {
    private String mId;
    private Integer rNum;
    private Timestamp rDate; // ✅ Timestamp
    private LocalDateTime rStartTime; // ✅ LocalDateTime
    private LocalDateTime rEndTime; // ✅ LocalDateTime
    private Double rDistance;
    private String rTime;
    private Double rSpeed;
    private Integer rKcal;
    private String rStatus;

    // Getter, Setter, Constructor는 @Data와 @Builder가 자동 생성
}

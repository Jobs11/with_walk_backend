package com.example.with_walk.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChallengeDTO {
    private Integer cNum;
    private String cTitle;
    private String cDescription;
    private String cType;
    private Integer cTargetValue;
    private String cUnit;
    private LocalDateTime cStartDate;
    private LocalDateTime cEndDate;
    private String cReward;
    private String cStatus;
    private LocalDateTime cCreatedAt;
    private LocalDateTime cUpdatedAt;

    private Integer participantCount;
    private Integer daysLeft;
    private Boolean isJoined;
    private Double progress;
    private Integer currentValue;
}

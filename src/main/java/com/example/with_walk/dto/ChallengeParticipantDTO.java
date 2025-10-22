package com.example.with_walk.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChallengeParticipantDTO {
    private Integer cpNum;
    private Integer cNum;
    private String mId;
    private LocalDateTime cpJoinDate;
    private String cpStatus;
    private LocalDateTime cpCompletedDate;
    private Integer cpCurrentValue;

    private String cTitle;
    private String cReward;
    private Integer cTargetValue;
    private String cUnit;
}

package com.example.with_walk.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BadgeDTO {
    private Integer mbNum;
    private String mId;
    private Integer cNum;
    private String mbBadgeName;
    private LocalDateTime mbEarnedDate;

    private String cTitle;
    private String cDescription;
}

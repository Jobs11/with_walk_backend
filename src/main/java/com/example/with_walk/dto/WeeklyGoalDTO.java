package com.example.with_walk.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyGoalDTO {
    private Integer wgNum; // 주간 목표 번호
    private String mId; // 회원 ID
    private BigDecimal wgGoalKm; // 주간 목표 거리(km)
    private LocalDate wgStartDate; // 해당 주 월요일 날짜
    private LocalDateTime wgCreatedAt; // 생성일시
    private LocalDateTime wgUpdatedAt; // 수정일시
}

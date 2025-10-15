package com.example.with_walk.service;

import java.time.LocalDate;
import java.util.List;

import com.example.with_walk.dto.WeeklyGoalDTO;

public interface WeeklyGoalService {

    // 현재 주간 목표 조회
    WeeklyGoalDTO getCurrentWeeklyGoal(String mId);

    // 주간 목표 설정/수정
    void setWeeklyGoal(String mId, Double goalKm);

    // 주간 목표 삭제
    void deleteWeeklyGoal(String mId);

    // 회원의 모든 주간 목표 조회
    List<WeeklyGoalDTO> getAllWeeklyGoals(String mId);

    // 이번 주 월요일 날짜 계산
    LocalDate getCurrentMondayDate();
}

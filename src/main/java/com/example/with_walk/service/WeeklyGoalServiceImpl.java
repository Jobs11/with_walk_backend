package com.example.with_walk.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.with_walk.dto.WeeklyGoalDTO;
import com.example.with_walk.mapper.WeeklyGoalMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeeklyGoalServiceImpl implements WeeklyGoalService {

    private final WeeklyGoalMapper weeklyGoalMapper;

    @Override
    public WeeklyGoalDTO getCurrentWeeklyGoal(String mId) {
        LocalDate monday = getCurrentMondayDate();
        WeeklyGoalDTO goal = weeklyGoalMapper.getWeeklyGoal(mId, monday);

        // 목표가 없으면 기본값 0km 반환
        if (goal == null) {
            goal = new WeeklyGoalDTO();
            goal.setMId(mId);
            goal.setWgGoalKm(BigDecimal.ZERO);
            goal.setWgStartDate(monday);
        }

        return goal;
    }

    @Override
    @Transactional
    public void setWeeklyGoal(String mId, Double goalKm) {
        LocalDate monday = getCurrentMondayDate();

        WeeklyGoalDTO dto = new WeeklyGoalDTO();
        dto.setMId(mId);
        dto.setWgGoalKm(BigDecimal.valueOf(goalKm));
        dto.setWgStartDate(monday);

        // UPSERT: 있으면 업데이트, 없으면 삽입
        weeklyGoalMapper.insertWeeklyGoal(dto);

        log.info("주간 목표 설정 완료 - mId: {}, goalKm: {}, startDate: {}", mId, goalKm, monday);
    }

    @Override
    @Transactional
    public void deleteWeeklyGoal(String mId) {
        LocalDate monday = getCurrentMondayDate();
        weeklyGoalMapper.deleteWeeklyGoal(mId, monday);
        log.info("주간 목표 삭제 완료 - mId: {}, startDate: {}", mId, monday);
    }

    @Override
    public List<WeeklyGoalDTO> getAllWeeklyGoals(String mId) {
        return weeklyGoalMapper.getAllWeeklyGoals(mId);
    }

    @Override
    public LocalDate getCurrentMondayDate() {
        LocalDate today = LocalDate.now();

        // 오늘이 월요일이면 그대로, 아니면 이번 주 월요일로 이동
        int daysToSubtract = today.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue();

        return today.minusDays(daysToSubtract);
    }
}

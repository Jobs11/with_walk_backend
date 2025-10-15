package com.example.with_walk.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.WeeklyGoalDTO;

@Mapper
public interface WeeklyGoalMapper {

    // 주간 목표 조회
    WeeklyGoalDTO getWeeklyGoal(@Param("mId") String mId, @Param("startDate") LocalDate startDate);

    // 주간 목표 등록 (UPSERT)
    int insertWeeklyGoal(WeeklyGoalDTO dto);

    // 주간 목표 수정
    int updateWeeklyGoal(WeeklyGoalDTO dto);

    // 주간 목표 삭제
    int deleteWeeklyGoal(@Param("mId") String mId, @Param("startDate") LocalDate startDate);

    // 회원의 모든 주간 목표 조회
    List<WeeklyGoalDTO> getAllWeeklyGoals(@Param("mId") String mId);
}

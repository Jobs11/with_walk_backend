package com.example.with_walk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.dto.WeeklyGoalDTO;
import com.example.with_walk.service.WeeklyGoalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/withwalk/goal")
@RequiredArgsConstructor
public class WeeklyGoalController {

    private final WeeklyGoalService weeklyGoalService;

    // 현재 주간 목표 조회
    @GetMapping("/weekly/{mId}")
    public ResponseEntity<WeeklyGoalDTO> getCurrentWeeklyGoal(@PathVariable String mId) {
        log.info("주간 목표 조회 요청 - mId: {}", mId);
        WeeklyGoalDTO goal = weeklyGoalService.getCurrentWeeklyGoal(mId);
        return ResponseEntity.ok(goal);
    }

    // 주간 목표 설정/수정
    @PostMapping("/weekly")
    public ResponseEntity<Map<String, Object>> setWeeklyGoal(@RequestBody Map<String, Object> request) {
        String mId = (String) request.get("mId");
        Double goalKm = Double.parseDouble(request.get("goalKm").toString());

        log.info("주간 목표 설정 요청 - mId: {}, goalKm: {}", mId, goalKm);

        weeklyGoalService.setWeeklyGoal(mId, goalKm);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "주간 목표가 설정되었습니다.");

        return ResponseEntity.ok(response);
    }

    // 주간 목표 삭제
    @DeleteMapping("/weekly/{mId}")
    public ResponseEntity<Map<String, Object>> deleteWeeklyGoal(@PathVariable String mId) {
        log.info("주간 목표 삭제 요청 - mId: {}", mId);

        weeklyGoalService.deleteWeeklyGoal(mId);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "주간 목표가 삭제되었습니다.");

        return ResponseEntity.ok(response);
    }

    // 회원의 모든 주간 목표 히스토리 조회
    @GetMapping("/weekly/history/{mId}")
    public ResponseEntity<List<WeeklyGoalDTO>> getAllWeeklyGoals(@PathVariable String mId) {
        log.info("주간 목표 히스토리 조회 요청 - mId: {}", mId);
        List<WeeklyGoalDTO> goals = weeklyGoalService.getAllWeeklyGoals(mId);
        return ResponseEntity.ok(goals);
    }
}

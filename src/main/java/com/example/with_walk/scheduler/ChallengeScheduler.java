package com.example.with_walk.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.with_walk.mapper.ChallengeMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChallengeScheduler {

    private final ChallengeMapper challengeMapper;

    /**
     * 매일 자정(00:00:00)에 만료된 챌린지 자동 종료
     * cron: 초 분 시 일 월 요일
     */
    @Scheduled(cron = "0 0 0 * * *") // 매일 자정
    @Transactional
    public void expireChallenges() {
        System.out.println("⏰ 챌린지 자동 종료 작업 시작...");

        int count = challengeMapper.updateExpiredChallenges();

        System.out.println("✅ " + count + "개의 챌린지가 종료되었습니다.");
    }

    /**
     * 매시간 정각에 실행 (더 자주 체크하고 싶다면)
     */
    // @Scheduled(cron = "0 0 * * * *") // 매시간
    // @Transactional
    // public void expireChallengesHourly() {
    // System.out.println("⏰ 챌린지 자동 종료 작업 시작 (시간별)...");
    // int count = challengeMapper.updateExpiredChallenges();
    // System.out.println("✅ " + count + "개의 챌린지가 종료되었습니다.");
    // }
}

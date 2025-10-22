package com.example.with_walk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.dto.BadgeDTO;
import com.example.with_walk.dto.ChallengeDTO;
import com.example.with_walk.dto.ChallengeParticipantDTO;
import com.example.with_walk.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/withwalk/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping("/active")
    public ResponseEntity<List<ChallengeDTO>> getActiveChallenges(
            @RequestParam("user_id") String userId) {

        System.out.println("📡 /active 요청 받음 - userId: " + userId);

        List<ChallengeDTO> challenges = challengeService.getActiveChallenges(userId);

        System.out.println("✅ 조회된 챌린지 개수: " + challenges.size());

        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/{cNum}")
    public ResponseEntity<ChallengeDTO> getChallengeDetail(
            @PathVariable Integer cNum,
            @RequestParam("user_id") String userId) {
        ChallengeDTO challenge = challengeService.getChallengeDetail(cNum, userId);
        return ResponseEntity.ok(challenge);
    }

    @PostMapping("/{cNum}/join")
    public ResponseEntity<?> joinChallenge(
            @PathVariable Integer cNum,
            @RequestParam("user_id") String userId) {
        boolean success = challengeService.joinChallenge(cNum, userId);

        if (success) {
            return ResponseEntity.ok("참가 완료");
        } else {
            return ResponseEntity.badRequest().body("이미 참가중입니다");
        }
    }

    @GetMapping("/my/active")
    public ResponseEntity<List<ChallengeParticipantDTO>> getMyActiveChallenges(
            @RequestParam("user_id") String userId) {
        List<ChallengeParticipantDTO> challenges = challengeService.getMyActiveChallenges(userId);
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/my/completed")
    public ResponseEntity<List<ChallengeParticipantDTO>> getMyCompletedChallenges(
            @RequestParam("user_id") String userId) {
        List<ChallengeParticipantDTO> challenges = challengeService.getMyCompletedChallenges(userId);
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/my/badges")
    public ResponseEntity<List<BadgeDTO>> getMyBadges(
            @RequestParam("user_id") String userId) {
        List<BadgeDTO> badges = challengeService.getMyBadges(userId);
        return ResponseEntity.ok(badges);
    }
}

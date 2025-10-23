package com.example.with_walk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // 챌린지 생성 (관리자용)
    @PostMapping("/create")
    public ResponseEntity<?> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        try {
            System.out.println("📝 챌린지 생성 요청: " + challengeDTO.getCTitle());

            boolean success = challengeService.createChallenge(challengeDTO);

            if (success) {
                return ResponseEntity.ok("챌린지 생성 완료");
            } else {
                return ResponseEntity.badRequest().body("챌린지 생성 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    // 챌린지 수정 (관리자용)
    @PutMapping("/{cNum}")
    public ResponseEntity<?> updateChallenge(
            @PathVariable Integer cNum,
            @RequestBody ChallengeDTO challengeDTO) {
        try {
            challengeDTO.setCNum(cNum);
            boolean success = challengeService.updateChallenge(challengeDTO);

            if (success) {
                return ResponseEntity.ok("챌린지 수정 완료");
            } else {
                return ResponseEntity.badRequest().body("챌린지 수정 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    // 챌린지 삭제 (관리자용)
    @DeleteMapping("/{cNum}")
    public ResponseEntity<?> deleteChallenge(@PathVariable Integer cNum) {
        try {
            boolean success = challengeService.deleteChallenge(cNum);

            if (success) {
                return ResponseEntity.ok("챌린지 삭제 완료");
            } else {
                return ResponseEntity.badRequest().body("챌린지 삭제 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }
}

package com.example.with_walk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.dto.RankingDTO;
import com.example.with_walk.dto.StreetDTO;
import com.example.with_walk.service.ChallengeService; // 추가!
import com.example.with_walk.service.StreetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/withwalk/street/")
public class StreetController {

    @Autowired
    StreetService streetService;

    @Autowired
    ChallengeService challengeService; // 추가!

    @PostMapping("/register")
    public void registerStreet(@RequestBody StreetDTO street) {
        // 1. 걷기 기록 저장
        streetService.registerStreet(street);
        log.info("발자국 등록완료: {}", street.getMId());

        // 2. 챌린지 진행상황 업데이트
        try {
            double distanceInKm = street.getRDistance() / 1000.0; // m → km 변환
            challengeService.updateChallengeProgress(street.getMId(), distanceInKm);
            log.info("챌린지 진행상황 업데이트 완료: {}km", distanceInKm);
        } catch (Exception e) {
            log.error("챌린지 업데이트 실패: {}", e.getMessage());
            // 에러가 나도 걷기 기록은 저장되어야 하므로 예외를 throw하지 않음
        }
    }

    @GetMapping("/getlist")
    public List<StreetDTO> getStreetList(@RequestParam("mId") String mId, @RequestParam("rDate") String rDate) {
        List<StreetDTO> streets = streetService.getStreetList(mId, rDate);
        log.info("발자국 불러오기 성공: {}", mId);
        return streets;
    }

    @GetMapping("/getalllist")
    public List<StreetDTO> getStreetAllList(@RequestParam("mId") String mId) {
        List<StreetDTO> streets = streetService.getStreetAllList(mId);
        log.info("발자국 전체 불러오기 성공: {}", mId);
        return streets;
    }

    @PostMapping("/delete")
    public void deleteStreet(@RequestParam("rNum") Integer rNum) {
        streetService.deleteStreet(rNum);
        log.info("발자국 삭제완료: {}", rNum);
    }

    @GetMapping("/ranking/weekly/top3")
    public ResponseEntity<List<RankingDTO>> getWeeklyTop3() {
        List<RankingDTO> ranking = streetService.getWeeklyTop3();
        return ResponseEntity.ok(ranking);
    }
}

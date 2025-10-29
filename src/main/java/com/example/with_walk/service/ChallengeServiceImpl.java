package com.example.with_walk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.with_walk.dto.BadgeDTO;
import com.example.with_walk.dto.ChallengeDTO;
import com.example.with_walk.dto.ChallengeParticipantDTO;
import com.example.with_walk.mapper.ChallengeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeMapper challengeMapper;

    @Override
    public List<ChallengeDTO> getActiveChallenges(String userId) {
        return challengeMapper.selectActiveChallenges(userId);
    }

    @Override
    public ChallengeDTO getChallengeDetail(Integer cNum, String userId) {
        return challengeMapper.selectChallengeById(cNum, userId);
    }

    @Override
    @Transactional
    public boolean joinChallenge(Integer cNum, String userId) {
        ChallengeParticipantDTO existing = challengeMapper.selectParticipantByUserAndChallenge(cNum, userId);

        if (existing != null) {
            return false;
        }

        ChallengeParticipantDTO participant = new ChallengeParticipantDTO();
        participant.setCNum(cNum);
        participant.setMId(userId);
        participant.setCpStatus("진행중");
        participant.setCpCurrentValue(0);

        return challengeMapper.insertParticipant(participant) > 0;
    }

    @Override
    public List<ChallengeParticipantDTO> getMyActiveChallenges(String userId) {
        return challengeMapper.selectMyActiveChallenges(userId);
    }

    @Override
    public List<ChallengeParticipantDTO> getMyCompletedChallenges(String userId) {
        return challengeMapper.selectMyCompletedChallenges(userId);
    }

    @Override
    @Transactional
    public boolean updateChallengeProgress(String userId, double distance) {
        List<ChallengeParticipantDTO> activeChallenges = challengeMapper.selectMyActiveChallenges(userId);

        for (ChallengeParticipantDTO participant : activeChallenges) {
            ChallengeDTO challenge = challengeMapper.selectChallengeById(
                    participant.getCNum(), userId);

            int valueToAdd = 0;

            // 챌린지 타입에 따라 추가값 계산
            switch (challenge.getCType()) {
                case "distance":
                    // 거리 챌린지 (km)
                    valueToAdd = (int) distance;
                    break;

                case "frequency":
                    // 횟수 챌린지 (1회 추가)
                    valueToAdd = 1;
                    break;

                case "duration":
                    // 시간 챌린지는 별도 처리 필요
                    // 여기서는 스킵
                    continue;
            }

            // 현재 진행값 업데이트
            int newValue = participant.getCpCurrentValue() + valueToAdd;
            challengeMapper.updateChallengeProgress(
                    participant.getCpNum(),
                    newValue);

            // 목표 달성 확인
            if (newValue >= challenge.getCTargetValue()) {
                // 챌린지 완료 처리
                challengeMapper.updateChallengeComplete(participant.getCpNum());

                // 뱃지 지급
                BadgeDTO badge = new BadgeDTO();
                badge.setMId(userId);
                badge.setCNum(challenge.getCNum());
                badge.setMbBadgeName(challenge.getCReward());
                challengeMapper.insertBadge(badge);
            }
        }

        return true;
    }

    @Override
    public List<BadgeDTO> getMyBadges(String userId) {
        return challengeMapper.selectMyBadges(userId);
    }

    @Override
    @Transactional
    public boolean createChallenge(ChallengeDTO challengeDTO) {
        return challengeMapper.insertChallenge(challengeDTO) > 0;
    }

    @Override
    @Transactional
    public boolean updateChallenge(ChallengeDTO challengeDTO) {
        return challengeMapper.updateChallenge(challengeDTO) > 0;
    }

    @Override
    @Transactional
    public boolean deleteChallenge(Integer cNum) {
        // 참가자 데이터도 함께 삭제
        challengeMapper.deleteParticipantsByChallenge(cNum);
        return challengeMapper.deleteChallenge(cNum) > 0;
    }
}

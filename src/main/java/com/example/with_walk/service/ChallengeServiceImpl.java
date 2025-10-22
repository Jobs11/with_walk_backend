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

            if ("distance".equals(challenge.getCType())) {
                int newValue = participant.getCpCurrentValue() + (int) distance;
                challengeMapper.updateChallengeProgress(
                        participant.getCpNum(),
                        newValue);

                if (newValue >= challenge.getCTargetValue()) {
                    challengeMapper.updateChallengeComplete(participant.getCpNum());

                    BadgeDTO badge = new BadgeDTO();
                    badge.setMId(userId);
                    badge.setCNum(challenge.getCNum());
                    badge.setMbBadgeName(challenge.getCReward());
                    challengeMapper.insertBadge(badge);
                }
            }
        }

        return true;
    }

    @Override
    public List<BadgeDTO> getMyBadges(String userId) {
        return challengeMapper.selectMyBadges(userId);
    }
}

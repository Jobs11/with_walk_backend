package com.example.with_walk.service;

import java.util.List;

import com.example.with_walk.dto.BadgeDTO;
import com.example.with_walk.dto.ChallengeDTO;
import com.example.with_walk.dto.ChallengeParticipantDTO;

public interface ChallengeService {

    List<ChallengeDTO> getActiveChallenges(String userId);

    ChallengeDTO getChallengeDetail(Integer cNum, String userId);

    boolean joinChallenge(Integer cNum, String userId);

    List<ChallengeParticipantDTO> getMyActiveChallenges(String userId);

    List<ChallengeParticipantDTO> getMyCompletedChallenges(String userId);

    boolean updateChallengeProgress(String userId, double distance);

    List<BadgeDTO> getMyBadges(String userId);
}

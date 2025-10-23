package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.BadgeDTO;
import com.example.with_walk.dto.ChallengeDTO;
import com.example.with_walk.dto.ChallengeParticipantDTO;

@Mapper
public interface ChallengeMapper {

        List<ChallengeDTO> selectActiveChallenges(@Param("userId") String userId);

        ChallengeDTO selectChallengeById(@Param("cNum") Integer cNum, @Param("userId") String userId);

        int insertParticipant(ChallengeParticipantDTO participant);

        ChallengeParticipantDTO selectParticipantByUserAndChallenge(
                        @Param("cNum") Integer cNum,
                        @Param("userId") String userId);

        List<ChallengeParticipantDTO> selectMyActiveChallenges(@Param("userId") String userId);

        List<ChallengeParticipantDTO> selectMyCompletedChallenges(@Param("userId") String userId);

        int updateChallengeProgress(
                        @Param("cpNum") Integer cpNum,
                        @Param("value") Integer value);

        int updateChallengeComplete(@Param("cpNum") Integer cpNum);

        int insertBadge(BadgeDTO badge);

        List<BadgeDTO> selectMyBadges(@Param("userId") String userId);

        int selectParticipantCount(@Param("cNum") Integer cNum);

        // 챌린지 생성
        int insertChallenge(ChallengeDTO challengeDTO);

        // 챌린지 수정
        int updateChallenge(ChallengeDTO challengeDTO);

        // 챌린지 삭제
        int deleteChallenge(@Param("cNum") Integer cNum);

        // 챌린지 참가자 삭제
        int deleteParticipantsByChallenge(@Param("cNum") Integer cNum);

        // 만료된 챌린지 자동 종료
        int updateExpiredChallenges();
}

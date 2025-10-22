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
}

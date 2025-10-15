package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.FriendshipDTO;

@Mapper
public interface FriendMapper {

    // 팔로우 추가
    int insertFollow(FriendshipDTO friendship);

    // 팔로우 삭제
    int deleteFollow(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);

    // 팔로우 존재 여부 확인
    int checkFollowExists(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);

    // 팔로워 수 조회
    int countFollowers(@Param("userId") String userId);

    // 팔로잉 수 조회
    int countFollowing(@Param("userId") String userId);

    // 팔로워 목록 조회
    List<FriendshipDTO> selectFollowers(@Param("userId") String userId);

    // 팔로잉 목록 조회
    List<FriendshipDTO> selectFollowing(@Param("userId") String userId);
}

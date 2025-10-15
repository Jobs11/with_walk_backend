package com.example.with_walk.service;

import java.util.List;

import com.example.with_walk.dto.FriendshipDTO;

public interface FriendService {

    // 팔로우 하기
    int followUser(String fromUserId, String toUserId);

    // 언팔로우 하기
    int unfollowUser(String fromUserId, String toUserId);

    // 팔로워 수 조회
    int getFollowerCount(String userId);

    // 팔로잉 수 조회
    int getFollowingCount(String userId);

    // 팔로우 상태 확인
    boolean isFollowing(String fromUserId, String toUserId);

    // 팔로워 목록 조회
    List<FriendshipDTO> getFollowers(String userId);

    // 팔로잉 목록 조회
    List<FriendshipDTO> getFollowing(String userId);
}

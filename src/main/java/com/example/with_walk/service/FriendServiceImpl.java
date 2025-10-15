package com.example.with_walk.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.with_walk.dto.FriendshipDTO;
import com.example.with_walk.mapper.FriendMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendMapper friendMapper;

    @Override
    @Transactional
    public int followUser(String fromUserId, String toUserId) {
        // 자기 자신을 팔로우할 수 없음
        if (fromUserId.equals(toUserId)) {
            throw new RuntimeException("자기 자신을 팔로우할 수 없습니다");
        }

        // 이미 팔로우 중인지 확인
        int exists = friendMapper.checkFollowExists(fromUserId, toUserId);
        if (exists > 0) {
            throw new RuntimeException("이미 팔로우 중입니다");
        }

        FriendshipDTO friendship = FriendshipDTO.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .status("accepted")
                .createdAt(LocalDateTime.now())
                .build();

        return friendMapper.insertFollow(friendship);
    }

    @Override
    @Transactional
    public int unfollowUser(String fromUserId, String toUserId) {
        return friendMapper.deleteFollow(fromUserId, toUserId);
    }

    @Override
    public int getFollowerCount(String userId) {
        return friendMapper.countFollowers(userId);
    }

    @Override
    public int getFollowingCount(String userId) {
        return friendMapper.countFollowing(userId);
    }

    @Override
    public boolean isFollowing(String fromUserId, String toUserId) {
        int exists = friendMapper.checkFollowExists(fromUserId, toUserId);
        return exists > 0;
    }

    @Override
    public List<FriendshipDTO> getFollowers(String userId) {
        return friendMapper.selectFollowers(userId);
    }

    @Override
    public List<FriendshipDTO> getFollowing(String userId) {
        return friendMapper.selectFollowing(userId);
    }
}

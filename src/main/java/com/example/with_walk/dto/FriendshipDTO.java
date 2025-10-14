package com.example.with_walk.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendshipDTO {
    private Integer fNum;
    private String fromUserId;
    private String toUserId;
    private String status; // pending, accepted, rejected
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package com.example.with_walk.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyLikeDTO {
    private Integer replyLikeId;
    private Integer replyId;
    private String mId;
    private Timestamp createdAt;
}

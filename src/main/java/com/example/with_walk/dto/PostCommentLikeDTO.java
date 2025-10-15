package com.example.with_walk.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentLikeDTO {
    private Integer pclNum; // 댓글 좋아요 번호 (PK)
    private Integer pcNum; // 댓글 번호 (FK)
    private String mId; // 회원 ID (FK)
    private LocalDateTime pclDate; // 좋아요 날짜
}

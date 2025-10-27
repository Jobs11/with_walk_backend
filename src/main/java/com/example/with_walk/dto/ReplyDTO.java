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
public class ReplyDTO {
    private Integer replyId;
    private Integer postId;
    private Integer parentReplyId; // NULL이면 일반 댓글, 값이 있으면 대댓글
    private String mId;
    private String mName; // JOIN으로 가져올 작성자 이름
    private String mNickname; // JOIN으로 가져올 작성자 닉네임
    private String mProfileImage; // JOIN으로 가져올 프로필 이미지
    private String replyContent;
    private Timestamp replyDate;
    private String replyStatus;
    private Integer likeCount;
    private Boolean isLiked; // 현재 사용자가 좋아요 눌렀는지 여부
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

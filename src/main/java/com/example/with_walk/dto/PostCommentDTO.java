package com.example.with_walk.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDTO {
    private Integer pcNum;
    private Integer pNum;
    private Integer parentPcNum; // ✅ 추가
    private String mId;
    private String pcContent;
    private LocalDateTime pcDate;

    private String mNickname;
    private String mProfileImage;

    // 조회용 추가 필드
    private String authorName;
    private String authorImage;

    // 좋아요 관련 필드 추가
    private Integer likeCount; // 좋아요 개수
    private Boolean isLiked; // 현재 사용자의 좋아요 여부

    private Boolean isLikedByAuthor;

    // ✅ 추가
    private List<PostCommentDTO> childComments; // 대댓글 리스트
}

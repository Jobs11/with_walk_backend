package com.example.with_walk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // ✅ 기본 생성자 추가
@AllArgsConstructor // ✅ 전체 매개변수 생성자 추가 (Builder가 이것을 사용)
public class PostDTO {
    private Integer pNum;
    private String mId;
    private Integer rNum;
    private String pContent;
    private String pImage;
    private String pDate;
    private Integer pLikes;

    // 조회용 추가 필드
    private String authorName;
    private String authorImage;
    private Integer likeCount;
    private Integer commentCount;
    private Boolean isLikedByUser;
}

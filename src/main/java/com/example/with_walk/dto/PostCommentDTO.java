package com.example.with_walk.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCommentDTO {
    private Integer pcNum;
    private Integer pNum;
    private String mId;
    private String pcContent;
    private LocalDateTime pcDate;

    // 조회용 추가 필드
    private String authorName;
    private String authorImage;
}

package com.example.with_walk.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDTO {
    private Integer inquiryId;
    private String userId;
    private String category;
    private String title;
    private String content;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 조회용 추가 필드
    private String replyContent;
    private LocalDateTime replyDate;
}

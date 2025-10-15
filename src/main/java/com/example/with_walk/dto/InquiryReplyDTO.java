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
public class InquiryReplyDTO {
    private Integer replyId;
    private Integer inquiryId;
    private String adminId;
    private String content;
    private LocalDateTime createdAt;
}

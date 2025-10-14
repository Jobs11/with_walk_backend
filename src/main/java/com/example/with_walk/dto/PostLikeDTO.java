package com.example.with_walk.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostLikeDTO {
    private Integer plNum;
    private Integer pNum;
    private String mId;
    private LocalDateTime createdAt;
}

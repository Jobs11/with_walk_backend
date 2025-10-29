package com.example.with_walk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostHashtagDTO {
    private Integer phNum; // 연결 번호
    private Integer pNum; // 게시글 번호
    private Integer hNum; // 해시태그 번호
}

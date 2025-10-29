package com.example.with_walk.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HashtagDTO {
    private Integer hNum; // 해시태그 번호
    private String hName; // 해시태그 이름
    private Integer hCount; // 사용 횟수
    private Timestamp createdAt; // 생성일
}

package com.example.with_walk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingDTO {
    private String mId; // 사용자 ID
    private String mName; // 사용자 이름 (JOIN 필요)
    private Double totalDistance; // 이번 주 총 거리
    private Integer rank; // 순위 (1, 2, 3)
}

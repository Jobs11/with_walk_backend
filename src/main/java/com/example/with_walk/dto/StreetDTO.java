package com.example.with_walk.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreetDTO {
    private String m_id;
    private Integer r_num;
    private Timestamp r_date; // ✅ Timestamp로 변경
    private LocalDateTime r_start_time; // ✅ LocalDateTime으로 변경
    private LocalDateTime r_end_time; // ✅ LocalDateTime으로 변경
    private Double r_distance;
    private String r_time;
    private Double r_speed;
    private Integer r_kcal;
    private String r_status;

    // Getter, Setter, Constructor...
}

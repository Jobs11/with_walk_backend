package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {

    // 전체 공지사항 조회
    List<NoticeDTO> selectAllNotices(@Param("offset") int offset, @Param("limit") int limit);

    // 카테고리별 공지사항 조회
    List<NoticeDTO> selectNoticesByCategory(
            @Param("category") String category,
            @Param("offset") int offset,
            @Param("limit") int limit);

    // 공지사항 상세 조회
    NoticeDTO selectNoticeById(@Param("noticeId") Integer noticeId);

    // 조회수 증가
    int updateViewCount(@Param("noticeId") Integer noticeId);

    // 공지사항 등록 (관리자용)
    int insertNotice(NoticeDTO notice);

    // 공지사항 수정 (관리자용)
    int updateNotice(NoticeDTO notice);

    // 공지사항 삭제 (관리자용)
    int deleteNotice(@Param("noticeId") Integer noticeId);
}

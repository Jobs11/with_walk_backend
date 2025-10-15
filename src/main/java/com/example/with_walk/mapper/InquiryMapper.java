package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.InquiryDTO;
import com.example.with_walk.dto.InquiryReplyDTO;

@Mapper
public interface InquiryMapper {

    // 사용자별 문의 목록 조회
    List<InquiryDTO> selectInquiriesByUser(@Param("userId") String userId);

    // 전체 문의 목록 조회 (관리자용)
    List<InquiryDTO> selectAllInquiries();

    // 문의 상세 조회 (답변 포함)
    InquiryDTO selectInquiryById(@Param("inquiryId") Integer inquiryId);

    // 문의 등록
    int insertInquiry(InquiryDTO inquiry);

    // 문의 삭제
    int deleteInquiry(@Param("inquiryId") Integer inquiryId);

    // 문의 상태 변경
    int updateInquiryStatus(
            @Param("inquiryId") Integer inquiryId,
            @Param("status") String status);

    // 답변 등록
    int insertReply(InquiryReplyDTO reply);

    // 답변 조회
    InquiryReplyDTO selectReplyByInquiryId(@Param("inquiryId") Integer inquiryId);

    // 답변 대기 중인 문의 개수
    int countPendingInquiries();
}

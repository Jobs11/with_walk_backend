package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.FaqDTO;

@Mapper
public interface FaqMapper {

    // 전체 FAQ 조회
    List<FaqDTO> selectAllFaqs();

    // 카테고리별 FAQ 조회
    List<FaqDTO> selectFaqsByCategory(@Param("category") String category);

    // FAQ 검색
    List<FaqDTO> searchFaqs(@Param("keyword") String keyword);

    // FAQ 상세 조회
    FaqDTO selectFaqById(@Param("faqId") Integer faqId);

    // 조회수 증가
    int updateViewCount(@Param("faqId") Integer faqId);

    // FAQ 등록 (관리자용)
    int insertFaq(FaqDTO faq);

    // FAQ 수정 (관리자용)
    int updateFaq(FaqDTO faq);

    // FAQ 삭제 (관리자용)
    int deleteFaq(@Param("faqId") Integer faqId);
}

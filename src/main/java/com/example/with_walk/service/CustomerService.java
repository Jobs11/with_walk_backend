package com.example.with_walk.service;

import java.util.List;

import com.example.with_walk.dto.FaqDTO;
import com.example.with_walk.dto.InquiryDTO;
import com.example.with_walk.dto.InquiryReplyDTO;
import com.example.with_walk.dto.NoticeDTO;

public interface CustomerService {

    // 공지사항
    List<NoticeDTO> getAllNotices(int page, int size);

    List<NoticeDTO> getNoticesByCategory(String category, int page, int size);

    NoticeDTO getNoticeDetail(Integer noticeId);

    // ✅ 새로 추가
    int insertNotice(NoticeDTO notice);

    // ✅ 새로 추가
    int updateNotice(NoticeDTO notice);

    // ✅ 새로 추가
    int deleteNotice(Integer noticeId);

    // FAQ
    List<FaqDTO> getAllFaqs();

    List<FaqDTO> getFaqsByCategory(String category);

    List<FaqDTO> searchFaqs(String keyword);

    FaqDTO getFaqDetail(Integer faqId);

    // 문의
    List<InquiryDTO> getUserInquiries(String userId);

    List<InquiryDTO> getAllInquiries(); // 관리자용

    InquiryDTO getInquiryDetail(Integer inquiryId);

    int createInquiry(InquiryDTO inquiry);

    int deleteInquiry(Integer inquiryId);

    // 답변 (관리자용)
    int replyToInquiry(InquiryReplyDTO reply);

    int getPendingInquiryCount();
}

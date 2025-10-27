package com.example.with_walk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.with_walk.dto.FaqDTO;
import com.example.with_walk.dto.InquiryDTO;
import com.example.with_walk.dto.InquiryReplyDTO;
import com.example.with_walk.dto.NoticeDTO;
import com.example.with_walk.mapper.FaqMapper;
import com.example.with_walk.mapper.InquiryMapper;
import com.example.with_walk.mapper.NoticeMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final NoticeMapper noticeMapper;
    private final FaqMapper faqMapper;
    private final InquiryMapper inquiryMapper;

    // ========== 공지사항 ==========
    @Override
    public List<NoticeDTO> getAllNotices(int page, int size) {
        int offset = (page - 1) * size;
        return noticeMapper.selectAllNotices(offset, size);
    }

    @Override
    public List<NoticeDTO> getNoticesByCategory(String category, int page, int size) {
        int offset = (page - 1) * size;
        return noticeMapper.selectNoticesByCategory(category, offset, size);
    }

    @Override
    @Transactional
    public NoticeDTO getNoticeDetail(Integer noticeId) {
        // 조회수 증가
        noticeMapper.updateViewCount(noticeId);
        return noticeMapper.selectNoticeById(noticeId);
    }

    // ✅ 공지사항 등록
    @Override
    public int insertNotice(NoticeDTO notice) {
        return noticeMapper.insertNotice(notice);
    }

    // ✅ 공지사항 수정
    @Override
    public int updateNotice(NoticeDTO notice) {
        return noticeMapper.updateNotice(notice);
    }

    // ✅ 공지사항 삭제
    @Override
    public int deleteNotice(Integer noticeId) {
        return noticeMapper.deleteNotice(noticeId);
    }

    // ========== FAQ ==========
    @Override
    public List<FaqDTO> getAllFaqs() {
        return faqMapper.selectAllFaqs();
    }

    @Override
    public List<FaqDTO> getFaqsByCategory(String category) {
        return faqMapper.selectFaqsByCategory(category);
    }

    @Override
    public List<FaqDTO> searchFaqs(String keyword) {
        return faqMapper.searchFaqs(keyword);
    }

    @Override
    @Transactional
    public FaqDTO getFaqDetail(Integer faqId) {
        // 조회수 증가
        faqMapper.updateViewCount(faqId);
        return faqMapper.selectFaqById(faqId);
    }

    // ========== 문의 ==========
    @Override
    public List<InquiryDTO> getUserInquiries(String userId) {
        return inquiryMapper.selectInquiriesByUser(userId);
    }

    @Override
    public List<InquiryDTO> getAllInquiries() {
        return inquiryMapper.selectAllInquiries();
    }

    @Override
    public InquiryDTO getInquiryDetail(Integer inquiryId) {
        return inquiryMapper.selectInquiryById(inquiryId);
    }

    @Override
    @Transactional
    public int createInquiry(InquiryDTO inquiry) {
        return inquiryMapper.insertInquiry(inquiry);
    }

    @Override
    @Transactional
    public int deleteInquiry(Integer inquiryId) {
        return inquiryMapper.deleteInquiry(inquiryId);
    }

    // ========== 답변 (관리자용) ==========
    @Override
    @Transactional
    public int replyToInquiry(InquiryReplyDTO reply) {
        // 답변 등록
        int result = inquiryMapper.insertReply(reply);

        // 문의 상태를 '답변완료'로 변경
        if (result > 0) {
            inquiryMapper.updateInquiryStatus(reply.getInquiryId(), "답변완료");
        }

        return result;
    }

    @Override
    public int getPendingInquiryCount() {
        return inquiryMapper.countPendingInquiries();
    }
}

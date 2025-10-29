package com.example.with_walk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.dto.FaqDTO;
import com.example.with_walk.dto.InquiryDTO;
import com.example.with_walk.dto.InquiryReplyDTO;
import com.example.with_walk.dto.NoticeDTO;
import com.example.with_walk.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/withwalk/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerCenterController {

    private final CustomerService customerService;

    // ========================================
    // 공지사항 API
    // ========================================

    /**
     * 전체 공지사항 조회
     * GET /customer/notices
     */
    @GetMapping("/notices")
    public ResponseEntity<?> getAllNotices(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        try {
            List<NoticeDTO> notices = customerService.getAllNotices(page, size);
            return ResponseEntity.ok(notices);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 카테고리별 공지사항 조회
     * GET /customer/notices/category/{category}
     */
    @GetMapping("/notices/category/{category}")
    public ResponseEntity<?> getNoticesByCategory(
            @PathVariable String category,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        try {
            List<NoticeDTO> notices = customerService.getNoticesByCategory(category, page, size);
            return ResponseEntity.ok(notices);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 공지사항 상세 조회
     * GET /customer/notices/{noticeId}
     */
    @GetMapping("/notices/{noticeId}")
    public ResponseEntity<?> getNoticeDetail(@PathVariable Integer noticeId) {
        try {
            NoticeDTO notice = customerService.getNoticeDetail(noticeId);
            if (notice != null) {
                return ResponseEntity.ok(notice);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("공지사항을 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ 공지사항 등록 (관리자용)
     * POST /customer/notices
     */
    @PostMapping("/notices")
    public ResponseEntity<?> createNotice(@RequestBody NoticeDTO notice) {
        try {
            int result = customerService.insertNotice(notice);

            if (result > 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "공지사항이 등록되었습니다");
                response.put("notice_id", notice.getNoticeId());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("공지사항 등록에 실패했습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ 공지사항 수정 (관리자용)
     * PUT /customer/notices/{noticeId}
     */
    @PutMapping("/notices/{noticeId}")
    public ResponseEntity<?> updateNotice(
            @PathVariable Integer noticeId,
            @RequestBody NoticeDTO notice) {
        try {
            notice.setNoticeId(noticeId);
            int result = customerService.updateNotice(notice);

            if (result > 0) {
                return ResponseEntity.ok("공지사항이 수정되었습니다");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("공지사항을 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ 공지사항 삭제 (관리자용)
     * DELETE /customer/notices/{noticeId}
     */
    @DeleteMapping("/notices/{noticeId}")
    public ResponseEntity<?> deleteNotice(@PathVariable Integer noticeId) {
        try {
            int result = customerService.deleteNotice(noticeId);

            if (result > 0) {
                return ResponseEntity.ok("공지사항이 삭제되었습니다");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("공지사항을 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    // ========================================
    // FAQ API
    // ========================================

    /**
     * 전체 FAQ 조회
     * GET /customer/faqs
     */
    @GetMapping("/faqs")
    public ResponseEntity<?> getAllFaqs() {
        try {
            List<FaqDTO> faqs = customerService.getAllFaqs();
            return ResponseEntity.ok(faqs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 카테고리별 FAQ 조회
     * GET /customer/faqs/category/{category}
     */
    @GetMapping("/faqs/category/{category}")
    public ResponseEntity<?> getFaqsByCategory(@PathVariable String category) {
        try {
            List<FaqDTO> faqs = customerService.getFaqsByCategory(category);
            return ResponseEntity.ok(faqs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * FAQ 검색
     * GET /customer/faqs/search
     */
    @GetMapping("/faqs/search")
    public ResponseEntity<?> searchFaqs(@RequestParam("keyword") String keyword) {
        try {
            List<FaqDTO> faqs = customerService.searchFaqs(keyword);
            return ResponseEntity.ok(faqs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * FAQ 상세 조회
     * GET /customer/faqs/{faqId}
     */
    @GetMapping("/faqs/{faqId}")
    public ResponseEntity<?> getFaqDetail(@PathVariable Integer faqId) {
        try {
            FaqDTO faq = customerService.getFaqDetail(faqId);
            if (faq != null) {
                return ResponseEntity.ok(faq);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("FAQ를 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ FAQ 등록 (관리자용)
     * POST /customer/faqs
     */
    @PostMapping("/faqs")
    public ResponseEntity<?> createFaq(@RequestBody FaqDTO faq) {
        try {
            int result = customerService.insertFaq(faq);

            if (result > 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "FAQ가 등록되었습니다");
                response.put("faq_id", faq.getFaqId());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("FAQ 등록에 실패했습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ FAQ 수정 (관리자용)
     * PUT /customer/faqs/{faqId}
     */
    @PutMapping("/faqs/{faqId}")
    public ResponseEntity<?> updateFaq(
            @PathVariable Integer faqId,
            @RequestBody FaqDTO faq) {
        try {
            faq.setFaqId(faqId);
            int result = customerService.updateFaq(faq);

            if (result > 0) {
                return ResponseEntity.ok("FAQ가 수정되었습니다");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("FAQ를 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ FAQ 삭제 (관리자용)
     * DELETE /customer/faqs/{faqId}
     */
    @DeleteMapping("/faqs/{faqId}")
    public ResponseEntity<?> deleteFaq(@PathVariable Integer faqId) {
        try {
            int result = customerService.deleteFaq(faqId);

            if (result > 0) {
                return ResponseEntity.ok("FAQ가 삭제되었습니다");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("FAQ를 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    // ========================================
    // 1:1 문의 API
    // ========================================

    /**
     * 사용자별 문의 목록 조회
     * GET /customer/inquiries/{userId}
     */
    @GetMapping("/inquiries/{userId}")
    public ResponseEntity<?> getUserInquiries(@PathVariable String userId) {
        try {
            List<InquiryDTO> inquiries = customerService.getUserInquiries(userId);
            return ResponseEntity.ok(inquiries);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 전체 문의 목록 조회 (관리자용)
     * GET /customer/inquiries
     */
    @GetMapping("/inquiries")
    public ResponseEntity<?> getAllInquiries() {
        try {
            List<InquiryDTO> inquiries = customerService.getAllInquiries();
            return ResponseEntity.ok(inquiries);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 문의 상세 조회
     * GET /customer/inquiry/{inquiryId}
     */
    @GetMapping("/inquiry/{inquiryId}")
    public ResponseEntity<?> getInquiryDetail(@PathVariable Integer inquiryId) {
        try {
            InquiryDTO inquiry = customerService.getInquiryDetail(inquiryId);
            if (inquiry != null) {
                return ResponseEntity.ok(inquiry);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("문의를 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 문의 등록
     * POST /customer/inquiry
     */
    @PostMapping("/inquiry")
    public ResponseEntity<?> createInquiry(@RequestBody InquiryDTO inquiry) {
        try {
            int result = customerService.createInquiry(inquiry);

            if (result > 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "문의가 등록되었습니다");
                response.put("inquiry_id", inquiry.getInquiryId());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("문의 등록에 실패했습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 문의 삭제
     * DELETE /customer/inquiry/{inquiryId}
     */
    @DeleteMapping("/inquiry/{inquiryId}")
    public ResponseEntity<?> deleteInquiry(@PathVariable Integer inquiryId) {
        try {
            int result = customerService.deleteInquiry(inquiryId);

            if (result > 0) {
                return ResponseEntity.ok("문의가 삭제되었습니다");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("문의를 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    // ========================================
    // 답변 API (관리자용)
    // ========================================

    /**
     * 문의 답변 등록
     * POST /customer/reply
     */
    @PostMapping("/reply")
    public ResponseEntity<?> replyToInquiry(@RequestBody InquiryReplyDTO reply) {
        try {
            int result = customerService.replyToInquiry(reply);

            if (result > 0) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("답변이 등록되었습니다");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("답변 등록에 실패했습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 답변 대기 중인 문의 개수
     * GET /customer/pending-count
     */
    @GetMapping("/pending-count")
    public ResponseEntity<?> getPendingInquiryCount() {
        try {
            int count = customerService.getPendingInquiryCount();
            Map<String, Object> response = new HashMap<>();
            response.put("pending_count", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }
}

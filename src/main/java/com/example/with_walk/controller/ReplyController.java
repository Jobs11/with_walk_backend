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

import com.example.with_walk.dto.ReplyDTO;
import com.example.with_walk.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/withwalk/reply")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReplyController {

    private final ReplyService replyService;

    // ========================================
    // 댓글 CRUD
    // ========================================

    /**
     * 댓글 등록
     * POST /reply
     */
    @PostMapping
    public ResponseEntity<?> createReply(@RequestBody ReplyDTO reply) {
        try {
            int result = replyService.createReply(reply);

            if (result > 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "댓글이 등록되었습니다");
                response.put("reply_id", reply.getReplyId());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("댓글 등록에 실패했습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 댓글 수정
     * PUT /reply/{replyId}
     */
    @PutMapping("/{replyId}")
    public ResponseEntity<?> updateReply(
            @PathVariable Integer replyId,
            @RequestBody ReplyDTO reply) {
        try {
            reply.setReplyId(replyId);
            int result = replyService.updateReply(reply);

            if (result > 0) {
                return ResponseEntity.ok("댓글이 수정되었습니다");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("댓글을 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 댓글 삭제
     * DELETE /reply/{replyId}
     */
    @DeleteMapping("/{replyId}")
    public ResponseEntity<?> deleteReply(@PathVariable Integer replyId) {
        try {
            int result = replyService.deleteReply(replyId);

            if (result > 0) {
                return ResponseEntity.ok("댓글이 삭제되었습니다");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("댓글을 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 게시글의 모든 댓글 조회
     * GET /reply/post/{postId}
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getRepliesByPostId(
            @PathVariable Integer postId,
            @RequestParam(required = false) String userId) {
        try {
            List<ReplyDTO> replies = replyService.getRepliesByPostId(postId, userId);
            return ResponseEntity.ok(replies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 댓글 상세 조회
     * GET /reply/{replyId}
     */
    @GetMapping("/{replyId}")
    public ResponseEntity<?> getReplyById(
            @PathVariable Integer replyId,
            @RequestParam(required = false) String userId) {
        try {
            ReplyDTO reply = replyService.getReplyById(replyId, userId);

            if (reply != null) {
                return ResponseEntity.ok(reply);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("댓글을 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 사용자가 작성한 댓글 목록
     * GET /reply/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRepliesByUserId(@PathVariable String userId) {
        try {
            List<ReplyDTO> replies = replyService.getRepliesByUserId(userId);
            return ResponseEntity.ok(replies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 댓글 개수 조회
     * GET /reply/count/{postId}
     */
    @GetMapping("/count/{postId}")
    public ResponseEntity<?> getReplyCount(@PathVariable Integer postId) {
        try {
            int count = replyService.getReplyCountByPostId(postId);
            Map<String, Object> response = new HashMap<>();
            response.put("reply_count", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    // ========================================
    // 좋아요
    // ========================================

    /**
     * 댓글 좋아요 토글
     * POST /reply/{replyId}/like
     */
    @PostMapping("/{replyId}/like")
    public ResponseEntity<?> toggleReplyLike(
            @PathVariable Integer replyId,
            @RequestParam String userId) {
        try {
            boolean isLiked = replyService.toggleReplyLike(replyId, userId);

            Map<String, Object> response = new HashMap<>();
            response.put("is_liked", isLiked);
            response.put("message", isLiked ? "좋아요를 눌렀습니다" : "좋아요를 취소했습니다");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 좋아요 여부 확인
     * GET /reply/{replyId}/like/check
     */
    @GetMapping("/{replyId}/like/check")
    public ResponseEntity<?> checkReplyLike(
            @PathVariable Integer replyId,
            @RequestParam String userId) {
        try {
            boolean isLiked = replyService.checkReplyLike(replyId, userId);

            Map<String, Object> response = new HashMap<>();
            response.put("is_liked", isLiked);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }
}

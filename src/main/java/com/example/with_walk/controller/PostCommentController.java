package com.example.with_walk.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.dto.PostCommentDTO;
import com.example.with_walk.service.PostCommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/withwalk/post/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostCommentController {

    private final PostCommentService commentService;

    /**
     * 댓글 목록 조회 (좋아요 정보 포함)
     * GET /post/comments/{pNum}?m_id=user123
     */
    @GetMapping("/comments/{pNum}")
    public ResponseEntity<?> getComments(
            @PathVariable Integer pNum,
            @RequestParam(name = "m_id", required = false) String mId) {
        try {
            List<PostCommentDTO> comments = commentService.getComments(pNum, mId);
            log.info("댓글 불러오기 성공: {}", comments);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 댓글 작성
     * POST /post/comment
     */
    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody PostCommentDTO comment) {
        try {
            // 날짜 설정
            if (comment.getPcDate() == null) {
                comment.setPcDate(LocalDateTime.now());
            }

            int result = commentService.createComment(comment);

            if (result > 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "댓글이 작성되었습니다");
                response.put("pc_num", comment.getPcNum());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("댓글 작성에 실패했습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 댓글 삭제
     * DELETE /post/comment/{pcNum}
     */
    @DeleteMapping("/comment/{pcNum}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer pcNum) {
        try {
            int result = commentService.deleteComment(pcNum);

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
     * 댓글 수 조회
     * GET /post/{pNum}/comment-count
     */
    @GetMapping("/{pNum}/comment-count")
    public ResponseEntity<?> getCommentCount(@PathVariable Integer pNum) {
        try {
            int count = commentService.getCommentCount(pNum);
            Map<String, Object> response = new HashMap<>();
            response.put("comment_count", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    @GetMapping("comment-writer/{pNum}")
    public ResponseEntity<List<PostCommentDTO>> getCommentList(
            @PathVariable Integer pNum,
            @RequestHeader("user_id") String currentUserId // 헤더에서 현재 사용자 ID 받기
    ) {
        List<PostCommentDTO> comments = commentService.getCommentList(pNum, currentUserId);
        return ResponseEntity.ok(comments);
    }
}

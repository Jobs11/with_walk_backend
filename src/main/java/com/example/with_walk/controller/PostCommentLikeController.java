package com.example.with_walk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.service.PostCommentLikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/withwalk/post/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostCommentLikeController {

    private final PostCommentLikeService commentLikeService;

    /**
     * 댓글 좋아요 토글 (추가/취소)
     * POST /post/comment-like/toggle
     */
    @PostMapping("/comment-like/toggle")
    public ResponseEntity<?> toggleLike(@RequestBody Map<String, Object> request) {
        try {
            Integer pcNum = (Integer) request.get("pc_num");
            String mId = (String) request.get("m_id");

            Map<String, Object> result = commentLikeService.toggleLike(pcNum, mId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 댓글 좋아요 개수 조회
     * GET /post/comment-like/count/{pcNum}
     */
    @GetMapping("/comment-like/count/{pcNum}")
    public ResponseEntity<?> getLikeCount(@PathVariable Integer pcNum) {
        try {
            int count = commentLikeService.getLikeCount(pcNum);

            Map<String, Object> response = new HashMap<>();
            response.put("pc_num", pcNum);
            response.put("count", count);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 사용자의 좋아요 여부 확인
     * GET /post/comment-like/check/{pcNum}/{mId}
     */
    @GetMapping("/comment-like/check/{pcNum}/{mId}")
    public ResponseEntity<?> checkIsLiked(
            @PathVariable Integer pcNum,
            @PathVariable String mId) {
        try {
            boolean isLiked = commentLikeService.isLikedByUser(pcNum, mId);

            Map<String, Object> response = new HashMap<>();
            response.put("pc_num", pcNum);
            response.put("m_id", mId);
            response.put("isLiked", isLiked);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 여러 댓글의 좋아요 정보 일괄 조회
     * POST /post/comment-like/batch
     */
    @PostMapping("/comment-like/batch")
    public ResponseEntity<?> getBatchLikeInfo(@RequestBody Map<String, Object> request) {
        try {
            @SuppressWarnings("unchecked")
            List<Integer> pcNums = (List<Integer>) request.get("pc_nums");
            String mId = (String) request.get("m_id");

            Map<Integer, Map<String, Object>> result = commentLikeService.getBatchLikeInfo(pcNums, mId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }
}

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.dto.PostDTO;
import com.example.with_walk.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/withwalk/post/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    /**
     * ✅ 게시글 작성 (JSON 받기 - Cloudinary URL 포함)
     * POST /post/create
     */
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDTO PostDTO) {

        try {
            // 날짜 설정
            if (PostDTO.getPDate() == null) {
                PostDTO.setPDate(LocalDateTime.now().toString());
            }

            int result = postService.createPost(PostDTO);

            if (result > 0) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "게시글이 작성되었습니다");
                response.put("p_num", PostDTO.getPNum());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("게시글 작성에 실패했습니다");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 피드 목록 조회
     * GET /post/feeds
     */
    @GetMapping("/feeds")
    public ResponseEntity<?> getFeeds(
            @RequestParam(value = "user_id", required = false) String userId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {

        try {
            List<PostDTO> feeds;
            System.out.println("게시글 접속완료");
            if (userId != null && !userId.isEmpty()) {
                // 친구 피드
                feeds = postService.getFriendFeeds(userId, page, size);
            } else {
                // 전체 피드
                feeds = postService.getFeeds(userId, page, size);
            }

            return ResponseEntity.ok(feeds);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 게시글 상세 조회
     * GET /post/{pNum}
     */
    @GetMapping("/{pNum}")
    public ResponseEntity<?> getPostDetail(
            @PathVariable Integer pNum,
            @RequestParam(value = "user_id", required = false) String userId) {

        try {
            PostDTO post = postService.getPostDetail(pNum, userId);

            if (post != null) {
                return ResponseEntity.ok(post);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("게시글을 찾을 수 없습니다");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 게시글 삭제
     * DELETE /post/{pNum}
     */
    @DeleteMapping("/{pNum}")
    public ResponseEntity<?> deletePost(@PathVariable Integer pNum) {

        try {
            int result = postService.deletePost(pNum);

            if (result > 0) {
                return ResponseEntity.ok("게시글이 삭제되었습니다");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("게시글을 찾을 수 없습니다");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 좋아요 토글
     * POST /post/like
     */
    @PostMapping("/like")
    public ResponseEntity<?> toggleLike(
            @RequestParam("p_num") Integer pNum,
            @RequestParam("user_id") String userId) {

        try {
            boolean isLiked = postService.toggleLike(pNum, userId);

            Map<String, Object> response = new HashMap<>();
            response.put("is_liked", isLiked);
            response.put("message", isLiked ? "좋아요 추가" : "좋아요 취소");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 인기 게시글 조회
     * GET /post/popular
     */
    @GetMapping("/popular")
    public ResponseEntity<?> getPopularPosts(
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        try {
            List<PostDTO> posts = postService.getPopularPosts(limit);
            return ResponseEntity.ok(posts);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }
}

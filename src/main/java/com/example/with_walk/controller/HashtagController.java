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

import com.example.with_walk.dto.HashtagDTO;
import com.example.with_walk.service.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/withwalk/hashtags")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HashtagController {

    private final HashtagService hashtagService;

    /**
     * 게시글에 해시태그 추가
     * POST /hashtags/post/{pNum}
     * Body: ["해시태그1", "해시태그2", ...]
     */
    @PostMapping("/post/{pNum}")
    public ResponseEntity<?> addHashtagsToPost(
            @PathVariable Integer pNum,
            @RequestBody List<String> hashtagNames) {
        try {
            hashtagService.addHashtagsToPost(pNum, hashtagNames);
            return ResponseEntity.ok("해시태그가 추가되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 게시글의 해시태그 수정
     * PUT /hashtags/post/{pNum}
     * Body: ["해시태그1", "해시태그2", ...]
     */
    @PutMapping("/post/{pNum}")
    public ResponseEntity<?> updatePostHashtags(
            @PathVariable Integer pNum,
            @RequestBody List<String> hashtagNames) {
        try {
            hashtagService.updatePostHashtags(pNum, hashtagNames);
            return ResponseEntity.ok("해시태그가 수정되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 게시글의 해시태그 삭제
     * DELETE /hashtags/post/{pNum}
     */
    @DeleteMapping("/post/{pNum}")
    public ResponseEntity<?> removeHashtagsFromPost(@PathVariable Integer pNum) {
        try {
            hashtagService.removeHashtagsFromPost(pNum);
            return ResponseEntity.ok("해시태그가 삭제되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 게시글의 해시태그 목록 조회
     * GET /hashtags/post/{pNum}
     */
    @GetMapping("/post/{pNum}")
    public ResponseEntity<?> getPostHashtags(@PathVariable Integer pNum) {
        try {
            List<HashtagDTO> hashtags = hashtagService.getPostHashtags(pNum);
            return ResponseEntity.ok(hashtags);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 해시태그로 게시글 번호 목록 조회
     * GET /hashtags/posts?hashtag=여행
     */
    @GetMapping("/posts")
    public ResponseEntity<?> getPostsByHashtag(
            @RequestParam("hashtag") String hashtagName) {
        try {
            List<Integer> postNums = hashtagService.getPostNumsByHashtag(hashtagName);

            Map<String, Object> response = new HashMap<>();
            response.put("hashtag", hashtagName);
            response.put("post_nums", postNums);
            response.put("count", postNums.size());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 인기 해시태그 조회
     * GET /hashtags/top?limit=10
     */
    @GetMapping("/top")
    public ResponseEntity<?> getTopHashtags(
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        try {
            List<HashtagDTO> hashtags = hashtagService.getTopHashtags(limit);
            return ResponseEntity.ok(hashtags);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 해시태그 검색
     * GET /hashtags/search?keyword=여행
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchHashtags(
            @RequestParam("keyword") String keyword) {
        try {
            List<HashtagDTO> hashtags = hashtagService.searchHashtags(keyword);
            return ResponseEntity.ok(hashtags);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }
}

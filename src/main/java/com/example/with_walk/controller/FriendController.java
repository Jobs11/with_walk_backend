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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.dto.FriendshipDTO;
import com.example.with_walk.service.FriendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/withwalk/friend")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FriendController {

    private final FriendService friendService;

    /**
     * 팔로우 하기
     * POST /friend/follow
     */
    @PostMapping("/follow")
    public ResponseEntity<?> followUser(@RequestBody FriendshipDTO friendship) {
        try {
            int result = friendService.followUser(
                    friendship.getFromUserId(),
                    friendship.getToUserId());

            if (result > 0) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("팔로우 성공");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("팔로우 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 언팔로우 하기
     * DELETE /friend/unfollow
     */
    @DeleteMapping("/unfollow")
    public ResponseEntity<?> unfollowUser(
            @RequestParam("from_user_id") String fromUserId,
            @RequestParam("to_user_id") String toUserId) {
        try {
            int result = friendService.unfollowUser(fromUserId, toUserId);

            if (result > 0) {
                return ResponseEntity.ok("언팔로우 성공");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("팔로우 관계를 찾을 수 없습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 팔로워 수 조회
     * GET /friend/followers/{userId}/count
     */
    @GetMapping("/followers/{userId}/count")
    public ResponseEntity<?> getFollowerCount(@PathVariable String userId) {
        try {
            int count = friendService.getFollowerCount(userId);
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 팔로잉 수 조회
     * GET /friend/following/{userId}/count
     */
    @GetMapping("/following/{userId}/count")
    public ResponseEntity<?> getFollowingCount(@PathVariable String userId) {
        try {
            int count = friendService.getFollowingCount(userId);
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 팔로우 상태 확인
     * GET /friend/status
     */
    @GetMapping("/status")
    public ResponseEntity<?> checkFollowStatus(
            @RequestParam("from_user_id") String fromUserId,
            @RequestParam("to_user_id") String toUserId) {
        try {
            boolean isFollowing = friendService.isFollowing(fromUserId, toUserId);
            Map<String, Object> response = new HashMap<>();
            response.put("is_following", isFollowing);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 팔로워 목록 조회
     * GET /friend/followers/{userId}
     */
    @GetMapping("/followers/{userId}")
    public ResponseEntity<?> getFollowers(@PathVariable String userId) {
        try {
            List<FriendshipDTO> followers = friendService.getFollowers(userId);
            return ResponseEntity.ok(followers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

    /**
     * 팔로잉 목록 조회
     * GET /friend/following/{userId}
     */
    @GetMapping("/following/{userId}")
    public ResponseEntity<?> getFollowing(@PathVariable String userId) {
        try {
            List<FriendshipDTO> following = friendService.getFollowing(userId);
            return ResponseEntity.ok(following);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }
}

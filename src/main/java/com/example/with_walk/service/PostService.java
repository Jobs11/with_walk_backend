package com.example.with_walk.service;

import java.util.List;

import com.example.with_walk.dto.PostDTO;

public interface PostService {

    // 게시글 작성 (Cloudinary URL 포함된 DTO만 받음)
    int createPost(PostDTO post);

    // 게시글 수정
    int updatePost(PostDTO post);

    // 피드 목록 조회
    List<PostDTO> getFeeds(String userId, int page, int size);

    // 친구 피드 조회
    List<PostDTO> getFriendFeeds(String userId, int page, int size);

    // 게시글 상세 조회
    PostDTO getPostDetail(Integer pNum, String userId);

    // 게시글 삭제
    int deletePost(Integer pNum);

    // 좋아요 토글
    boolean toggleLike(Integer pNum, String userId);

    // 인기 게시글 조회
    List<PostDTO> getPopularPosts(String userId, int limit);
}

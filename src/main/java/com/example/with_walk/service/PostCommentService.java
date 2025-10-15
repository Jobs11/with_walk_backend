package com.example.with_walk.service;

import java.util.List;

import com.example.with_walk.dto.PostCommentDTO;

public interface PostCommentService {

    // 댓글 목록 조회
    List<PostCommentDTO> getComments(Integer pNum);

    // 댓글 작성
    int createComment(PostCommentDTO comment);

    // 댓글 삭제
    int deleteComment(Integer pcNum);

    // 댓글 수 조회
    int getCommentCount(Integer pNum);
}

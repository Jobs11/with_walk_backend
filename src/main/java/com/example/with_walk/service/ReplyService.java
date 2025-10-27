package com.example.with_walk.service;

import java.util.List;

import com.example.with_walk.dto.ReplyDTO;

public interface ReplyService {

    // ========================================
    // 댓글 CRUD
    // ========================================

    /**
     * 댓글 등록
     */
    int createReply(ReplyDTO reply);

    /**
     * 댓글 수정
     */
    int updateReply(ReplyDTO reply);

    /**
     * 댓글 삭제
     */
    int deleteReply(Integer replyId);

    /**
     * 게시글의 모든 댓글 조회
     */
    List<ReplyDTO> getRepliesByPostId(Integer postId, String currentUserId);

    /**
     * 댓글 상세 조회
     */
    ReplyDTO getReplyById(Integer replyId, String currentUserId);

    /**
     * 사용자가 작성한 댓글 목록
     */
    List<ReplyDTO> getRepliesByUserId(String mId);

    /**
     * 댓글 개수 조회
     */
    int getReplyCountByPostId(Integer postId);

    // ========================================
    // 좋아요
    // ========================================

    /**
     * 댓글 좋아요 토글
     */
    boolean toggleReplyLike(Integer replyId, String mId);

    /**
     * 좋아요 여부 확인
     */
    boolean checkReplyLike(Integer replyId, String mId);
}

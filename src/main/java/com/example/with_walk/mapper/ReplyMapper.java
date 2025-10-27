package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.ReplyDTO;

@Mapper
public interface ReplyMapper {

    // ========================================
    // 댓글 CRUD
    // ========================================

    /**
     * 댓글 등록
     */
    int insertReply(ReplyDTO reply);

    /**
     * 댓글 수정
     */
    int updateReply(ReplyDTO reply);

    /**
     * 댓글 삭제 (상태 변경)
     */
    int deleteReply(@Param("replyId") Integer replyId);

    /**
     * 댓글 완전 삭제
     */
    int hardDeleteReply(@Param("replyId") Integer replyId);

    /**
     * 게시글의 모든 댓글 조회 (계층 구조)
     */
    List<ReplyDTO> getRepliesByPostId(
            @Param("postId") Integer postId,
            @Param("currentUserId") String currentUserId);

    /**
     * 댓글 상세 조회
     */
    ReplyDTO getReplyById(
            @Param("replyId") Integer replyId,
            @Param("currentUserId") String currentUserId);

    /**
     * 사용자가 작성한 댓글 목록
     */
    List<ReplyDTO> getRepliesByUserId(@Param("mId") String mId);

    /**
     * 댓글 개수 조회
     */
    int getReplyCountByPostId(@Param("postId") Integer postId);

    // ========================================
    // 좋아요
    // ========================================

    /**
     * 댓글 좋아요 추가
     */
    int insertReplyLike(
            @Param("replyId") Integer replyId,
            @Param("mId") String mId);

    /**
     * 댓글 좋아요 취소
     */
    int deleteReplyLike(
            @Param("replyId") Integer replyId,
            @Param("mId") String mId);

    /**
     * 좋아요 여부 확인
     */
    int checkReplyLike(
            @Param("replyId") Integer replyId,
            @Param("mId") String mId);

    /**
     * 댓글 좋아요 수 증가
     */
    int incrementReplyLikeCount(@Param("replyId") Integer replyId);

    /**
     * 댓글 좋아요 수 감소
     */
    int decrementReplyLikeCount(@Param("replyId") Integer replyId);
}

package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.PostCommentDTO;

@Mapper
public interface PostCommentMapper {

    // 댓글 목록 조회 (좋아요 정보 포함)
    List<PostCommentDTO> selectCommentsByPostId(@Param("pNum") Integer pNum, @Param("mId") String mId);

    // 댓글 작성
    int insertComment(PostCommentDTO comment);

    // 댓글 삭제
    int deleteComment(@Param("pcNum") Integer pcNum);

    // 댓글 수 조회
    int countCommentsByPostId(@Param("pNum") Integer pNum);

    // 특정 댓글 조회
    PostCommentDTO selectCommentById(@Param("pcNum") Integer pcNum);

    boolean isLikedByPostAuthor(@Param("pcNum") Integer pcNum);

    List<PostCommentDTO> getCommentList(
            @Param("pNum") Integer pNum,
            @Param("user_id") String user_id);

    PostCommentDTO getCommentByIdWithLikeInfo(
            @Param("pcNum") Integer pcNum,
            @Param("user_id") String userId);
}

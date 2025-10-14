package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.PostDTO;
import com.example.with_walk.dto.PostLikeDTO;

@Mapper
public interface PostMapper {

    // 게시글 작성
    int insertPost(PostDTO post);

    // 전체 피드 조회
    List<PostDTO> selectAllFeeds(@Param("userId") String userId,
            @Param("offset") int offset,
            @Param("limit") int limit);

    // 친구 피드 조회
    List<PostDTO> selectFriendFeeds(@Param("userId") String userId,
            @Param("offset") int offset,
            @Param("limit") int limit);

    // 게시글 상세 조회
    PostDTO selectPostById(@Param("pNum") Integer pNum,
            @Param("userId") String userId);

    // 게시글 삭제
    int deletePost(Integer pNum);

    // 좋아요 추가
    int insertLike(PostLikeDTO like);

    // 좋아요 삭제
    int deleteLike(@Param("pNum") Integer pNum,
            @Param("mId") String mId);

    // 좋아요 존재 여부
    int checkLikeExists(@Param("pNum") Integer pNum,
            @Param("mId") String mId);

    // 좋아요 수 업데이트
    int updateLikeCount(@Param("pNum") Integer pNum,
            @Param("increment") boolean increment);

    // 인기 게시글 조회
    List<PostDTO> selectPopularPosts(@Param("limit") int limit);
}

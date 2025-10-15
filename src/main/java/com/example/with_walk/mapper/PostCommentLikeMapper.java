package com.example.with_walk.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.PostCommentLikeDTO;

@Mapper
public interface PostCommentLikeMapper {

    // 좋아요 추가
    int insertLike(PostCommentLikeDTO dto);

    // 좋아요 삭제
    int deleteLike(@Param("pcNum") Integer pcNum, @Param("mId") String mId);

    // 좋아요 존재 여부 확인
    int existsLike(@Param("pcNum") Integer pcNum, @Param("mId") String mId);

    // 댓글의 총 좋아요 개수
    int countLikes(@Param("pcNum") Integer pcNum);

    // 사용자의 좋아요 여부 확인
    boolean isLikedByUser(@Param("pcNum") Integer pcNum, @Param("mId") String mId);

    // 여러 댓글의 좋아요 정보 일괄 조회
    List<Map<String, Object>> getBatchLikeInfo(@Param("pcNums") List<Integer> pcNums, @Param("mId") String mId);

}

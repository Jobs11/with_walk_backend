package com.example.with_walk.service;

import java.util.List;
import java.util.Map;

public interface PostCommentLikeService {

    // 댓글 좋아요 토글 (추가/취소)
    Map<String, Object> toggleLike(Integer pcNum, String mId);

    // 댓글 좋아요 개수 조회
    int getLikeCount(Integer pcNum);

    // 사용자의 좋아요 여부 확인
    boolean isLikedByUser(Integer pcNum, String mId);

    // 여러 댓글의 좋아요 정보 일괄 조회
    Map<Integer, Map<String, Object>> getBatchLikeInfo(List<Integer> pcNums, String mId);
}

package com.example.with_walk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.with_walk.dto.PostCommentLikeDTO;
import com.example.with_walk.mapper.PostCommentLikeMapper;
import com.example.with_walk.mapper.PostCommentMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostCommentLikeServiceimpl implements PostCommentLikeService {

    private final PostCommentLikeMapper commentLikeMapper;
    private final PostCommentMapper commentMapper; // 🆕 추가!

    @Override
    @Transactional
    public Map<String, Object> toggleLike(Integer pcNum, String mId) {
        Map<String, Object> result = new HashMap<>();

        // 기존 좋아요 존재 여부 확인
        int exists = commentLikeMapper.existsLike(pcNum, mId);
        boolean isLiked;

        if (exists > 0) {
            // 좋아요 취소
            commentLikeMapper.deleteLike(pcNum, mId);
            isLiked = false;
        } else {
            // 좋아요 추가
            PostCommentLikeDTO dto = new PostCommentLikeDTO();
            dto.setPcNum(pcNum);
            dto.setMId(mId);
            commentLikeMapper.insertLike(dto);
            isLiked = true;
        }

        // 현재 좋아요 개수 조회
        int likeCount = commentLikeMapper.countLikes(pcNum);

        // 🆕 게시글 작성자가 이 댓글에 좋아요 눌렀는지 확인
        boolean isLikedByAuthor = commentMapper.isLikedByPostAuthor(pcNum);

        result.put("success", true);
        result.put("isLiked", isLiked);
        result.put("likeCount", likeCount);
        result.put("isLikedByAuthor", isLikedByAuthor); // 🆕 추가!
        result.put("message", isLiked ? "좋아요가 추가되었습니다" : "좋아요가 취소되었습니다");

        System.out.println("📤 toggleLike 응답: isLiked=" + isLiked +
                ", likeCount=" + likeCount +
                ", isLikedByAuthor=" + isLikedByAuthor);

        return result;
    }

    @Override
    public int getLikeCount(Integer pcNum) {
        return commentLikeMapper.countLikes(pcNum);
    }

    @Override
    public boolean isLikedByUser(Integer pcNum, String mId) {
        return commentLikeMapper.isLikedByUser(pcNum, mId);
    }

    @Override
    public Map<Integer, Map<String, Object>> getBatchLikeInfo(List<Integer> pcNums, String mId) {
        Map<Integer, Map<String, Object>> result = new HashMap<>();

        if (pcNums == null || pcNums.isEmpty()) {
            return result;
        }

        List<Map<String, Object>> batchInfo = commentLikeMapper.getBatchLikeInfo(pcNums, mId);

        for (Map<String, Object> info : batchInfo) {
            Integer pcNum = (Integer) info.get("pc_num");
            Map<String, Object> likeInfo = new HashMap<>();
            likeInfo.put("like_count", info.get("like_count"));
            likeInfo.put("is_liked", info.get("is_liked"));
            result.put(pcNum, likeInfo);
        }

        return result;
    }
}

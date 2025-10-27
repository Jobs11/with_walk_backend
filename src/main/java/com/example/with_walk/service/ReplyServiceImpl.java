package com.example.with_walk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.with_walk.dto.ReplyDTO;
import com.example.with_walk.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;

    // ========================================
    // 댓글 CRUD
    // ========================================

    @Override
    @Transactional
    public int createReply(ReplyDTO reply) {
        return replyMapper.insertReply(reply);
    }

    @Override
    @Transactional
    public int updateReply(ReplyDTO reply) {
        return replyMapper.updateReply(reply);
    }

    @Override
    @Transactional
    public int deleteReply(Integer replyId) {
        return replyMapper.deleteReply(replyId);
    }

    @Override
    public List<ReplyDTO> getRepliesByPostId(Integer postId, String currentUserId) {
        return replyMapper.getRepliesByPostId(postId, currentUserId);
    }

    @Override
    public ReplyDTO getReplyById(Integer replyId, String currentUserId) {
        return replyMapper.getReplyById(replyId, currentUserId);
    }

    @Override
    public List<ReplyDTO> getRepliesByUserId(String mId) {
        return replyMapper.getRepliesByUserId(mId);
    }

    @Override
    public int getReplyCountByPostId(Integer postId) {
        return replyMapper.getReplyCountByPostId(postId);
    }

    // ========================================
    // 좋아요
    // ========================================

    @Override
    @Transactional
    public boolean toggleReplyLike(Integer replyId, String mId) {
        // 좋아요 여부 확인
        int likeCount = replyMapper.checkReplyLike(replyId, mId);

        if (likeCount > 0) {
            // 좋아요 취소
            replyMapper.deleteReplyLike(replyId, mId);
            replyMapper.decrementReplyLikeCount(replyId);
            return false; // 좋아요 취소됨
        } else {
            // 좋아요 추가
            replyMapper.insertReplyLike(replyId, mId);
            replyMapper.incrementReplyLikeCount(replyId);
            return true; // 좋아요 추가됨
        }
    }

    @Override
    public boolean checkReplyLike(Integer replyId, String mId) {
        return replyMapper.checkReplyLike(replyId, mId) > 0;
    }
}

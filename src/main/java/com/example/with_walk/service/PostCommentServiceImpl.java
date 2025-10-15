package com.example.with_walk.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.with_walk.dto.PostCommentDTO;
import com.example.with_walk.mapper.PostCommentMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentMapper commentMapper;

    @Override
    public List<PostCommentDTO> getComments(Integer pNum) {
        return commentMapper.selectCommentsByPostId(pNum);
    }

    @Override
    @Transactional
    public int createComment(PostCommentDTO comment) {
        // 날짜 설정
        if (comment.getPcDate() == null) {
            comment.setPcDate(LocalDateTime.now());
        }

        // 댓글 삽입
        int result = commentMapper.insertComment(comment);

        // 게시글의 댓글 수 업데이트 (posts 테이블에 comment_count 컬럼이 있다면)
        // postMapper.updateCommentCount(comment.getPNum(), true);

        return result;
    }

    @Override
    @Transactional
    public int deleteComment(Integer pcNum) {
        // 댓글 정보 먼저 조회 (게시글 번호 필요)
        PostCommentDTO comment = commentMapper.selectCommentById(pcNum);

        // 댓글 삭제
        int result = commentMapper.deleteComment(pcNum);

        // 게시글의 댓글 수 업데이트 (선택사항)
        // if (result > 0 && comment != null) {
        // postMapper.updateCommentCount(comment.getPNum(), false);
        // }

        return result;
    }

    @Override
    public int getCommentCount(Integer pNum) {
        return commentMapper.countCommentsByPostId(pNum);
    }
}

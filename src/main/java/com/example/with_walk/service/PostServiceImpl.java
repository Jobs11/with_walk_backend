package com.example.with_walk.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.with_walk.dto.PostDTO;
import com.example.with_walk.dto.PostLikeDTO;
import com.example.with_walk.mapper.PostMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    // ✅ Cloudinary 사용하므로 파일 업로드 관련 코드 제거

    @Override
    @Transactional
    public int createPost(PostDTO post) {

        // 날짜 설정
        if (post.getPDate() == null) {
            post.setPDate(LocalDateTime.now().toString());
        }

        // pImage는 이미 Cloudinary URL이 들어있음
        return postMapper.insertPost(post);
    }

    @Override
    @Transactional
    public int updatePost(PostDTO post) {
        // 게시글이 존재하는지 확인
        PostDTO existingPost = postMapper.selectPostById(post.getPNum(), post.getMId());

        if (existingPost == null) {
            throw new RuntimeException("게시글을 찾을 수 없습니다");
        }

        // 작성자 확인 (본인만 수정 가능)
        if (!existingPost.getMId().equals(post.getMId())) {
            throw new RuntimeException("본인의 게시글만 수정할 수 있습니다");
        }

        // 게시글 수정
        return postMapper.updatePost(post);
    }

    @Override
    public List<PostDTO> getFeeds(String userId, int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.selectAllFeeds(userId, offset, size);
    }

    @Override
    public List<PostDTO> getFriendFeeds(String userId, int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.selectFriendFeeds(userId, offset, size);
    }

    @Override
    public PostDTO getPostDetail(Integer pNum, String userId) {
        return postMapper.selectPostById(pNum, userId);
    }

    @Override
    @Transactional
    public int deletePost(Integer pNum) {
        return postMapper.deletePost(pNum);
    }

    @Override
    @Transactional
    public boolean toggleLike(Integer pNum, String userId) {

        // 이미 좋아요 했는지 확인
        int exists = postMapper.checkLikeExists(pNum, userId);

        if (exists > 0) {
            // 좋아요 취소
            postMapper.deleteLike(pNum, userId);
            postMapper.updateLikeCount(pNum, false);
            return false;
        } else {
            // 좋아요 추가
            PostLikeDTO like = PostLikeDTO.builder()
                    .pNum(pNum)
                    .mId(userId)
                    .build();
            postMapper.insertLike(like);
            postMapper.updateLikeCount(pNum, true);
            return true;
        }
    }

    @Override
    public List<PostDTO> getPopularPosts(String userId, int limit) {
        return postMapper.selectPopularPosts(userId, limit);
    }
}

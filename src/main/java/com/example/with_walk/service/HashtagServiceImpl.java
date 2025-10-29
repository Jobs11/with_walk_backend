package com.example.with_walk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.with_walk.dto.HashtagDTO;
import com.example.with_walk.dto.PostHashtagDTO;
import com.example.with_walk.mapper.HashtagMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagMapper hashtagMapper;

    @Override
    @Transactional
    public void addHashtagsToPost(Integer pNum, List<String> hashtagNames) {
        if (hashtagNames == null || hashtagNames.isEmpty()) {
            return;
        }

        for (String name : hashtagNames) {
            // 해시태그 정리 (앞뒤 공백 제거, # 제거)
            String cleanName = name.trim().replace("#", "");
            if (cleanName.isEmpty()) {
                continue;
            }

            // 해시태그 조회 또는 생성
            HashtagDTO hashtag = getOrCreateHashtag(cleanName);

            // 게시글-해시태그 연결
            PostHashtagDTO postHashtag = new PostHashtagDTO();
            postHashtag.setPNum(pNum);
            postHashtag.setHNum(hashtag.getHNum());

            try {
                hashtagMapper.insertPostHashtag(postHashtag);
                // 사용 횟수 증가
                hashtagMapper.incrementCount(hashtag.getHNum());
            } catch (Exception e) {
                // 중복 연결 무시 (UNIQUE 제약조건)
                continue;
            }
        }
    }

    @Override
    @Transactional
    public void updatePostHashtags(Integer pNum, List<String> hashtagNames) {
        // 기존 해시태그 삭제
        removeHashtagsFromPost(pNum);

        // 새 해시태그 추가
        addHashtagsToPost(pNum, hashtagNames);
    }

    @Override
    @Transactional
    public void removeHashtagsFromPost(Integer pNum) {
        // 게시글의 해시태그 목록 조회
        List<HashtagDTO> hashtags = hashtagMapper.findHashtagsByPostNum(pNum);

        // 연결 삭제
        hashtagMapper.deletePostHashtagsByPostNum(pNum);

        // 각 해시태그의 사용 횟수 감소
        for (HashtagDTO hashtag : hashtags) {
            hashtagMapper.decrementCount(hashtag.getHNum());
        }
    }

    @Override
    public List<HashtagDTO> getPostHashtags(Integer pNum) {
        return hashtagMapper.findHashtagsByPostNum(pNum);
    }

    @Override
    public List<Integer> getPostNumsByHashtag(String hashtagName) {
        // # 제거 및 정리
        String cleanName = hashtagName.trim().replace("#", "");
        return hashtagMapper.findPostNumsByHashtagName(cleanName);
    }

    @Override
    public List<HashtagDTO> getTopHashtags(int limit) {
        return hashtagMapper.findTopHashtags(limit);
    }

    @Override
    public List<HashtagDTO> searchHashtags(String keyword) {
        // # 제거 및 정리
        String cleanKeyword = keyword.trim().replace("#", "");
        return hashtagMapper.searchHashtags(cleanKeyword);
    }

    @Override
    @Transactional
    public HashtagDTO getOrCreateHashtag(String hashtagName) {
        // # 제거 및 정리
        String cleanName = hashtagName.trim().replace("#", "");

        // 기존 해시태그 조회
        HashtagDTO hashtag = hashtagMapper.findByName(cleanName);

        // 없으면 생성
        if (hashtag == null) {
            hashtag = new HashtagDTO();
            hashtag.setHName(cleanName);
            hashtag.setHCount(0);
            hashtagMapper.insertHashtag(hashtag);
        }

        return hashtag;
    }
}

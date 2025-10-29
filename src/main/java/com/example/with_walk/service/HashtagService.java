package com.example.with_walk.service;

import java.util.List;

import com.example.with_walk.dto.HashtagDTO;

public interface HashtagService {

    /**
     * 게시글에 해시태그 추가 (여러 개)
     * - 해시태그가 없으면 생성
     * - 있으면 사용 횟수 증가
     * - 게시글과 연결
     */
    void addHashtagsToPost(Integer pNum, List<String> hashtagNames);

    /**
     * 게시글의 해시태그 수정
     * - 기존 연결 삭제 후 새로 추가
     */
    void updatePostHashtags(Integer pNum, List<String> hashtagNames);

    /**
     * 게시글의 해시태그 삭제
     * - 연결 삭제
     * - 사용 횟수 감소
     */
    void removeHashtagsFromPost(Integer pNum);

    /**
     * 게시글의 해시태그 목록 조회
     */
    List<HashtagDTO> getPostHashtags(Integer pNum);

    /**
     * 해시태그로 게시글 번호 목록 조회
     */
    List<Integer> getPostNumsByHashtag(String hashtagName);

    /**
     * 인기 해시태그 조회 (상위 N개)
     */
    List<HashtagDTO> getTopHashtags(int limit);

    /**
     * 해시태그 검색
     */
    List<HashtagDTO> searchHashtags(String keyword);

    /**
     * 해시태그 이름으로 조회 (없으면 생성)
     */
    HashtagDTO getOrCreateHashtag(String hashtagName);
}

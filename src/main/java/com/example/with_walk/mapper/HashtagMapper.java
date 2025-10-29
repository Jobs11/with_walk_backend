package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.HashtagDTO;
import com.example.with_walk.dto.PostHashtagDTO;

@Mapper
public interface HashtagMapper {

    // ========================================
    // 해시태그 기본 CRUD
    // ========================================

    /**
     * 해시태그 이름으로 조회
     */
    HashtagDTO findByName(@Param("hName") String hName);

    /**
     * 해시태그 번호로 조회
     */
    HashtagDTO findByNum(@Param("hNum") Integer hNum);

    /**
     * 해시태그 등록
     */
    int insertHashtag(HashtagDTO hashtag);

    /**
     * 해시태그 사용 횟수 증가
     */
    int incrementCount(@Param("hNum") Integer hNum);

    /**
     * 해시태그 사용 횟수 감소
     */
    int decrementCount(@Param("hNum") Integer hNum);

    /**
     * 인기 해시태그 조회 (상위 N개)
     */
    List<HashtagDTO> findTopHashtags(@Param("limit") int limit);

    /**
     * 해시태그 검색 (LIKE)
     */
    List<HashtagDTO> searchHashtags(@Param("keyword") String keyword);

    // ========================================
    // 게시글-해시태그 연결
    // ========================================

    /**
     * 게시글에 해시태그 연결
     */
    int insertPostHashtag(PostHashtagDTO postHashtag);

    /**
     * 게시글의 모든 해시태그 연결 삭제
     */
    int deletePostHashtagsByPostNum(@Param("pNum") Integer pNum);

    /**
     * 특정 연결 삭제
     */
    int deletePostHashtag(@Param("pNum") Integer pNum, @Param("hNum") Integer hNum);

    /**
     * 게시글의 해시태그 목록 조회
     */
    List<HashtagDTO> findHashtagsByPostNum(@Param("pNum") Integer pNum);

    /**
     * 해시태그로 게시글 번호 목록 조회
     */
    List<Integer> findPostNumsByHashtagNum(@Param("hNum") Integer hNum);

    /**
     * 해시태그 이름으로 게시글 번호 목록 조회
     */
    List<Integer> findPostNumsByHashtagName(@Param("hName") String hName);
}

package com.example.with_walk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.MemberVO;

@Mapper
public interface MemberMapper {

    public MemberVO getUser(@Param("m_id") String m_id, @Param("m_password") String m_password);

    public MemberVO getUserData(@Param("m_id") String m_id);

    public void registerUser(MemberVO member);

    public void deleteUser(String m_id);

    public void updateUser(MemberVO memberVO);

    public void updateProfile(MemberVO memberVO);

}

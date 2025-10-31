package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.MemberDTO;

@Mapper
public interface MemberMapper {

    public MemberDTO getUser(@Param("mId") String m_id, @Param("mPassword") String m_password);

    public MemberDTO getUserData(@Param("mId") String m_id);

    public MemberDTO checkNick(@Param("mNickname") String m_nickname);

    public List<MemberDTO> searchNick(@Param("mNickname") String m_nickname);

    public void registerUser(MemberDTO member);

    public void deleteUser(String m_id);

    public void updateUser(MemberDTO memberVO);

    public void updateProfile(MemberDTO memberVO);

    List<MemberDTO> getAllMembers();
}

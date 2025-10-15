package com.example.with_walk.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.MemberDTO;

public interface MemberService {
    public MemberDTO getUser(String m_id, String m_password);

    public MemberDTO getUserData(String m_id);

    public MemberDTO checkNick(@Param("m_nickname") String m_nickname);

    public List<MemberDTO> searchNick(@Param("m_nickname") String m_nickname);

    public void registerUser(MemberDTO member);

    public void deleteUser(String m_id);

    public void updateUser(MemberDTO member);

    public void updateProfile(MemberDTO memberVO);
}

package com.example.with_walk.service;

import com.example.with_walk.dto.MemberVO;

public interface MemberService {
    public MemberVO getUser(String m_id, String m_password);

    public MemberVO getUserData(String m_id);

    public void registerUser(MemberVO member);

    public void deleteUser(String m_id);

    public void updateUser(MemberVO member);

    public void updateProfile(MemberVO memberVO);
}

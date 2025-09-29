package com.example.with_walk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.with_walk.dto.MemberVO;
import com.example.with_walk.mapper.MemberMapper;

@Service
public class MemberServiceimpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public MemberVO getUser(String m_id, String m_password) {
        return memberMapper.getUser(m_id, m_password);
    }

    @Override
    public MemberVO getUserData(String m_id) {
        return memberMapper.getUserData(m_id);
    }

    @Override
    public void registerUser(MemberVO member) {
        memberMapper.registerUser(member);
    }

    @Override
    public void deleteUser(String m_id) {
        memberMapper.deleteUser(m_id);
    }

    @Override
    public void updateUser(MemberVO member) {
        memberMapper.updateUser(member);
    }

    @Override
    public void updateProfile(MemberVO member) {
        memberMapper.updateProfile(member);
    }

}

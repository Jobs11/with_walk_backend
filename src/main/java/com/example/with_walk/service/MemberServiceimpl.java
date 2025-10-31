package com.example.with_walk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.with_walk.dto.MemberDTO;
import com.example.with_walk.mapper.MemberMapper;

@Service
public class MemberServiceimpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public MemberDTO getUser(String m_id, String m_password) {
        return memberMapper.getUser(m_id, m_password);
    }

    @Override
    public MemberDTO getUserData(String m_id) {
        return memberMapper.getUserData(m_id);
    }

    @Override
    public MemberDTO checkNick(String m_nickname) {
        return memberMapper.checkNick(m_nickname);
    }

    @Override
    public List<MemberDTO> searchNick(String m_nickname) {
        return memberMapper.searchNick(m_nickname);
    }

    @Override
    public void registerUser(MemberDTO member) {
        memberMapper.registerUser(member);
    }

    @Override
    public void deleteUser(String m_id) {
        memberMapper.deleteUser(m_id);
    }

    @Override
    public void updateUser(MemberDTO member) {
        memberMapper.updateUser(member);
    }

    @Override
    public void updateProfile(MemberDTO member) {
        memberMapper.updateProfile(member);
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberMapper.getAllMembers();
    }

}

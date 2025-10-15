package com.example.with_walk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.dto.MemberDTO;
import com.example.with_walk.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/withwalk/member/")
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/register")
    public void registerUser(@RequestBody MemberDTO member) {
        memberService.registerUser(member);
        System.out.println("회원 등록완료");
    }

    @GetMapping("/getUser")
    public MemberDTO getUser(@RequestParam("m_id") String m_id, @RequestParam("m_password") String m_password) {
        MemberDTO member = memberService.getUser(m_id, m_password);
        System.out.println("회원 불러오기 성공");
        // log.info("회원 불러오기 성공: {}", member);
        return member;
    }

    @GetMapping("/getUserdata")
    public MemberDTO getUserData(@RequestParam("m_id") String m_id) {
        MemberDTO member = memberService.getUserData(m_id);
        System.out.println("회원 불러오기 성공");
        // log.info("회원 불러오기 성공: {}", member);
        return member;
    }

    @GetMapping("/check")
    public MemberDTO checkNick(@RequestParam("m_nickname") String m_nickname) {
        MemberDTO member = memberService.checkNick(m_nickname);
        System.out.println("회원 불러오기 성공");
        // log.info("회원 불러오기 성공: {}", member);
        return member;
    }

    @GetMapping("/search")
    public List<MemberDTO> searchNick(@RequestParam("m_nickname") String m_nickname) {
        List<MemberDTO> member = memberService.searchNick(m_nickname);
        System.out.println("회원 불러오기 성공");
        // log.info("회원 불러오기 성공: {}", member);
        return member;
    }

    @PostMapping("/modify")
    public void modifyUser(@RequestBody MemberDTO member) {
        memberService.updateUser(member);
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestParam("m_id") String m_id) {
        System.out.println("회원 삭제완료");
        memberService.deleteUser(m_id);
    }

    @PostMapping("/upprofile")
    public void updateProfile(@RequestBody MemberDTO member) {
        memberService.updateProfile(member);
        System.out.println("프로필 수정 성공");
        // log.info("프로필 수정 성공: {}", member);
    }

}

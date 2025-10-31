package com.example.with_walk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public MemberDTO getUser(@RequestParam("mId") String mId, @RequestParam("mPassword") String mPassword) {
        MemberDTO member = memberService.getUser(mId, mPassword);
        System.out.println("id: " + mId + ", password: " + mPassword);
        System.out.println("회원 불러오기 성공");
        log.info("회원 불러오기 성공: {}", member);
        return member;
    }

    @GetMapping("/getUserData")
    public MemberDTO getUserData(@RequestParam("mId") String mId) {
        MemberDTO member = memberService.getUserData(mId);
        System.out.println("회원 불러오기 성공");
        return member;
    }

    @GetMapping("/check")
    public MemberDTO checkNick(@RequestParam("mNickname") String mNickname) {
        MemberDTO member = memberService.checkNick(mNickname);
        System.out.println("닉네임 체크 성공");
        return member;
    }

    @GetMapping("/search")
    public List<MemberDTO> searchNick(@RequestParam("mNickname") String mNickname) {
        List<MemberDTO> member = memberService.searchNick(mNickname);
        System.out.println("닉네임 불러오기 성공");
        log.info("닉네임 불러오기 성공: {}", member);
        return member;
    }

    @PostMapping("/modify")
    public void modifyUser(@RequestBody MemberDTO member) {
        memberService.updateUser(member);
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestParam("mId") String mId) {
        System.out.println("회원 삭제완료");
        memberService.deleteUser(mId);
    }

    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody MemberDTO member) {
        memberService.updateProfile(member);
        System.out.println("프로필 수정 성공");
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        try {
            List<MemberDTO> members = memberService.getAllMembers();
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            log.error("전체 회원 조회 실패", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

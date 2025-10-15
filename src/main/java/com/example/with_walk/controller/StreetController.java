package com.example.with_walk.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.with_walk.dto.StreetDTO;
import com.example.with_walk.service.StreetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/withwalk/street/")
public class StreetController {

    @Autowired
    StreetService streetService;

    @PostMapping("/register")
    public void registerStreet(@RequestBody StreetDTO streetVO) {
        streetService.registerStreet(streetVO);
        System.out.println("발자국 등록완료");
    }

    @GetMapping("/getlist")
    public List<StreetDTO> getStreetList(@RequestParam("m_id") String m_id, @RequestParam("r_date") String r_date) {
        List<StreetDTO> Street = streetService.getStreetList(m_id, r_date);
        System.out.println("발자국 불러오기 성공");
        // log.info("기억 불러오기 성공: {}", Street);
        return Street;
    }

    @GetMapping("/getalllist")
    public List<StreetDTO> getStreetList(@RequestParam("m_id") String m_id) {
        List<StreetDTO> Street = streetService.getStreetAllList(m_id);
        System.out.println("발자국 불러오기 성공");
        // log.info("기억 불러오기 성공: {}", Street);
        return Street;
    }

    @PostMapping("/delete")
    public void deleteStreet(@Param("r_num") Integer r_num) {
        System.out.println("발자국 삭제완료");
        streetService.deleteStreet(r_num);
    }

}

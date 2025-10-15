package com.example.with_walk.controller;

import java.util.List;

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
    public void registerStreet(@RequestBody StreetDTO street) {
        streetService.registerStreet(street);
        System.out.println("발자국 등록완료");
    }

    @GetMapping("/getlist")
    public List<StreetDTO> getStreetList(@RequestParam("mId") String mId, @RequestParam("rDate") String rDate) {
        List<StreetDTO> streets = streetService.getStreetList(mId, rDate);
        System.out.println("발자국 불러오기 성공");
        return streets;
    }

    @GetMapping("/getalllist")
    public List<StreetDTO> getStreetAllList(@RequestParam("mId") String mId) {
        List<StreetDTO> streets = streetService.getStreetAllList(mId);
        System.out.println("발자국 불러오기 성공");
        return streets;
    }

    @PostMapping("/delete")
    public void deleteStreet(@RequestParam("rNum") Integer rNum) {
        streetService.deleteStreet(rNum);
        System.out.println("발자국 삭제완료");
    }

}

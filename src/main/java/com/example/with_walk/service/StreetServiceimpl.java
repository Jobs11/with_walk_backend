package com.example.with_walk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.with_walk.dto.StreetDTO;
import com.example.with_walk.mapper.StreetMapper;

@Service
public class StreetServiceimpl implements StreetService {

    @Autowired
    StreetMapper streetMapper;

    @Override
    public List<StreetDTO> getStreetList(String m_id, String r_date) {
        return streetMapper.getStreetList(m_id, r_date);
    }

    @Override
    public List<StreetDTO> getStreetAllList(String m_id) {
        return streetMapper.getStreetAllList(m_id);
    }

    @Override
    public void registerStreet(StreetDTO StreetVO) {
        streetMapper.registerStreet(StreetVO);
    }

    @Override
    public void deleteStreet(Integer r_num) {
        streetMapper.deleteStreet(r_num);
        System.out.println("발자국삭제 " + r_num);
    }

}

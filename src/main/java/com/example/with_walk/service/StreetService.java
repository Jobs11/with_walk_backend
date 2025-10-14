package com.example.with_walk.service;

import java.util.List;

import com.example.with_walk.dto.StreetDTO;

public interface StreetService {
    public List<StreetDTO> getStreetList(String m_id, String r_date);

    public List<StreetDTO> getStreetAllList(String m_id);

    public void registerStreet(StreetDTO streetVO);

    public void deleteStreet(Integer r_num);

}

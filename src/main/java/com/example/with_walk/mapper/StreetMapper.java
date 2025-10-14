package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.StreetDTO;

@Mapper
public interface StreetMapper {

    public List<StreetDTO> getStreetList(@Param("m_id") String m_id, @Param("r_date") String r_date);

    public List<StreetDTO> getStreetAllList(@Param("m_id") String m_id);

    public void registerStreet(StreetDTO streetVO);

    public void deleteStreet(Integer r_num);

}

package com.example.with_walk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.with_walk.dto.RankingDTO;
import com.example.with_walk.dto.StreetDTO;

@Mapper
public interface StreetMapper {

    public List<StreetDTO> getStreetList(@Param("mId") String m_id, @Param("rDate") String r_date);

    public List<StreetDTO> getStreetAllList(@Param("mId") String m_id);

    public void registerStreet(StreetDTO streetVO);

    public void deleteStreet(Integer r_num);

    List<RankingDTO> getWeeklyTop3();

}

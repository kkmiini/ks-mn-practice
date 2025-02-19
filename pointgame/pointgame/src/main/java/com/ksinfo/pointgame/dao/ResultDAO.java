package com.ksinfo.pointgame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ksinfo.pointgame.dto.GameDTO;

@Mapper
public interface ResultDAO {
	
	List<GameDTO> getResult(@Param("memberId") String memberId);

}

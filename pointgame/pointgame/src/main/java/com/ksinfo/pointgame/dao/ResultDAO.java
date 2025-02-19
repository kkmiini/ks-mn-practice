package com.ksinfo.pointgame.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ksinfo.pointgame.dto.GameDTO;

@Mapper
public interface ResultDAO {
	
	List<GameDTO> getResult(@Param("memberId") String memberId);

	void saveResult(Map<String, Object> paramMap);
	

}

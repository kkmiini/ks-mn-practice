package com.ksinfo.pointgame.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksinfo.pointgame.dto.GameDTO;

@Mapper
public interface PointDAO {
	

    GameDTO getPoint(@Param("memberId") String memberId);
    
    void setHideNum(@Param("memberId") String memberId, @Param("secretNumber") int secretNumber);

	void setGameNumAndGameOver(@Param("memberId")String memberId);

    void updateCreateDate(@Param("memberId") String memberId);

	void saveGameCount(String memberId);

	void savePointInfo(String memberId, int newPoints);
    
    
    
}

package com.ksinfo.pointgame.service;

import org.springframework.stereotype.Service;

import com.ksinfo.pointgame.dao.PointDAO;
import com.ksinfo.pointgame.dto.GameDTO;

@Service
public class GameService {

	private final PointDAO pointDAO;
	
	public GameService(PointDAO pointDAO) {
		this.pointDAO = pointDAO;
	}

	public GameDTO getPointInfo(String memberId) {
		return pointDAO.getPoint(memberId);
	}
	

	
}

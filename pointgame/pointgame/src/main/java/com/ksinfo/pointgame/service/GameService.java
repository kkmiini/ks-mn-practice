package com.ksinfo.pointgame.service;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.ksinfo.pointgame.dao.PointDAO;
import com.ksinfo.pointgame.dao.ResultDAO;
import com.ksinfo.pointgame.dto.GameDTO;
import com.ksinfo.pointgame.util.RandomNumberUtil;

@Service
public class GameService {

    private final PointDAO pointDAO;
    private final ResultDAO resultDAO;

    public GameService(PointDAO pointDAO, ResultDAO resultDAO) {
        this.pointDAO = pointDAO;
        this.resultDAO = resultDAO;
    }

    public GameDTO getPointInfo(String memberId) {
        // 현재 포인트 정보 가져오기
        GameDTO gameInfo = pointDAO.getPoint(memberId);

        // gameInfo가 null이면 기본 값 반환 (예외 방지)
        if (gameInfo == null) {
            return new GameDTO(memberId, 0, 0, null, 0, 0, 0, null);
        }

        // 현재 날짜 가져오기
        LocalDate dbDate = gameInfo.getCreateDate();
        LocalDate today = LocalDate.now();

        // create_date가 당일이 아니면 업데이트 로직 수행
        if (dbDate == null || dbDate.isBefore(today)) {
            // 숨겨진 숫자 (secretNumber) 업데이트
            int secretNumber = RandomNumberUtil.generateUniqueThreeDigitNumber();
            pointDAO.setHideNum(memberId, secretNumber);
            gameInfo.setSecretNumber(secretNumber); // DTO에도 반영

            // 게임 횟수 및 게임 종료 상태 초기화
            pointDAO.setGameNumAndGameOver(memberId);
            gameInfo.setGameCount(0);
            gameInfo.setGameOver(0);

            // create_date를 오늘 날짜로 업데이트
            pointDAO.updateCreateDate(memberId);
            gameInfo.setCreateDate(today); // DTO에서도 날짜 갱신
        }

        return gameInfo;
    }

	public GameDTO getResult(String memberId) {
		GameDTO resultInfo = resultDAO.getResult(memberId);
		
		 // 2️⃣ resultInfo가 null이면 새로운 객체 생성 (예외 방지)
	    if (resultInfo == null) {
	    	return new GameDTO(memberId, 0, 0, null, 0, 0, 0, null);
	    }
		
		 LocalDate dbDate = resultInfo.getCreateDate();
	     LocalDate today = LocalDate.now();
	     
	     // create_date가 당일이면 DB 조회 
	        if (dbDate == null || dbDate.equals(today)) {
	        	 // 새 결과 데이터 가져오기
	            GameDTO newResultInfo = resultDAO.getResult(memberId);

	            if (newResultInfo != null) {
	                resultInfo.setResultNumber(newResultInfo.getResultNumber());
	                resultInfo.setResultContent(newResultInfo.getResultContent());
	            }
		
	        }
	       
		return resultInfo;
	}


}

package com.ksinfo.pointgame.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			return new GameDTO(memberId, 0, 0, null, 0, 0, null, null);
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

	public List<GameDTO> getResults(String memberId) {
		List<GameDTO> resultInfos = resultDAO.getResult(memberId); // 여러 개의 결과 조회

		if (resultInfos == null || resultInfos.isEmpty()) {
			// 결과가 없으면 빈 리스트 반환
			return List.of(new GameDTO(memberId, 0, 0, null, 0, 0, null, null));
		}

		for (GameDTO resultInfo : resultInfos) {

			resultInfo.setResultNumber(resultInfo.getResultNumber());
			resultInfo.setResultContent(resultInfo.getResultContent());
		}

		return resultInfos;
	}

	public void saveInfo(String memberId, int gameCount, String resultNumber, String resultContent) {
		// 파라미터를 Map으로 만들어 DAO에 전달
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("memberId", memberId);
		paramMap.put("inputCount", gameCount);
		paramMap.put("resultNumber", resultNumber);
		paramMap.put("resultContent", resultContent);

		// DAO 호출하여 데이터 저장
		resultDAO.saveResult(paramMap);
	}

	public void saveGameCount(String memberId) {
		pointDAO.saveGameCount(memberId);

	}

	public void savePointInfo(String memberId, int newPoints) {
		pointDAO.savePointInfo(memberId, newPoints);

	}

}

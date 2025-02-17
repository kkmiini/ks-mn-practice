package com.ksinfo.pointgame.service;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.ksinfo.pointgame.dao.PointDAO;
import com.ksinfo.pointgame.dto.GameDTO;
import com.ksinfo.pointgame.util.RandomNumberUtil;

@Service
public class GameService {

    private final PointDAO pointDAO;

    public GameService(PointDAO pointDAO) {
        this.pointDAO = pointDAO;
    }

    public GameDTO getPointInfo(String memberId) {
        return pointDAO.getPoint(memberId);
    }

    public GameDTO setHideNum(String memberId) {
        // 현재 사용자 정보를 DB에서 조회
        GameDTO gameInfo = pointDAO.getPoint(memberId);

        /* gameInfo가 `null`이면 기본값 반환 (예외 방지)
        if (gameInfo == null) {
            return new GameDTO(memberId, 0, 0, null, 0, false);
        } */

        // createDate가 null이 아니고, 오늘 날짜와 동일하면 기존 secretNumber 유지
        if (gameInfo.getCreateDate() != null) {
            LocalDate dbDate = gameInfo.getCreateDate(); 
            LocalDate today = LocalDate.now(); // 서버 기준 현재 날짜
            
            // 오늘 날짜면 기존 secretNumber 유지
            if (dbDate.equals(today)) {
                return gameInfo;
            }
        }

        // 오늘 날짜가 아니라면 새로운 난수 생성 및 DB 업데이트
        int secretNumber = RandomNumberUtil.generateUniqueThreeDigitNumber();
        pointDAO.setHideNum(memberId, secretNumber);

        // 업데이트된 정보를 다시 반환하지 않고 기존 `gameInfo`를 업데이트하여 반환
        gameInfo.setSecretNumber(secretNumber);
        return gameInfo;
    }
}

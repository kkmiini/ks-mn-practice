package com.ksinfo.pointgame.util;

import com.ksinfo.pointgame.dto.GameDTO;

public class PlayGameUtil {

    public static GameDTO processInput(int num1, int num2, int num3, int secret_num, int game_count) {
        
        // 숨김 숫자 배열 (세 자리 숫자 분리)
        int[] R = new int[3];
        R[0] = secret_num / 100;
        R[1] = (secret_num / 10) % 10;
        R[2] = secret_num % 10;

        // 입력 숫자 배열
        int[] IN = new int[3];
        IN[0] = num1;
        IN[1] = num2;
        IN[2] = num3;

        // 게임 횟수 증가
        int K = game_count;
        int s = 0;  // 스트라이크 개수
        int b = 0;  // 볼 개수
        String result_content;
        int result_number = Integer.parseInt("" + num1 + num2 + num3);

        // 스트라이크 & 볼 판별 로직
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (R[i] == IN[j]) {
                    if (i == j) {
                        s++;  // 자리와 숫자가 모두 일치 -> 스트라이크
                    } else {
                        b++;  // 숫자는 맞지만 자리 불일치 -> 볼
                    }
                }
            }
        }

        // 결과 메시지 설정
        if (s == 3) {
            result_content = "あたり";  // 정답
        } else if (s == 0 && b == 0) {
            result_content = "はずれ"; // 스트라이크와 볼 모두 없음
        } else {
            result_content = (s > 0 ? s + "S" : "") + (b > 0 ? b + "B" : "");
        }

        // 결과 저장
        GameDTO gameDTO = new GameDTO();
        gameDTO.setResultContent(result_content);  // 결과 메시지 저장
        gameDTO.setResultNumber(result_number);  // 입력 숫자 저장
        gameDTO.setGameCount(K);  // 게임 횟수 저장

        return gameDTO;
    }
}

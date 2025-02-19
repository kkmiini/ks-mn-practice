package com.ksinfo.pointgame.util;


import com.ksinfo.pointgame.dto.GameDTO;

public class PlayGameUtil {


	public static GameDTO processInput(int num1, int num2, int num3, int secret_num, int game_count) {
		
		//숨김숫자
		int[] R = new int[3];
		R[0] = secret_num / 100;
		R[1] = (secret_num / 10) % 10;
		R[2] = secret_num % 10;
		
		//입력숫자 
		int[] IN = new int[3];
		IN[0] = num1;
		IN[1] = num2;
		IN[2] = num3;
		
		//입력횟수(input_count) = K 
		int K = game_count + 1;
		int s = 0;  
		int b = 0; 
		int x = 0;
		String result_content = null;
		//int result_number = Integer.parseInt("" + num1 + num2 + num3);
		
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(R[i] == IN[j]) {
					if(i == j) {
						s = s + 1;
						i = i + 1;
						
					} else {
						b = b + 1;
						i = i + 1; 
					}
				} else { 
					x = x = 1; 
					
						if(x == 9) { 
							result_content = "はずれ"; //게임실패
						} 
				}
			}
		}
		
		if(s == 0) {
			result_content = b + "B";
		} else { 
			if(s == 3) {
				result_content = "あたり";
			} else { 
				if(b == 0) {
					result_content = s + "S";
				} else {
					result_content = s + "S" + b + "B";
				}
			}
		}
		
		
		
		
		
		GameDTO gameDTO = new GameDTO();
		gameDTO.setResultContent(result_content);  // 결과 저장
		//gameDTO.setResultNumber(result_number); // 입력숫자 저장
		
		return gameDTO;
	}

}

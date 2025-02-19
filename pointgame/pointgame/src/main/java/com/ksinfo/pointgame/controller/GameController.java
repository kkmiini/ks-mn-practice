package com.ksinfo.pointgame.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ksinfo.pointgame.dto.GameDTO;
import com.ksinfo.pointgame.service.GameService;
import com.ksinfo.pointgame.util.PlayGameUtil;

@Controller
@RequestMapping("/")
public class GameController {
	
	private final GameService gameService;
	
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	

	
	
	  // 포인트 정보 조회 (화면에 데이터 전달)
    @GetMapping("/game")
    public String getPoint(@RequestParam String memberId, Model model) {
    	
    	GameDTO pointInfo = gameService.getPointInfo(memberId); // DB에서 조회
        
        List<GameDTO> resultInfos = gameService.getResults(memberId); // 게임이력 DB에서 조회 


        // 조회된 포인트 정보를 모델에 추가하여 화면에 전달
        model.addAttribute("pointInfo", pointInfo);
        model.addAttribute("memberId", memberId);
        model.addAttribute("resultInfos", resultInfos);
        
        return "game"; // pointView.jsp 또는 pointView.html로 이동
        
   
    }
    
    @GetMapping("/play")
    public String saveResult(@RequestParam String memberId, @RequestParam int num1, int num2, int num3, Model model) {
    	
    	GameDTO pointInfo = gameService.getPointInfo(memberId);

    	
    	int secret_num = pointInfo.getSecretNumber();
    	int game_count = (pointInfo.getGameCount() == 0) ? 1 : pointInfo.getGameCount() + 1;
    	
	
    	  // Util 클래스로 값 전달
    	GameDTO resultValue = PlayGameUtil.processInput(num1, num2, num3, secret_num, game_count);
    	

    	gameService.saveInfo(
    			memberId,
    			resultValue.getGameCount(), 
    			resultValue.getResultNumber(), 
    			resultValue.getResultContent());
  
    	List<GameDTO> resultInfos = gameService.getResults(memberId); // 게임이력 DB에서 조회 
        model.addAttribute("pointInfo", pointInfo);
        model.addAttribute("resultInfos", resultInfos);
   
    	
    	return "game"; 
    }
    

}
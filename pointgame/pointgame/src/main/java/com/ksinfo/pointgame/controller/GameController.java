package com.ksinfo.pointgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ksinfo.pointgame.dto.GameDTO;
import com.ksinfo.pointgame.service.GameService;

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
        GameDTO hideNum = gameService.setHideNum(memberId); //숨김숫자 db 업데이트

        // 조회된 포인트 정보를 모델에 추가하여 화면에 전달
        model.addAttribute("pointInfo", pointInfo);
        model.addAttribute("memberId", memberId);
        
        return "game"; // pointView.jsp 또는 pointView.html로 이동
    }

}
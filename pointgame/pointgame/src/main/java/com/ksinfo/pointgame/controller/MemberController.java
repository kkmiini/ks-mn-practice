package com.ksinfo.pointgame.controller;

import com.ksinfo.pointgame.dto.GameDTO;
import com.ksinfo.pointgame.service.GameService;
import com.ksinfo.pointgame.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MemberController {
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 로그인 페이지 렌더링
	@GetMapping("/login")
	public String showLoginForm() {
		return "login"; // login.html을 Thymeleaf에서 렌더링
	}

	// 로그인 처리
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@PostMapping("/login")
	public String login(@RequestParam(required = false) String memberId,
			@RequestParam(required = false) String memberPassword, Model model) {
		// memberId가 입력되지 않았을 경우
		if (memberId == null || memberId.trim().isEmpty()) {
			model.addAttribute("error", "会員IDを６桁で入力してください");
			model.addAttribute("focus", "memberId");
			return "login";
		}

		// memberId가 숫자만 포함되어 있는지 검증
		if (!memberId.matches("^[0-9]+$")) {
			model.addAttribute("error", "会員IDは数字のみで入力してください");
			model.addAttribute("focus", "memberId");
			return "login";
		}

		// memberPassword가 입력되지 않았을 경우
		if (memberPassword == null || memberPassword.trim().isEmpty()) {
			model.addAttribute("error", "パスワードを８桁で入力してください");
			model.addAttribute("focus", "memberPassword");
			return "login";
		}

		// memberPassword가 영문자와 숫자만 포함되어 있는지 검증
		if (!memberPassword.matches("^[A-Za-z0-9]+$")) {
			model.addAttribute("error", "パスワードは半角英数字以外は入力できません");
			model.addAttribute("focus", "memberPassword");
			return "login";
		}

		try {
			// DB 연결 및 인증 시도
			boolean isAuthenticated = memberService.login(memberId, memberPassword);

			if (isAuthenticated) {
				return "redirect:/game?memberId=" + memberId;
			} else {
				// 인증 실패 시 로그 파일에 에러 메시지 기록
	            logger.error("IDまたはパスワードが間違っています。");
				model.addAttribute("error", "IDまたはパスワードが間違っています。");
				model.addAttribute("focus", "memberPassword");
				return "login";
			}
		} catch (DataAccessException e) {
			// DB 연결 실패 시 로그 파일에 에러 기록
			logger.error("DB接続時にエラーが発生しました。", e);
			// 화면에는 시스템 에러 메시지 표시
			model.addAttribute("error", "システムエラーが発生しました。");
			return "login";
		}
	}

}

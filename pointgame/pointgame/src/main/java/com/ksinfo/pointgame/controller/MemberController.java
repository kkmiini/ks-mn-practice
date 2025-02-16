package com.ksinfo.pointgame.controller;

import com.ksinfo.pointgame.service.MemberService;
import org.springframework.stereotype.Controller;
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
    @PostMapping("/login")
    public ModelAndView login(@RequestParam String memberId, @RequestParam String password) {
        ModelAndView modelAndView = new ModelAndView();
        if (memberService.authenticate(memberId, password)) {
            modelAndView.setViewName("redirect:/game"); // 로그인 성공 -> game.html로 리디렉트
        } else {
            modelAndView.setViewName("login"); // 로그인 실패 -> login.html 반환
            modelAndView.addObject("error", "아이디 또는 비밀번호가 잘못되었습니다.");
        }
        return modelAndView;
    }

    // 게임 페이지 렌더링
    @GetMapping("/game")
    public ModelAndView showGamePage() {
        return new ModelAndView("game"); // Thymeleaf의 game.html 반환
    }
}

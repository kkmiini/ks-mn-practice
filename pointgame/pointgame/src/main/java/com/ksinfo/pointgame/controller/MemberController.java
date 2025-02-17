package com.ksinfo.pointgame.controller;

import com.ksinfo.pointgame.dto.GameDTO;
import com.ksinfo.pointgame.service.GameService;
import com.ksinfo.pointgame.service.MemberService;
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
    @PostMapping("/login")
    public String login(@RequestParam String memberId, @RequestParam String memberPassword, Model model) {
        boolean isAuthenticated = memberService.authenticate(memberId, memberPassword);
        
        if (isAuthenticated) {
            return "redirect:/game?memberId=" + memberId; // ✅ memberId 전달
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login"; // 로그인 화면으로 다시 이동
        }
    }

}

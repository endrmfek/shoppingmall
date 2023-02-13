package com.practice.jpashoppingmall.controller;

import com.practice.jpashoppingmall.constant.Role;
import com.practice.jpashoppingmall.dto.MemberFormDto;
import com.practice.jpashoppingmall.entity.Member;
import com.practice.jpashoppingmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute("roles")
    public Role[] roles() {
        return Role.values();
    }

    /**
     * 회원가입 화면 진입
    * */
    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto" , new MemberFormDto());
        return "member/memberForm";
    }

    /**
     * 회원가입 폼 제출
     * */
    //ModelAttribute 생략 가능.
    @PostMapping("/new")
    public String memberForm(@Valid @ModelAttribute("memberFormDto") MemberFormDto memberFormDto,
                             BindingResult bindingResult,
                             Model model) {

        if(bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            //에러 발생시 에러를 모델에 담아 넘김
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

    /**
     * 로그인 화면 진입
     * */
    @GetMapping("/login")
    public String login() {
        return "/member/memberLoginForm";
    }

    /**
    * 로그인 에러 발생 시 진입 화면
    * */
    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }
}

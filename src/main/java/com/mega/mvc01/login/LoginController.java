package com.mega.mvc01.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mega.mvc01.UserVO;

@Controller

public class LoginController {

	@Autowired
	LoginService service;

	// 회원가입
	@RequestMapping("insert.do")
	public void create2(UserVO loginVO) {
		service.insert(loginVO);
	}

	// 아이디 중복확인
	@RequestMapping("select.do")
	public void select(UserVO loginvo, Model model) throws Exception {
		Boolean result = service.select(loginvo);
		System.out.println(result);
		model.addAttribute("result", result);

	}

	// 로그인
	@RequestMapping("select1.do")
	public void select1(UserVO loginvo, Model model, HttpSession session ) {

		int result = service.select1(loginvo);
		System.out.println(result);
		model.addAttribute("result", result);

		if (result == 1) {
			session.setAttribute("user_id", loginvo.getUser_id() );
			System.out.println(session.getAttribute("user_id"));
		}
		 
		 
	}

	@RequestMapping("select2.do")
	public void select2(UserVO loginvo, Model model) {
		UserVO vo = service.select2(loginvo);
		System.out.println(loginvo);
		model.addAttribute("vo", vo);
	}

	// id찾기 중복확인
	@RequestMapping("select3.do")
	public void select3(UserVO loginvo, Model model) {
		int result = service.select3(loginvo);
		System.out.println(result);
		model.addAttribute("result", result);
	}

	// id찾기
	@RequestMapping("select4.do")
	public void select4(UserVO loginvo, Model model) {
		UserVO vo = service.select4(loginvo);
		System.out.println(loginvo);
		model.addAttribute("vo", vo);
	}

	// pw찾기 중복확인
	@RequestMapping("select5.do")
	public void select5(UserVO loginvo, Model model) {
		int result = service.select5(loginvo);
		System.out.println(result);
		model.addAttribute("result", result);
	}

	// pw찾기
	@RequestMapping("select6.do")
	public void select6(UserVO loginvo, Model model) {
		UserVO vo = service.select6(loginvo);
		model.addAttribute("vo", vo);

	}

}

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

	// �쉶�썝媛��엯
	@RequestMapping("login/insert.do")
	public String create2(UserVO loginVO) {
		service.insert(loginVO);
		return "redirect:/login/logn.do";		
	}

	// �븘�씠�뵒 以묐났�솗�씤
	@RequestMapping("login/select.do")
	public void select(UserVO loginvo, Model model) throws Exception {
		Boolean result = service.select(loginvo);
		System.out.println(result);
		model.addAttribute("result", result);

	}

	// 濡쒓렇�씤
	@RequestMapping("login/select1.do")
	public void select1(UserVO loginvo, Model model, HttpSession session ) {

		int result = service.select1(loginvo);
		System.out.println(result);
		model.addAttribute("result", result);

		if (result == 1) {
			session.setAttribute("user_id", loginvo.getUser_id() );
			System.out.println(session.getAttribute("user_id"));
		}
		 
		 
	}

	@RequestMapping("login/select2.do")
	public void select2(UserVO loginvo, Model model) {
		UserVO vo = service.select2(loginvo);
		System.out.println(loginvo);
		model.addAttribute("vo", vo);
	}

	// id李얘린 以묐났�솗�씤
	@RequestMapping("login/select3.do")
	public void select3(UserVO loginvo, Model model) {
		int result = service.select3(loginvo);
		System.out.println(result);
		model.addAttribute("result", result);
	}

	// id李얘린
	@RequestMapping("login/select4.do")
	public void select4(UserVO loginvo, Model model) {
		UserVO vo = service.select4(loginvo);
		System.out.println(loginvo);
		model.addAttribute("vo", vo);
	}

	// pw李얘린 以묐났�솗�씤
	@RequestMapping("login/select5.do")
	public void select5(UserVO loginvo, Model model) {
		int result = service.select5(loginvo);
		System.out.println(result);
		model.addAttribute("result", result);
	}

	// pw李얘린
	@RequestMapping("login/select6.do")
	public void select6(UserVO loginvo, Model model) {
		UserVO vo = service.select6(loginvo);
		model.addAttribute("vo", vo);

	}
	//  濡쒓렇�씤�뱾�뼱媛�湲�
	@RequestMapping("login/logn.do")
	public void logingo() {
	
	}

	// pw�뱾�뼱媛�湲�
	@RequestMapping("login/pw.do")
	public void pwgo() {
		
	}
	// id�뱾�뼱媛�湲�
	@RequestMapping("login/id.do")
	public void idgo() {
		
	}
	// �쉶�썝媛��엯�뱾�뼱媛�湲�
	@RequestMapping("login/Member.do")
	public void mambergo() {
		
	}

}

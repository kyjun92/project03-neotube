package com.mega.mvc01.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mega.mvc01.ReplyVO;

@Controller
public class ReplyController {

	@Autowired
	ReplyService service;

	// 글쓱기
	@RequestMapping("client/create.do")
	public String create2(ReplyVO replyVO) {
	int result = service.create(replyVO);
	System.out.println(result);
		if (result==1) {
			return "ok";
		}else {
			return "fail";
		}
	} 

	// 글삭제
	@RequestMapping(value="client/delete3.do", method=RequestMethod.GET)
	public String delete(ReplyVO replyVO) {
		System.out.println("컨트롤러로 이동성공");
		
		int result = service.delete(replyVO); 
				
		if(result == 1) {
			//response.sendRedirect("one.do?no="+replyVO.getOriginal());
			return "redirect:/client/one.do?border_id="+replyVO.getBorder_id(); 
		}else {
			return "fail";// views/fail.jsp
		}
		
	}
	@RequestMapping("next.do")
	public int next(ReplyVO replyVO) {
	int result = service.next(replyVO);
	
	 return result;
}
}

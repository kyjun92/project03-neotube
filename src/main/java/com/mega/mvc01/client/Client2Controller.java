package com.mega.mvc01.client;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mega.mvc01.Client2VO;
import com.mega.mvc01.UserVO;



@Controller
public class Client2Controller {

	@Autowired
	Client2Service service;

	@Autowired
	ReplyService service2;
	
	
     //  게시판 내용 
	@RequestMapping("client/one.do")
	public void one(Client2VO ClientVO, Model model, HttpSession session) {

		session.setAttribute("id", "<%=user_id%>");

		model.addAttribute("one", service.one(ClientVO));
        System.out.println(service.one(ClientVO)+"이거이거이거이거이거이거");
		model.addAttribute("list", service2.list(ClientVO.getBorder_id()));
		model.addAttribute("total", service2.list(ClientVO.getBorder_id()).size());
	}
	// 글쓱기
	@RequestMapping("client/insert01.do")
	public void create2(Client2VO client2VO) {
		service.insert(client2VO);
		System.out.println(client2VO);
	}

	// 글삭제
	@RequestMapping("client/delete01.do")
	public void delete(Client2VO client2VO) {
		service.deleteReply(client2VO);
		service.delete(client2VO);
	}
	// 글수정(화면)
	@RequestMapping("client/update.do")
	public void update1(Client2VO client2VO,Model model) {
	model.addAttribute("VO",service.one(client2VO));
	}
	// 글수정
	@RequestMapping("client/update01.do")
	public void update(Client2VO client2VO) {
		System.out.println(client2VO);
		service.update(client2VO);
	}
	//게시판 메인넘기기
	@RequestMapping("client/client.do")
	public void list(Client2VO client2vo,Model model,@RequestParam(defaultValue="1")int pageNo,@RequestParam(defaultValue="10")int pageSize) {
		int size = service.boardsize();
		int totalPage = size/pageSize;
		
		if(size % pageSize > 0) 
			totalPage++;
		
		if(pageNo < 1 || pageNo >pageSize)
			pageNo = 1;
		
		List<Client2VO> boards = service.list(pageNo, pageSize);
		model.addAttribute("boards", boards);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("size",size);
		model.addAttribute("beginPage",(pageNo - 2) > 0 ?(pageNo - 2 ):1);
		model.addAttribute("endPage",(pageNo + 2) < totalPage ?(pageNo + 2 ):totalPage);
		
		// model.addAttribute("list",service.borderList(client2vo));
	}
	
	//글쓰기 들어가기
	@RequestMapping("client/Client2.do")
	public void writing() {
	}
	
	
	
	// 회원수정
	@RequestMapping("client/Modification.do")
	public void Modification(UserVO loginvo ,Model model) {
		System.out.println(loginvo);
			UserVO vo=service.Information(loginvo);
			System.out.println(vo.getName());
			model.addAttribute("vo", vo);
			}
	
	
	//회원삭제
		@RequestMapping("client/taltoe.do")
		public void taltoe (UserVO loginvo ) {
			service.taltoeReply(loginvo);
			service.taltoeBorder(loginvo);
		    service.taltoe(loginvo);
		}
		
		// 회원정보
		@RequestMapping("client/information.do")
		public void Information(UserVO loginvo ,Model model) {
		UserVO vo =service.Information(loginvo);
			System.out.println(loginvo);
		    model.addAttribute("vo",vo);
		}
		
		// 회원수정
		@RequestMapping("client/Modification1.do")
		public String Modify(UserVO loginvo ) {
		 int result =service.Modify(loginvo);
				System.out.println(result+"리절트값");
			if (result==1) {
				System.out.println("안녕 재대로왓어");
			}	
				
				
				return result+"";
				
				
				}
		
		@RequestMapping("client/searchList.do")
		public void serach(Client2VO client2VO,Model model) {
			System.out.println(client2VO.getBorder_title());
			List<Client2VO> list= service.borderList(client2VO);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
				
			}
			model.addAttribute("list1", list);
		}
	 // @RequestMapping("client/client.do")	
	  //public void client() {
		  
	 // }
}

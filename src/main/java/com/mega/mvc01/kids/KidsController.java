package com.mega.mvc01.kids;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("kids")
@Controller
public class KidsController {

	@Autowired
	KidsService service;
	
	//페이지 리다이렉터
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	@RequestMapping("/popular")
	public String popular() {
		return "popular";
	}
	@RequestMapping("/subscribelist")
	public String subscribelist() {
		return "subscribelist";
	}
	@RequestMapping("/likelist")
	public String likelist() {
		return "likelist";
	}
	@RequestMapping("/history")
	public String history() {
		return "history";
	}
	
	//전체리스트 뽑아오기
	@RequestMapping("/list")
	public String listByCategory(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);
		List<KidsVO> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "list";
	}
	
	//구독 기준 리스트 뽑아오기
	@RequestMapping("/listbysubscribe")
	public String listBySubscribe(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);;
		List<KidsVO> bag = service.listBySubscribe(vo);
		model.addAttribute("bag", bag);
		return "list";
	}
	
	
	//좋아요 기준 리스트 뽑아오기
	@RequestMapping("/listbylike")
	public String listByLike(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);;
		List<KidsVO> bag = service.listByLike(vo);
		model.addAttribute("bag", bag);
		return "list";
	}
	
	//시청이력기준 리스트 뽑아오기
	@RequestMapping("/listbyhistory")
	public String listByHistory(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);;
		List<KidsVO> bag = service.listByHistory(vo);
		model.addAttribute("bag", bag);
		return "list";
	}
	
	public List<KidsVO> listBySearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//비디오 1개 정보 뽑아오기 
	@RequestMapping("/video")
	public String getVideo(String id, String user_id, Model model) {
		KidsVO vo = new KidsVO();
		vo.setVideo_id(id);
		KidsVO video = service.one(vo);
		model.addAttribute("video", video);
		
		String[] taglist = video.getTag().split(" ");
		model.addAttribute("taglist", taglist);
		
		return "video";
	}
	
	//시청이력 추가하기
	@RequestMapping("/addhistory")
	public String addHistory(UserControlVO vo) {
		int result = service.addHistory(vo);
		return "addhistory";
	}
	
	//좋아요 버튼
	//결과는 "do", "undo", "toggle" 중 하나이며 결과에 따라서 페이지의 동작이 다르다.
	@RequestMapping("/like")
	public String likeVideo(UserControlVO vo, Model model) {
		String result = service.likeVideo(vo); //"do", "undo", "toggle"
		model.addAttribute("result", result);
		return "like";
	}
	
	//싫어요 버튼
	@RequestMapping("/dislike")
	public String dislikeVideo(UserControlVO vo, Model model) {
		String result = service.dislikeVideo(vo);
		model.addAttribute("result", result);
		return "dislike";
	}
	
	//구독 버튼
	@RequestMapping(value="/subscribe", method=RequestMethod.POST)
	public String subscribe(UserControlVO vo, Model model) {
		String result = service.subscribe(vo); //"do", "undo"
		model.addAttribute("result", result);
		return "subscribe";
	}
	
	//댓글 가져오기
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String getReply(ReplyVO vo, Model model) {
		List<ReplyVO> bag = service.getReply(vo);
		model.addAttribute("reply_bag", bag);
		return "reply";
	}
	
	//댓글 쓰기
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String postReply(ReplyVO vo, Model model) {
		ReplyVO result = service.postReply(vo);
		model.addAttribute("replyvo", result);
		return "submitreply";
	}
	
	//다음 동영상
	@RequestMapping("/nextvideo")
	public String nextVideoList(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 20);
		vo.setAmount(20);
		List<KidsVO> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "nextvideo";
	}

	public void upload(KidsVO vo) {
		// TODO Auto-generated method stub

	}
	
}

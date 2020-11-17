package com.mega.mvc01.sport;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mega.mvc01.JoinVO;
import com.mega.mvc01.JoinVideoUserlikeVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;


@Controller
public class VideoController {

	@Autowired
	VideoService service;
	
	
	/*
	@RequestMapping("")
	public void one() {
		
	}
	 */
	
	//홈
	@RequestMapping("sports/main_view.do")
	public void list(Model model,JoinVO joinVO) throws Exception {
		List<VideoVO> list = service.recommandAlgorithm();
		model.addAttribute("list" ,list);
	}
	
	//인기
	@RequestMapping("sports/popular.do")
	public void popular(Model model) {
		List<JoinVO> list = service.popular();
		model.addAttribute("list" ,list);
	} 
	
	//결제 api
	@RequestMapping("payment/payment.do")
	public void payment() {
	//결제창으로 넘겨주기	
	}
	
	//view -> likepage
	@RequestMapping("sports/likepage.do")
	public void likepage(Model model) {	
		 List<JoinVideoUserlikeVO> list =service.likepage();
		 model.addAttribute("list", list);
		 System.out.println(list.size());
	}
	
	//watch 창
	@RequestMapping("sports/watch.do")
	public void watch(VideoVO videoVO, Model model , ChannelVO channelVO) {
		model.addAttribute("list", service.list(videoVO.getVideo_id()));
		model.addAttribute("list2", service.list2(channelVO.getChannel_id()));
	} 
	
	//main 창
		@RequestMapping("../../main.jsp")
		public void main() {
		}
		
		
		
//		@RequestMapping("sports/like.do")
//		public String likeInsert(UserlikeVO userlikeVO) {
//			service.likeinsert(userlikeVO);
//			return "redirect:watch.do";
//		}
		
		//record insert
		@RequestMapping("sports/user_record.do")
		public void user_record(UserRecordVO userrecordVO) {
			service.user_recordInsert(userrecordVO);
		}
		
		//record_view
		@RequestMapping("sports/record_view.do")
		public void record_view(Model model) {
			List<VideoVO> list = service.record_view();
			model.addAttribute("list", list);
		}
		
		@RequestMapping("sports/subscribeDispose.do")
		public void subscribe(SubscribeVO subscribeVO,Model model) {
			int a = service.startSubscribe(subscribeVO);
			model.addAttribute("sub", a);
		}
		
		@RequestMapping("sports/subscribeBoolean.do")
		public void subscribeBoolean(SubscribeVO subscribeVO,Model model) {
				int a = service.selectSubscribe2(subscribeVO);
				model.addAttribute("sub", a);
		}
		
		@RequestMapping("sports/subscribe.do")
		public void subscribeList(String user_id,Model model) {
			List<VideoVO> list = service.subscribeList(user_id);
			String a = list.get(1).getChannel_title();
			System.out.println(a);
			model.addAttribute("list", list);
		}
		
		//클릭시 마다 조회수 +1
		@RequestMapping("sports/viewnum_update")
		public void viewnumUpdate(String video_id) {
			service.viewnumUpdate(video_id);
		}
		
		@RequestMapping("sports/likeButton.do")
		public void likeButton(UserLikeVO userLikeVO,Model model) {
			int a = service.like(userLikeVO);
			System.out.println("likebutton a값>>"+ a);
			model.addAttribute("like", a);
		}
		
		@RequestMapping("sports/dislikeButton.do")
		public void dislikeButton(UserLikeVO userLikeVO,Model model) {
			int a = service.dislike(userLikeVO);
			System.out.println("likebutton a값>>"+ a);
			model.addAttribute("like", a);
		}
		
		//좋아요 테이블에 값대입
		@RequestMapping("sports/makeLike.do")
		public void makeLike(UserLikeVO userLikeVO,Model model) {
			service.insertUserLike(userLikeVO);//like_index대입
			int likeIndex = service.selectLikeindex(userLikeVO);
			System.out.println("controller단 likeindex>>>> " +likeIndex);
			model.addAttribute("like", likeIndex);
		}
		
		
		
		/*
	@RequestMapping("")
	public void update() {
		
	}
	@RequestMapping("")
	public void delete() {
		
	}
*/
	
}

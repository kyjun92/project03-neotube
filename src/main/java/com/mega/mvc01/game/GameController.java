package com.mega.mvc01.game;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.ServiceInterface;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

import sun.print.resources.serviceui;

@Controller
public class GameController {

	@Autowired
	ServiceInterface gameService; // 서비스단 싱글톤으로 호출
	
	@Autowired
	TransactionService tranService;

	
	@RequestMapping("select_main.game")
	@ResponseBody // jackson lib를 이용하여 list를 json으로 변환하여 전달
	public List<VideoVO> select_main2(int page_index, Model model, HttpSession session) {
		// 메인 페이지 시작시 나열되는 동영상 정보를 Read
		String userId = session.getAttribute("user_id") + "";
		List<VideoVO> list = gameService.select_main(userId, page_index);		
		model.addAttribute("json", list);
		
		return list;
	}

	
	@RequestMapping("playingPage.game")
	public void playingPage(String videoId, Model model, HttpSession session) {	// playing_page에 표시될 정보들을 불러옴
		int result = 1;
		String userId = session.getAttribute("user_id") + ""; // session으로 잡혀있는 id값을 가져옴
		
		VideoVO videoVO = gameService.playingVideo(videoId); 	// 재생한 비디오의 정보 조회
		model.addAttribute("videoVO", videoVO);					// 비디오 정보를 model로 묶음
		ChannelVO channelVO = gameService.selectChannel(videoVO.getChannel_id()); 	// 재생한 비디오를 등록한 채널 정보
		model.addAttribute("channelVO", channelVO);									//채널 정보 model
		if(!userId.equals("null")) {							// login상태를 체크
			//gameService.updatePlaynum(videoId); 				// 비디오의 조회수 +1
			int like = gameService.selectLike(userId, videoId); // user_like table 에서 like 정보 따옴
			model.addAttribute("like", like);					// 해당 영상에 대한 like_index를 넘김
			
			UserRecordVO userRecordVO = new UserRecordVO(); // 새로운 vo를 선언하여
			userRecordVO.setUser_id(userId);				//user_id와 video_id를 묶어서 Service로 넘김
			userRecordVO.setVideo_id(videoId);
			//gameService.inserUserRecord(userRecordVO); 		// user_record table에 조회 기록 등록
			try {												//transaction 으로 조회수+1 과 user_record update를 부가기능으로 묶음
				result = tranService.tran(userRecordVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SubscribeVO subscribeVO = new SubscribeVO();
			subscribeVO.setUser_id(userId);
			subscribeVO.setChannel_id(videoVO.getChannel_id());
			int sub = gameService.startSubscribe(subscribeVO);
			model.addAttribute("sub", sub);
			model.addAttribute("result", result);
		}
		
		

	}
	
	@RequestMapping("updateLike.game")
	public void leavePlayingPage(String videoId, byte like, byte likeOrigin, Model model, HttpSession session) {
		// 페이지를 나갈 때, 변경된 좋아요 정보 수정
		String userId = session.getAttribute("user_id") + "";
		if(!userId.equals("null")) {
			
			UserLikeVO vo = new UserLikeVO();
			vo.setUser_id(userId);
			vo.setVideo_id(videoId);
			vo.setLike_index(like);
			gameService.updateLike(vo);
			String[] l = {videoId, like+"", likeOrigin+""}; // 비디오id, 변경 like, 변경 전 like 정보를 배열로 묶어서 서비스단으로 보냄
			gameService.updateLikeNum(l);
		}
		
	}
	
	// 수정되는 구독정보를 update하는 controller
	
	@RequestMapping("updateSuscribe.game")
	public void updateSubscribe(String channelId, Model model, HttpSession session) {
		String userId = session.getAttribute("user_id") + "";
		SubscribeVO vo = new SubscribeVO();
		vo.setUser_id(userId);
		vo.setChannel_id(channelId);
		int sub = gameService.updateSubscibe(vo);
		model.addAttribute("result", sub);
	}
	
	@RequestMapping("recommand.game")
	public void reco(Model model, HttpSession session) {
		String user_id = session.getAttribute("user_id") + "";
		String result = gameService.reco(user_id);
		
		model.addAttribute("views", result);
		
		
	}
	
	@RequestMapping("randomInsert.game")
	public void random() {
		gameService.random();
	}
	

//	@RequestMapping("selectLike.game")
//	public void selectLike(String videoId, Model model, HttpSession session) {
//		String userId = session.getAttribute("id") + "";
//		System.out.println(videoId);
//		int like = gameService.selectLike(userId, videoId);
//
//		model.addAttribute("like", like);
//	}

}

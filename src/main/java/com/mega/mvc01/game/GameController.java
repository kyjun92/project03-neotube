package com.mega.mvc01.game;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Controller
public class GameController {

	@Autowired
	GameService gameService; // 서비스단 싱글톤으로 호출

	@RequestMapping("select_main.game")
	@ResponseBody // jackson lib를 이용하여 list를 json으로 변환하여 전달
	public List<VideoVO> select_main2(Model model, HttpSession session) {
		// 메인 페이지 시작시 나열되는 동영상 정보를 Read
		List<VideoVO> list = gameService.select_main();
		model.addAttribute("json", list);
		
		return list;
	}

	@RequestMapping("playingPage.game")
	public void playingPage(String videoId, Model model, HttpSession session) {
		String userId = session.getAttribute("id") + "";
		int like = gameService.selectLike(userId, videoId); // user_like table 에서 like 정보 따옴
		model.addAttribute("like", like);

		gameService.updatePlaynum(videoId); // 비디오의 조회수 +1

		VideoVO videoVO = gameService.playingVideo(videoId); // 재생한 비디오의 정보 조회
		model.addAttribute("videoVO", videoVO);

		ChannelVO channelVO = gameService.selectChannel(videoVO.getChannel_id()); // 재생한 비디오를 등록한 채널 정보
		model.addAttribute("channelVO", channelVO);

		UserRecordVO userRecordVO = new UserRecordVO();
		userRecordVO.setUser_id(userId);
		userRecordVO.setVideo_id(videoId);
		gameService.inserUserRecord(userRecordVO); // user_record table에 조회 기록 등록

	}
	@RequestMapping("updateLike.game")
	public void leavePlayingPage(String videoId, byte like, byte likeOrigin, Model model, HttpSession session) {
		// 페이지를 나갈 때, 변경된 좋아요 정보 수정
		String userId = session.getAttribute("id") + "";
		UserLikeVO vo = new UserLikeVO();
		vo.setUser_id(userId);
		vo.setVideo_id(videoId);
		vo.setLike(like);
		gameService.updateLike(vo);
		String[] l = {videoId, like+"", likeOrigin+""}; // 비디오id, 변경 like, 변경 전 like 정보를 배열로 묶어서 서비스단으로 보냄
		gameService.updateLikeNum(l);
		
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

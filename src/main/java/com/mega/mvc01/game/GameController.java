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
	@ResponseBody  // jackson lib를 이용하여 list를 json으로 변환하여 전달
	public List<VideoVO> select_main2(Model model, HttpSession session) {
		List<VideoVO> list = gameService.select_main();
		model.addAttribute("json", list);
		
		session.setAttribute("id", "kyjun92");
		
		return list;
		
	}
	
	@RequestMapping("playingPage.game")
	public void playingPage(String videoId, Model model, HttpSession session) {
		gameService.updatePlaynum(videoId);
		VideoVO videoVO = gameService.playingVideo(videoId);
		model.addAttribute("videoVO", videoVO);
		ChannelVO channelVO = gameService.selectChannel(videoVO.getChannel_id());
		model.addAttribute("channelVO", channelVO);
		UserRecordVO userRecordVO = new UserRecordVO();
		userRecordVO.setUser_id(session.getAttribute("id")+"");
		userRecordVO.setVideo_id(videoId);
		gameService.inserUserRecord(userRecordVO);
		
		
	}
	
	@RequestMapping("selectLike.game")
	public void selectLike(String videoId, Model model, HttpSession session) {
		String userId = session.getAttribute("id") +"";
		int like = gameService.selectLike(userId, videoId);
		
		model.addAttribute("like", like);
	}
	
}

package com.mega.mvc01.cooking;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Controller
public class CookingController {

	@Autowired
	CookingService cookingService; // 서비스단 싱글톤으로 호출

	@RequestMapping("cooking/select_main.cooking")
	@ResponseBody // jackson lib를 이용하여 list를 json으로 변환하여 전달
	public List<VideoVO> select_main2(Model model, HttpSession session) {

		List<VideoVO> list = cookingService.select_main();
		model.addAttribute("json", list);

		return list;
	}

	@RequestMapping("cooking/playingPage.cooking")
	public void playingPage(String videoId, Model model, HttpSession session) {
		cookingService.updatePlaynum(videoId);
		
		VideoVO videoVO = cookingService.playingVideo(videoId);
		model.addAttribute("videoVO", videoVO);
		
		ChannelVO channelVO = cookingService.selectChannel(videoVO.getChannel_id());
		model.addAttribute("channelVO", channelVO);
		
		UserRecordVO userRecordVO = new UserRecordVO();
		userRecordVO.setUser_id(session.getAttribute("id") + "");
		userRecordVO.setVideo_id(videoId);
		cookingService.insertUserRecord(userRecordVO);
		System.out.println("콘트롤: "+videoVO.getChannel_id());
	
		SubscribeVO subscribeVO = new SubscribeVO();
		subscribeVO.setUser_id(session.getAttribute("id") + "");
		subscribeVO.setChannel_id(videoVO.getChannel_id());
		int sub = cookingService.startSubscribe(subscribeVO);
		model.addAttribute("sub", sub);
	}
	
	@RequestMapping("cooking/updateSuscribe.cooking")
	public void updateSubscribe(String channelId, Model model, HttpSession session) {
		String userId = session.getAttribute("id") + "";
		SubscribeVO vo = new SubscribeVO();
		vo.setUser_id(userId);
		vo.setChannel_id(channelId);
		int sub = cookingService.updateSubscibe(vo);
		model.addAttribute("result", sub);
		System.out.println(sub);
	}

}

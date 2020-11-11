package com.mega.mvc01;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface ConInterface {

	List<VideoVO> select_main2(int page_index, Model model, HttpSession session);
	
	void playingPage(String videoId, Model model, HttpSession session);

	void leavePlayingPage(String videoId, byte like, byte likeOrigin, Model model, HttpSession session);

	void updateSubscribe(String channelId, Model model, HttpSession session);
}
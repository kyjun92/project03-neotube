package com.mega.mvc01;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public interface ServiceInterface {

	List<VideoVO> select_main(String userId, int page_index);
	
	VideoVO playingVideo(String videoId);

	ChannelVO selectChannel(String channelId);

	void updatePlaynum(String videoId);

	void inserUserRecord(UserRecordVO vo);

	int selectLike(String userId, String videoId);

	void updateLike(UserLikeVO vo);

	void updateLikeNum(String[] l);
	
	int startSubscribe(SubscribeVO vo);
	
	int updateSubscibe(SubscribeVO vo);
	

}
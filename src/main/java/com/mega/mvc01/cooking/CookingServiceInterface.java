package com.mega.mvc01.cooking;

import java.util.List;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

public interface CookingServiceInterface {

	List<VideoVO> select_main(String userId, int page_index) throws Exception;

	VideoVO playingVideo(String videoId);

	ChannelVO selectChannel(String channelId);

	void updatePlaynum(String videoId);

	void insertUserRecord(UserRecordVO vo);

	int selectLike(String userId, String videoId);

	void updateLike(UserLikeVO vo);

	void updateLikeNum(String[] l);

	int startSubscribe(SubscribeVO vo);

	int updateSubscibe(SubscribeVO vo);

	void error(String userId) throws Exception;

}
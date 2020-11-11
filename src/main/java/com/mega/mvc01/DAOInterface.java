package com.mega.mvc01;

import java.util.List;

public interface DAOInterface {

	List<VideoVO> select_main(String userId, int page_index);
	
	VideoVO playingVideo(String videoId);

	ChannelVO selectChannel(String channelId);

	void updatePlaynum(String videoId);

	void insertUserRecord(UserRecordVO vo);

	int selectLike(String userId, String videoId);

	void updateLike(UserLikeVO vo);

	void updateLikeNum(String[] l);
	
	void insertSubscribe(SubscribeVO vo);
	
	int selectSubscribe(SubscribeVO vo);
	
	int selectSubscribe2(SubscribeVO vo);
	
	void updateSubscribe(SubscribeVO vo);

}
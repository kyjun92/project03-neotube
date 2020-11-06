package com.mega.mvc01.game;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Service
public class GameService {

	@Autowired
	GameDAO dao;
	
	public List<VideoVO> select_main() {
		return dao.select_main();
	}
	public VideoVO playingVideo(String videoId) {
		return dao.playingVideo(videoId);
	}
	public ChannelVO selectChannel(String channelId) {
		return dao.selectChannel(channelId);
	}
	public void updatePlaynum(String videoId) {
		dao.updatePlaynum(videoId);
	}
	public void inserUserRecord(UserRecordVO vo) {
		dao.insertUserRecord(vo);
	}
	
	public UserLikeVO selectLike(String userId, String videoId) {
		
		return dao.selectLike(userId, videoId);
	}
}

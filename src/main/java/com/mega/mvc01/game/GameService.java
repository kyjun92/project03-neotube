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
	
	public List<VideoVO> select_main() { //메인 동영상 리스트 select
		return dao.select_main();
	}
	public VideoVO playingVideo(String videoId) { // 선택한 동영상 정보 select
		return dao.playingVideo(videoId);
	}
	public ChannelVO selectChannel(String channelId) { // 선택한 동영상의 채널 정보 select
		return dao.selectChannel(channelId);
	}
	public void updatePlaynum(String videoId) { // 선택한 동영상의 조회수 +1 update
		dao.updatePlaynum(videoId);
	}
	public void inserUserRecord(UserRecordVO vo) { // 선택한 동영상의 조회기록을 update
		dao.insertUserRecord(vo);
	}
	
	public int selectLike(String userId, String videoId) { //해당 유저가 선택한 동영상의 like 정보 select
		return dao.selectLike(userId, videoId);
	}
	public void updateLike(UserLikeVO vo) { // 변경된 좋아요/싫어요 정보 수정 update
		dao.updateLike(vo);
	}
	public void updateLikeNum(String[] l) { // 변경된 좋아요/싫어요 숫자 수정 update
		dao.updateLikeNum(l);
	}
}

package com.mega.mvc01.game;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Repository
public class GameDAO {

	@Autowired
	SqlSessionTemplate mybatis;
	
	public List<VideoVO> select_main() {
		List<VideoVO> list = mybatis.selectList("game.game_list");
		
		String a = new String();
		return list;
	}
	
	public VideoVO playingVideo(String videoId) {
		VideoVO vo2 = mybatis.selectOne("game.playingVideo", videoId);
		return vo2;
	}
	public ChannelVO selectChannel(String channelId) {
		ChannelVO vo2 = mybatis.selectOne("game.selectChannel", channelId);
		
		return vo2;
	}
	public void updatePlaynum(String videoId) {
		mybatis.update("game.updatePlaynum", videoId);
	}
	public void insertUserRecord(UserRecordVO vo) {
		mybatis.insert("game.insertUserRecord", vo);
	}
}

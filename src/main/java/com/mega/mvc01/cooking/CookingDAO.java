package com.mega.mvc01.cooking;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Repository
public class CookingDAO {

	@Autowired
	SqlSessionTemplate mybatis;

	// 메인 페이지에서 비디오 항목에 따른 전체 목록을 불러오기 위해
	public List<VideoVO> select_main() {
		List<VideoVO> list = mybatis.selectList("cooking.cooking_list");
		return list;
	}

	// 재생할 동영상을 선택하고 해당 동영상의 정보를 불러오기 위해
	public VideoVO playingVideo(String videoId) {
		VideoVO vo2 = mybatis.selectOne("cooking.playingVideo", videoId);
		return vo2;
	}

	// 재생화면으로 들어갔을 때, 채널의 정보를 불러오기 위해
	public ChannelVO selectChannel(String channelId) {
		ChannelVO vo2 = mybatis.selectOne("cooking.selectChannel", channelId);

		return vo2;
	}
	
	// 조회수 업데이트를 위해
	public void updatePlaynum(String videoId) {
		mybatis.update("cooking.updatePlaynum", videoId);
	}

	// 해당 유저의 시청기록을 db에 등록하기 위해
	public void insertUserRecord(UserRecordVO vo) {
		mybatis.insert("cooking.insertUserRecord", vo);
	}
	
	// 구독을 누르기 전에 해당 유저가 채널의 구독 여부를 반별해야하는데 db에 목록이 없기 때문에
	// 먼저 유저의 채널 구독 여부를 db에 저장하고 시작하기 위해
	public void insertSubscribe(SubscribeVO vo) {
		System.out.println("dao: " + vo.getUser_id()+vo.getChannel_id());
		mybatis.insert("cooking.insertSubscribe", vo);
	}
	
	// 구독을 했는지의 여부를 판단하고 값을 반환받기 위해
	// 해당 목록의 숫자를 받아와서 판단
	public int selectSubscribe(SubscribeVO vo) {
		return mybatis.selectOne("cooking.selectSubscribe", vo);
	}

	// 구독을 했는지의 여부를 판단하고 값을 반환받기 위해
	// 구독의 값을 받아서 판단
	public int selectSubscribe2(SubscribeVO vo) {
		return mybatis.selectOne("cooking.selectSubscribe2", vo);
	}
	
	// 구독 버튼을 누르면 정보를 업데이트 시켜 주기 위해
	public void updateSubscribe(SubscribeVO vo) {
		mybatis.update("cooking.updateSubscribe", vo);
	}
}

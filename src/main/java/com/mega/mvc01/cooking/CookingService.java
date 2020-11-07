package com.mega.mvc01.cooking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Service
public class CookingService {

	@Autowired
	CookingDAO dao;

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

	public void insertUserRecord(UserRecordVO vo) {
		dao.insertUserRecord(vo);
	}
	
	public int startSubscribe(SubscribeVO vo) {
		System.out.println("서비스: "+vo.getUser_id()+vo.getChannel_id());
		int a = dao.selectSubscribe(vo); //구독을 했는지 데이터가 있는지를 판단하기 위한 구문
		if ( a == 0) { //컬럼의 개수가 0이면 항목이 없다는 소리
			dao.insertSubscribe(vo); // 구독을 판단할 데이터 생성
		}
		return dao.selectSubscribe2(vo); // 생성 후에 그 데이터의 값을 반환 boolean의 값이기 때문에 0,1로 표기 됨
	}
	
	public int updateSubscibe(SubscribeVO vo) {
		int a = dao.selectSubscribe2(vo); // 해당 데이터의 구독 값이 참인지 거짓인지 판단하고 업뎃
		if (a==0) {
			vo.setSubscribe(true);
			dao.updateSubscribe(vo);
		} else {
			vo.setSubscribe(false);
			dao.updateSubscribe(vo);
		}
		return dao.selectSubscribe2(vo); // 업뎃이후의 값을 반환
	}
}

package com.mega.mvc01.game;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.DAOInterface;
import com.mega.mvc01.ServiceInterface;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Service
public class GameService implements ServiceInterface {

	@Autowired
	DAOInterface dao;
	
	@Override
	public List<VideoVO> select_main(String userId, int page_index) { //메인 동영상 리스트 select
		return dao.select_main(userId, page_index);
	}
	
	
	@Override
	public VideoVO playingVideo(String videoId) { // 선택한 동영상 정보 select
		return dao.playingVideo(videoId);
	}
	@Override
	public ChannelVO selectChannel(String channelId) { // 선택한 동영상의 채널 정보 select
		return dao.selectChannel(channelId);
	}
	@Override
	public void updatePlaynum(String videoId) { // 선택한 동영상의 조회수 +1 update
		dao.updatePlaynum(videoId);
	}
	@Override
	public void inserUserRecord(UserRecordVO vo) { // 선택한 동영상의 조회기록을 update
		dao.insertUserRecord(vo);
	}
	
	@Override
	public int selectLike(String userId, String videoId) { //해당 유저가 선택한 동영상의 like 정보 select
		return dao.selectLike(userId, videoId);
	}
	@Override
	public void updateLike(UserLikeVO vo) { // 변경된 좋아요/싫어요 정보 수정 update
		dao.updateLike(vo);
	}
	@Override
	public void updateLikeNum(String[] l) { // 변경된 좋아요/싫어요 숫자 수정 update
		dao.updateLikeNum(l);
	}
	@Override
	public int startSubscribe(SubscribeVO vo) {
		int a = dao.selectSubscribe(vo); //구독을 했는지 데이터가 있는지를 판단하기 위한 구문
		if ( a == 0) { //컬럼의 개수가 0이면 항목이 없다는 소리
			dao.insertSubscribe(vo); // 구독을 판단할 데이터 생성
		}
		return dao.selectSubscribe2(vo); // 생성 후에 그 데이터의 값을 반환 boolean의 값이기 때문에 0,1로 표기 됨
	}
	@Override
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
	
	public void random() {
		dao.random();
	}
	

}

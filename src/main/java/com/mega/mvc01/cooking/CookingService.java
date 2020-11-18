package com.mega.mvc01.cooking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Service
public class CookingService implements CookingServiceInterface {

	@Autowired
	CookingDAOInterface dao;

	
	@Autowired
	Algorithm al; // 싱글톤 객체로 생성하고 있기 떄문에, new (프로토타입) 선언이 아니라 싱글톤으로 선언을 해줘야 한다.
	
	@Override
	public List<VideoVO> select_main(String userId, int page_index) throws Exception {
		if (!userId.equals("null") && page_index == 0 ) {
			return al.goAlgori(userId, page_index); // 홈에서 알고리즘을 적용해야하기 때문에 알고리즘 클래스를 호출
		} else {
			return dao.select_main(userId, page_index);
		}
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
	public void insertUserRecord(UserRecordVO vo) { // 선택한 동영상의 조회기록을 update
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
	
	// 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독
	// 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독
	@Override
	public int startSubscribe(SubscribeVO vo) {
		System.out.println("서비스: " + vo.getUser_id() + vo.getChannel_id());
		int a = dao.selectSubscribe(vo); // 구독을 했는지 데이터가 있는지를 판단하기 위한 구문
		if (a == 0) { // 컬럼의 개수가 0이면 항목이 없다는 소리
			dao.insertSubscribe(vo); // 구독을 판단할 데이터 생성
		}
		return dao.selectSubscribe2(vo); // 생성 후에 그 데이터의 값을 반환 boolean의 값이기 때문에 0,1로 표기 됨
	}

	@Override
	public int updateSubscibe(SubscribeVO vo) {
		int a = dao.selectSubscribe2(vo); // 해당 데이터의 구독 값이 참인지 거짓인지 판단하고 업뎃
		if (a == 0) {
			vo.setSubscribe(true);
			dao.updateSubscribe(vo);
		} else {
			vo.setSubscribe(false);
			dao.updateSubscribe(vo);
		}
		return dao.selectSubscribe2(vo); // 업뎃이후의 값을 반환
	}

	// 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기
	@Override
	public void error(String userId) {
		System.out.println("service --------------");
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String now2 = format.format(now); //현재 날짜와 사용자의 아이디를 검색하여, 시청기록에 같은 비디오의 값이 있는지를 확인
		UserRecordVO vo = new UserRecordVO(); // 넘겨줄 값을 저장
		vo.setUser_id(userId);
		vo.setRecord_date(now2);
		List<Integer> list = dao.errorVideoIdList(vo); // 사용자가 같은 영상을 몇번 보았는지 판단하여 가져옴
		int[] a = new int[list.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = list.get(i).intValue(); // 그 값중에 5회이상의 영상이 있으면 강제로 오류를 발생하여 aop를 실행
			if (a[i] >= 5) {
				System.out.println(3/0);
				break;
			}
		}
	}
}

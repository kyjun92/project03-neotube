package com.mega.mvc01.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.DAOInterface;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Repository
public class GameDAO implements DAOInterface {

	@Autowired
	SqlSessionTemplate mybatis;

	@Override
	public List<VideoVO> select_main() {
		List<VideoVO> list = mybatis.selectList("game.game_list");

		String a = new String();
		return list;
	}

	@Override
	public VideoVO playingVideo(String videoId) { // 재생한 동영상의 정보를 select
		VideoVO vo2 = mybatis.selectOne("game.playingVideo", videoId);
		return vo2;
	}

	@Override
	public ChannelVO selectChannel(String channelId) { // 재생한 동영상의 채널정보를 select
		ChannelVO vo2 = mybatis.selectOne("game.selectChannel", channelId);

		return vo2;
	}

	@Override
	public void updatePlaynum(String videoId) { // 재생한 동영상의 조회수 +1
		mybatis.update("game.updatePlaynum", videoId);
	}

	@Override
	public void insertUserRecord(UserRecordVO vo) { // 재생한 동영상의 id를 user_record에 insert
		mybatis.insert("game.insertUserRecord", vo);
	}

	@Override
	public int selectLike(String userId, String videoId) { // user_like 에서 like 의 정보를 가져옴
		UserLikeVO vo1 = new UserLikeVO();
		vo1.setUser_id(userId);
		vo1.setVideo_id(videoId);
		UserLikeVO vo = mybatis.selectOne("game.selectLike", vo1);
		if (vo == null) { // user_like 에 기록이 없을 경우, 새로 insert하고 like = 0; 을 반환 (= 선택한 동영상의 재생페이지에 처음 접속한
							// 경우)
			mybatis.insert("game.insertUserLike", vo1);
			int like = 0;
			return like;
		}
		int like = vo.getLike();
		return like;
	}

	@Override
	public void updateLike(UserLikeVO vo) { // 변경된 좋아요/싫어요 정보 수정
		mybatis.update("game.updateLike", vo);
	}

	@Override
	public void updateLikeNum(String[] l) { // 경우의 수에 따라 like_num/dislike_num의 숫자를 계산
		//origin과 like 가 같은 경우는 배재 => JS if문으로 거름
		if (l[2].equals("0")) {	// origin = 0 / 좋아요 싫어요 둘 다 꺼져있었을 때
			if (l[1].equals("1")) { // 좋아요가 켜진 상태로 변경
				l[1] = "+ 1";
				l[2] = "+ 0";
			} else {				// 싫어요가 켜진 상태로 변경
				l[1] = "+ 0";
				l[2] = "+ 1";
			}
		} else if (l[2].equals("1")) {	// origin = 1 / 좋아요가 켜져 있었을 때
			if (l[1].equals("0")) { // 둘다 꺼진 상태로 변경
				l[1] = "- 1";
				l[2] = "+ 0";
			} else {				// 싫어요가 켜진 상태로 변경
				l[1] = "- 1";
				l[2] = "+ 1";
			}

		} else {	// origin = 2 / 싫어요가 켜져 있었을 때
			if (l[1].equals("1")) { // 좋아요가 켜진 상태로 변경
				l[1] = "+ 1";
				l[2] = "- 1";
			} else {				// 둘다 꺼진 상태로 변경
				l[1] = "+ 0";
				l[2] = "- 1";
			}

		}
		Map<String, String> map = new HashMap<>(); // 위 배열 내용들을 해시맵으로 맵핑하여 넘김
		map.put("videoId", l[0]);
		map.put("like", l[1]);
		map.put("dislike", l[2]);
		mybatis.update("game.updateLikenum", map);
	}
	
	// 구독을 누르기 전에 해당 유저가 채널의 구독 여부를 반별해야하는데 db에 목록이 없기 때문에
		// 먼저 유저의 채널 구독 여부를 db에 저장하고 시작하기 위해
		public void insertSubscribe(SubscribeVO vo) {
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

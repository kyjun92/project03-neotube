package com.mega.mvc01.cooking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.UserVO;
import com.mega.mvc01.VideoVO;

@Repository
public class CookingDAO implements CookingDAOInterface {

	@Autowired
	SqlSessionTemplate mybatis;

	// 메인 페이지에서 비디오 항목에 따른 전체 목록을 불러오기 위해
	@Override
	public List<VideoVO> select_main(String userId, int page_index) {
		
		List<VideoVO> list = null;
		if(userId.equals("null")) {
			list = mybatis.selectList("cooking.cooking_list");
		}else {
			if(page_index == 0) {
				list = mybatis.selectList("cooking.selectAlgoData"); // 알고리즘 계산을 위해 쓰이는 비디오의 정보를 불러오기 위해
//				System.out.println("test===============" + list);
			}else if(page_index == 1) {
				list = mybatis.selectList("cooking.popular_list");
			}else if(page_index == 2) {
				list = mybatis.selectList("cooking.subscribe_list", userId);
			}else if(page_index == 3) {
				list = mybatis.selectList("cooking.likeVideo_list", userId);
			}else if(page_index == 4) {
				list = mybatis.selectList("cooking.userRecordVideo_list");
			}
		}
//		System.out.println(list.get(0).getVideo_id());
		return list;
	}

	// 재생할 동영상을 선택하고 해당 동영상의 정보를 불러오기 위해
	@Override
	public VideoVO playingVideo(String videoId) {
		VideoVO vo2 = mybatis.selectOne("cooking.playingVideo", videoId);
		return vo2;
	}

	// 재생화면으로 들어갔을 때, 채널의 정보를 불러오기 위해
	@Override
	public ChannelVO selectChannel(String channelId) {
		ChannelVO vo2 = mybatis.selectOne("cooking.selectChannel", channelId);

		return vo2;
	}

	// 조회수 업데이트를 위해
	@Override
	public void updatePlaynum(String videoId) {
		mybatis.update("cooking.updatePlaynum", videoId);
	}

	// 해당 유저의 시청기록을 db에 등록하기 위해
	@Override
	public void insertUserRecord(UserRecordVO vo) {
		mybatis.insert("cooking.insertUserRecord", vo);
	}

	@Override
	public int selectLike(String userId, String videoId) { // user_like 에서 like 의 정보를 가져옴
		UserLikeVO vo1 = new UserLikeVO();
		vo1.setUser_id(userId);
		vo1.setVideo_id(videoId);
		UserLikeVO vo = mybatis.selectOne("cooking.selectLike", vo1);
		if (vo == null) { // user_like 에 기록이 없을 경우, 새로 insert하고 like = 0; 을 반환 (= 선택한 동영상의 재생페이지에 처음 접속한
							// 경우)
			mybatis.insert("cooking.insertUserLike", vo1);
			int like = 0;
			return like;
		}
		int like = vo.getLike_index();
		return like;
	}

	@Override
	public void updateLike(UserLikeVO vo) { // 변경된 좋아요/싫어요 정보 수정
		mybatis.update("cooking.updateLike", vo);
	}

	@Override
	public void updateLikeNum(String[] l) { // 경우의 수에 따라 like_num/dislike_num의 숫자를 계산
		// origin과 like 가 같은 경우는 배재 => JS if문으로 거름
		if (l[2].equals("0")) { // origin = 0 / 좋아요 싫어요 둘 다 꺼져있었을 때
			if (l[1].equals("1")) { // 좋아요가 켜진 상태로 변경
				l[1] = "+ 1";
				l[2] = "+ 0";
			} else { // 싫어요가 켜진 상태로 변경
				l[1] = "+ 0";
				l[2] = "+ 1";
			}
		} else if (l[2].equals("1")) { // origin = 1 / 좋아요가 켜져 있었을 때
			if (l[1].equals("0")) { // 둘다 꺼진 상태로 변경
				l[1] = "- 1";
				l[2] = "+ 0";
			} else { // 싫어요가 켜진 상태로 변경
				l[1] = "- 1";
				l[2] = "+ 1";
			}

		} else { // origin = 2 / 싫어요가 켜져 있었을 때
			if (l[1].equals("1")) { // 좋아요가 켜진 상태로 변경
				l[1] = "+ 1";
				l[2] = "- 1";
			} else { // 둘다 꺼진 상태로 변경
				l[1] = "+ 0";
				l[2] = "- 1";
			}
		}
		Map<String, String> map = new HashMap<>(); // 위 배열 내용들을 해시맵으로 맵핑하여 넘김
		map.put("videoId", l[0]);
		map.put("like", l[1]);
		map.put("dislike", l[2]);
		mybatis.update("cooking.updateLikenum", map);
	}

	// 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독 구독

	// 구독을 누르기 전에 해당 유저가 채널의 구독 여부를 반별해야하는데 db에 목록이 없기 때문에
	// 먼저 유저의 채널 구독 여부를 db에 저장하고 시작하기 위해
	@Override
	public void insertSubscribe(SubscribeVO vo) {
		System.out.println("dao: " + vo.getUser_id() + vo.getChannel_id());
		mybatis.insert("cooking.insertSubscribe", vo);
	}

	// 구독을 했는지의 여부를 판단하고 값을 반환받기 위해
	// 해당 목록의 숫자를 받아와서 판단
	@Override
	public int selectSubscribe(SubscribeVO vo) {
		return mybatis.selectOne("cooking.selectSubscribe", vo);
	}

	// 구독을 했는지의 여부를 판단하고 값을 반환받기 위해
	// 구독의 값을 받아서 판단
	@Override
	public int selectSubscribe2(SubscribeVO vo) {
		return mybatis.selectOne("cooking.selectSubscribe2", vo);
	}

	// 구독 버튼을 누르면 정보를 업데이트 시켜 주기 위해
	@Override
	public void updateSubscribe(SubscribeVO vo) {
		mybatis.update("cooking.updateSubscribe", vo);
	}

	// 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘 알고리즘

	// 알고리즘 계산을 위해 쓰이는 비디오의 정보를 불러오기 위해
//	public List<VideoVO> selectAlgoData() {
//		return mybatis.selectList("cooking.selectAlgoData");
//	}

	// 해당 사용자의 시청기록의 개수가 몇개인지를 알기위해
	@Override
	public int selectCountRecord(String userId) {
		return mybatis.selectOne("cooking.selectCountRecord", userId);
	}

	// 비디오의 채널 목록을 중복을 제거하고 가져오는 가져오기 위해
	@Override
	public List<SubscribeVO> selectCookingChannel() {
		return mybatis.selectList("cooking.selectCookingChannel");
	}

	// 사용자가 시청한 채널과 그 횟수를 가져오기 위해
	@Override
	public List<SubscribeVO> selectRecordChannel(String userId) {
		return mybatis.selectList("cooking.selectRecordChannel", userId);
	}

	@Override
	public List<Integer> selectRecordChannel2(String userId) {
		return mybatis.selectList("cooking.selectRecordChannel2", userId);
	}

	// 사용자의 나이를 가져오기 위해
	@Override
	public String selectOneAge(String userId) {
		return mybatis.selectOne("cooking.selectOneAge", userId);
	}

	// 전체 사용자의 나이를 가져오기 위해
	@Override
	public List<UserVO> selectAllAge() {
		return mybatis.selectList("cooking.selectAllAge");
	}

	// 마지막 정렬된 아이디로 동영상의 정보를 가져오기 위해서
	@Override
	public List<VideoVO> selectFinalVideo(String videoId) {
		return mybatis.selectList("cooking.selectFinalVideo", videoId);
	}

	// 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기
	// 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기

	// 유저의 아이디와 현재 시간(yyyy-MM-dd) 값으로 시청기록 테이블을 찾아서 같은 비디오의 값이 5개 이상이 되는지 체크하기 위해
	@Override
	public List<Integer> errorVideoIdList(UserRecordVO vo) {
		return mybatis.selectList("cooking.errorVideoIdList", vo);
	}
}

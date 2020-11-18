package com.mega.mvc01.cooking;

import java.util.List;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.UserVO;
import com.mega.mvc01.VideoVO;

public interface CookingDAOInterface {

	// 메인 페이지에서 비디오 항목에 따른 전체 목록을 불러오기 위해
	List<VideoVO> select_main(String userId, int page_index);

	// 재생할 동영상을 선택하고 해당 동영상의 정보를 불러오기 위해
	VideoVO playingVideo(String videoId);

	// 재생화면으로 들어갔을 때, 채널의 정보를 불러오기 위해
	ChannelVO selectChannel(String channelId);

	// 조회수 업데이트를 위해
	void updatePlaynum(String videoId);

	// 해당 유저의 시청기록을 db에 등록하기 위해
	void insertUserRecord(UserRecordVO vo);

	int selectLike(String userId, String videoId);

	void updateLike(UserLikeVO vo);

	void updateLikeNum(String[] l);

	// 구독을 누르기 전에 해당 유저가 채널의 구독 여부를 반별해야하는데 db에 목록이 없기 때문에
	// 먼저 유저의 채널 구독 여부를 db에 저장하고 시작하기 위해
	void insertSubscribe(SubscribeVO vo);

	// 구독을 했는지의 여부를 판단하고 값을 반환받기 위해
	// 해당 목록의 숫자를 받아와서 판단
	int selectSubscribe(SubscribeVO vo);

	// 구독을 했는지의 여부를 판단하고 값을 반환받기 위해
	// 구독의 값을 받아서 판단
	int selectSubscribe2(SubscribeVO vo);

	// 구독 버튼을 누르면 정보를 업데이트 시켜 주기 위해
	void updateSubscribe(SubscribeVO vo);

	// 해당 사용자의 시청기록의 개수가 몇개인지를 알기위해
	int selectCountRecord(String userId);

	// 비디오의 채널 목록을 중복을 제거하고 가져오는 가져오기 위해
	List<SubscribeVO> selectCookingChannel();

	// 사용자가 시청한 채널과 그 횟수를 가져오기 위해
	List<SubscribeVO> selectRecordChannel(String userId);

	List<Integer> selectRecordChannel2(String userId);

	// 사용자의 나이를 가져오기 위해
	String selectOneAge(String userId);

	// 전체 사용자의 나이를 가져오기 위해
	List<UserVO> selectAllAge();

	// 마지막 정렬된 아이디로 동영상의 정보를 가져오기 위해서
	List<VideoVO> selectFinalVideo(String videoId);

	// 유저의 아이디와 현재 시간(yyyy-MM-dd) 값으로 시청기록 테이블을 찾아서 같은 비디오의 값이 5개 이상이 되는지 체크하기 위해
	List<Integer> errorVideoIdList(UserRecordVO vo);

}
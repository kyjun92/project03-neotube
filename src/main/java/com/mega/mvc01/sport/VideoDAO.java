package com.mega.mvc01.sport;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.JoinVO;
import com.mega.mvc01.JoinVideoUserlikeVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserRecordVO;

@Repository
public class VideoDAO implements DAOinterface {

	@Autowired
	SqlSessionTemplate mybatis;
	
	@Override
	public void insert() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void one() {
		
	}

	@Override
	public List<VideoVO> list() {
		List<VideoVO> list	= mybatis.selectList("video.select");
		return list;
	}

	//인기
	public List<JoinVO> popular() {
		List<JoinVO> list = mybatis.selectList("video.popular");
		return list;
	}
	
	//홈
	public List<JoinVO> join() {
		List<JoinVO> list= mybatis.selectList("video.join");
		return list;
	}
	
	public List<VideoVO> list(String video_id) {
		 List<VideoVO> list= mybatis.selectList("video.list", video_id);
			/* System.out.println(list); */
		 return list;
	}
	
	public List<ChannelVO> list2(String channel_id) {
		 List<ChannelVO> list= mybatis.selectList("video.list2", channel_id);
		 return list;
	}

	//좋아요 insert
//	public void likeInsert(UserlikeVO vo) {
//		 mybatis.insert("video.likeinsert", vo);
//	}
	
	//조인테이블 list select
	public List<JoinVideoUserlikeVO> likepage() {
		List<JoinVideoUserlikeVO> list = mybatis.selectList("video.likeVideo");
		return list;
	}
	
	//record insert
	public void user_recordInsert(UserRecordVO vo) {
		 mybatis.insert("video.user_recordInsert", vo);
	}
	
	//record_view list select
	public List<VideoVO>  record_view() {
		List<VideoVO> list= mybatis.selectList("video.recordView");
			return list;
	}
	
	//구독 list insert
	public void subscribeInsert(SubscribeVO vo) {
		 mybatis.insert("video.insertSubscribe", vo);
	}
	
	//구독 list select
	public List<VideoVO>  subscribeList(String user_id){
		List<VideoVO> list= mybatis.selectList("video.subscribeList", user_id);
		System.out.println(user_id);
		System.out.println(list);
		return list;
	}
	
	public void viewnumUpdate(String video_id) {
		mybatis.update("video.updatePlaynum", video_id);
	}
	
	//구독 리스트 뽑아내고 subscribe 값만 쓸예정
	public SubscribeVO subscribeBoolean(SubscribeVO vo) {
		SubscribeVO vo2 = mybatis.selectOne("video.subscribeBoolean", vo);
		return vo2;
	}
	
	
	// 구독을 누르기 전에 해당 유저가 채널의 구독 여부를 반별해야하는데 db에 목록이 없기 때문에
	// 먼저 유저의 채널 구독 여부를 db에 저장하고 시작하기 위해
	public void insertSubscribe(SubscribeVO vo) {
		System.out.println("dao: " + vo.getUser_id()+" "+vo.getChannel_id());
		mybatis.insert("video.insertSubscribe", vo);
	}
	
	// 구독을 했는지의 여부를 판단하고 값을 반환받기 위해
	// 해당 목록의 숫자를 받아와서 판단
	public int selectSubscribe(SubscribeVO vo) {
		return mybatis.selectOne("video.selectSubscribe", vo);
	}

	// 구독을 했는지의 여부를 판단하고 값을 반환받기 위해
	// 구독의 값을 받아서 판단
	public int selectSubscribe2(SubscribeVO vo) {
		return mybatis.selectOne("video.selectSubscribe2", vo);
	}
	
	// 구독 버튼을 누르면 정보를 업데이트 시켜 주기 위해
	public void updateSubscribe0(SubscribeVO vo) {
		mybatis.update("video.updateSubscribe0", vo);
	}
	
	// 구독 버튼을 누르면 정보를 업데이트 시켜 주기 위해
	public void updateSubscribe1(SubscribeVO vo) {
		mybatis.update("video.updateSubscribe1", vo);
	}
	
	
	
	//합치기
	@Override
	public void delete() {
		// TODO Auto-generated method stub
	}

	
	
}

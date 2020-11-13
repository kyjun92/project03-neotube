package com.mega.mvc01.sport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.mvc01.JoinVO;
import com.mega.mvc01.JoinVideoUserlikeVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserRecordVO;

@Service
public class VideoService {

	@Autowired
	VideoDAO dao;
	
	
	public void insert() {
		
	}

	public void update() {

	}

	public void one() {
		
	}
	public List<VideoVO> list() {
		return dao.list();
	}
	public List<VideoVO> list(String video_id) {
		return dao.list(video_id);
	}
	public List<ChannelVO> list2(String channel_id) {
		return dao.list2(channel_id);
	}
	public List<JoinVO> join() {
		return dao.join();
	}
	public List<JoinVO> popular() {
		return dao.popular();
	}
	
	public void delete() {
	}
	
	public List<VideoVO> algorithm() {
		System.out.println("서비스 알고리즘 호출" +dao.list());
		return dao.list();
	}
//	public void likeinsert(UserlikeVO vo) {
//		dao.likeInsert(vo);
//	}
	
	public List<JoinVideoUserlikeVO> likepage() {
		return dao.likepage();
	}
	
	public void user_recordInsert(UserRecordVO vo) {
		dao.user_recordInsert(vo);
	}
	
	public void SubscribeInsert(SubscribeVO vo) {
		dao.subscribeInsert(vo);
	}
	
	public List<VideoVO> record_view(){
		return dao.record_view();
	}
	
	public List<VideoVO> subscribeList(String user_id) {
		return dao.subscribeList(user_id);
	}
	
	public void viewnumUpdate(String video_id) {
		dao.viewnumUpdate(video_id);
	}
	
	public SubscribeVO subscribeBoolean(SubscribeVO vo) {
		return dao.subscribeBoolean(vo);
	}
	public int selectSubscribe2(SubscribeVO vo) {
		return dao.selectSubscribe2(vo);
	}
	
	public int startSubscribe(SubscribeVO vo) {
		int a = dao.selectSubscribe(vo);
					System.out.println("83번째줄" + a);
		if(a==0) {// 값이 없을때
			dao.insertSubscribe(vo);
		}else { //값이 있을때
			int b = dao.selectSubscribe2(vo); // subscribe 가 값이 있을때
			System.out.println("88번째줄" + b);
			if(b==0) {
				dao.updateSubscribe1(vo);// subscribe 1 
			}else {
				dao.updateSubscribe0(vo); // subscribe 0
			}
		}
		return dao.selectSubscribe2(vo); 
	}
}

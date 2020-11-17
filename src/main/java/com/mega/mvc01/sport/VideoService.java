package com.mega.mvc01.sport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.mvc01.JoinVO;
import com.mega.mvc01.JoinVideoUserlikeVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
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
	
	
	public int like(UserLikeVO vo) {
		int likeIndex = dao.selectLikeindex(vo);
		System.out.println("service단 에서 like index값"+likeIndex);
		dao.updateLike1(vo);
		
		likeIndex = dao.selectLikeindex(vo);
		System.out.println("service단 에서 like index값 변경값"+likeIndex);		
		return likeIndex;
		}
	
	
	public int dislike(UserLikeVO vo) {
		int a = dao.selectLike(vo);
		int b = dao.selectLikeindex(vo);
		System.out.println("114번째줄>>>b"+ b);
		if(a==0) { // 테이블이 없을때
			dao.insertUserLike(vo); // 값 넣기 like_index 0 대입
			b = 0;
			System.out.println("118번째줄>>>b"+ b);
			return b; 
		}else {// 테이블이 있을때
				dao.updateLike2(vo);
				b = dao.selectLikeindex(vo);
				System.out.println("122번째줄>>>b"+ b);
				return b;
			}
	}
	
	public int selectLikeindex(UserLikeVO vo) {
		return dao.selectLikeindex(vo);
	}
	
	public void insertUserLike(UserLikeVO vo) {
		dao.insertUserLike(vo);
	}
	
	
	public List<VideoVO> recommandAlgorithm() throws Exception {

		List<VideoVO> list = new ArrayList<>();
		VideoVO	 vo = new VideoVO();
		list = dao.list(); // ArrayList에 DB값 저장
		
		Random r = new Random();
		double s_y;
		double s_x;
		double s_f;
		int[][] recommandArray= new int[100][2];
		
		for (int i = 0; i < list.size(); i++) {
			vo=list.get(i);
			recommandArray[i][0] = i;
			
			s_y = vo.getPlay_num()/10000;
			System.out.print("s_y 값"+s_y+" ");
			
			s_x = vo.getLike_num()/100;
			System.out.print("s_x 값"+s_x+" ");
			
			s_f = Math.pow((s_x*s_x)+(s_y*s_y),0.5);// x의 y제곱을 반환
			
			recommandArray[i][1] = Math.round((float)(s_f) + r.nextFloat()*45); //math.round : 입력값을 반올림한 값과 가장 가까운 정수를 의미합니다.
			System.out.println(i+"번째 "+recommandArray[i][1]+", ");
		}
		
		QuickSort(recommandArray,0,98);
		
		int[] s_id = new int[100];
		
		
		for (int i = 0; i < 99; i++) {
			s_id[i] = recommandArray[98-i][0];
		}
		
		for (int i = 0; i < 99; i++) {
			System.out.println(i+"배열 s_id["+i+"]>>>>>"+s_id[i]);
		}
		
		int idx1 ;
		
		List<VideoVO> rcList = new ArrayList<>();
		for (int i = 10; i < 50; i++)// 배열
		{	
			idx1 = s_id[i];
			System.out.println("인덱스"+idx1);
			vo=list.get(idx1);
			rcList.add(list.get(idx1));
		}
		return rcList;
	}
	
	static void swap(int[][] a, int idx1, int idx2) {
		int[] t = {a[idx1][0],a[idx1][1]}; 
		a[idx1][0] = a[idx2][0];
		a[idx1][1] = a[idx2][1];
		a[idx2][0] = t[0];
		a[idx2][1] = t[1];
	}
	
	static void QuickSort(int[][] a, int l, int r) {
		int pl = l;
		int pr = r;
		int x = a[(pl + pr) / 2][1];
		
		do {
			while(a[pl][1] < x) pl++;
			while(a[pr][1] > x) pr--;
			if(pl <= pr) swap(a, pl++, pr--);
		}while(pl <= pr);
		
		if(l < pr) QuickSort(a, l, pr);
		if(pl < r) QuickSort(a, pl, r);
}
	
}

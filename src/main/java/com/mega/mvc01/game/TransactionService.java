package com.mega.mvc01.game;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mega.mvc01.UserRecordVO;

@Service
public class TransactionService {

	@Autowired
	GameDAO dao;
	
	public String user_id_tran;			
	public String video_id_tran;
	public int count;
	
	@Transactional
	public int tran(UserRecordVO vo) throws Exception {
		int result = 1;								// 성공 / 실패 결과 변수 대입
		System.out.println(user_id_tran + " :: " + video_id_tran);
		
		dao.updatePlaynum(vo.getVideo_id());		// 조회수 +1 dao
		if(vo.getUser_id().equals(user_id_tran) && vo.getVideo_id().equals(video_id_tran)) {	
			count++;		// user_id와 video_id가 연속으로 같은 값이 들어온다면 count ++
		}else {
			count = 0;		// 그 외의 경우는 모두 count = 0
		}					
		user_id_tran = vo.getUser_id();			//새로 들어온 vo값을 static 전역 변수에 저장
		video_id_tran = vo.getVideo_id();
		if(count < 6) {							//count가 5이하일 경우 commit 진행
			dao.insertUserRecord(vo);
			System.out.println(count);
		}else {									//count가 5를 넘어가면 오류를 일으켜 트랜잭션 => rollback 실행
			result = 0;
			System.out.println(3/0);
		}
		return result;
	}
	

	
	
//	@Transactional
//	public int tran(BbsVO2 bbsVO2, MemberVO memberVO) throws Exception {
//		System.out.println("호출되긴 한단다!");
//		int result = 1;
//		dao.insert(memberVO);
//		if(0 == dao2.insert(bbsVO2)) {
//			System.out.println("에러발생!");
//			result = 0;
//		}
//		return result;
//	}
}

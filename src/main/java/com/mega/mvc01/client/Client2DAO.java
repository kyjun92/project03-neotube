package com.mega.mvc01.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.Client2VO;
import com.mega.mvc01.ReplyVO;
import com.mega.mvc01.UserVO;


@Repository
public class Client2DAO {
	@Autowired
	SqlSessionTemplate mybatis;
	
	// 글쓰기
	public void insert(Client2VO client2vo) {
		Date now = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now1 = fm.format(now);
		client2vo.setDate(now1);
		mybatis.insert("Client2.insert", client2vo); 
         
		
	} 
	// 글삭제
	public void delete(Client2VO client2vo) {
		mybatis.delete("Client2.delete", client2vo);

	}
	//게시판 댓글삭제 (댓글아이디번호)
	public int deleteReply(Client2VO client2vo) {
		int a = mybatis.delete("Client2.deleteReply", client2vo);
		System.out.println("해당 유저아이디 댓글 삭제"+a);
		return a;
	}
	public int boardsize() {
		int size = mybatis.selectOne("Client2.countAll");
		System.out.println(size);
		return mybatis.selectOne("Client2.countAll");
	}
	
	// 글수정
	public void update(Client2VO client2vo) {
		mybatis.update("Client2.update", client2vo);

	}
	
	public Client2VO select(Client2VO vo) {
		Client2VO vo2 = mybatis.selectOne("Client2.one", vo);
		System.out.println("�˻����1: "+ vo2);
		return vo2;
	}
	public List<Client2VO> list(Client2VO vo) {
		List<Client2VO> list = mybatis.selectList("bbs.list", vo);
		return list;
	}
	
	// 회원삭제
	public void taltoe(UserVO loginvo) {
		mybatis.delete("user.delete", loginvo);
	}

	// 댓글삭제(유저아이디번호)
		public int taltoeReply(UserVO loginvo) {
			int a = mybatis.delete("user.deleteReply", loginvo);
			System.out.println("해당 유저아이디 댓글 삭제"+a);
			return a;
		}
		public int taltoeBorder(UserVO loginvo) {
			int a = mybatis.delete("user.deleteBorder", loginvo);
			System.out.println("해당 유저아이디 게시판 삭제"+a);
			return a;
		}
	

		// 회원정보
		public UserVO Information(UserVO uservo) {
		UserVO vo= mybatis.selectOne("user.profile", uservo);
	             return vo;
		}
		// 회원수정
		public int Modify(UserVO loginvo) {
		int result = mybatis.update("user.update", loginvo);
		System.out.println(mybatis.update("user.update", loginvo)+"lllllllllllllllllllllllllll");
		return result;
		}
		
		public List<Client2VO> borderList(Client2VO client2VO) {
			List<Client2VO> list = mybatis.selectList("Client2.bbsList", client2VO);
			System.out.println(list);
			return list;
		}
		
		public List<Client2VO> findAll(Map<String,Object> param){
			
			
			return mybatis.selectList("Client2.findAll",param);
		}

}

package com.mega.mvc01.client;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.mvc01.Client2VO;
import com.mega.mvc01.ReplyVO;
import com.mega.mvc01.UserVO;


@Service
public class Client2Service {

	@Autowired
	Client2DAO dao;
	//글쓰기
	public void insert(Client2VO clien2tvo) {
             dao.insert(clien2tvo);
	}
	//글삭제
	public void delete(Client2VO client2vo) {
             dao.delete(client2vo);
	}
	//댓글 삭제(글 번호)
	public int deleteReply(Client2VO client2vo) {
		return	dao.deleteReply(client2vo);
	}
	
	//글수정
	public void update(Client2VO client2vo) {
             dao.update(client2vo);
	}
	//게시판 글보기
	public Client2VO one (Client2VO vo) {
		return dao.select(vo); 
	}
	
	//회원 삭제
	public void taltoe(UserVO loginvo) {
		 dao.taltoe(loginvo);
	}
	//댓글 삭제(회원 번호)
	public int taltoeReply(UserVO loginvo) {
		return	dao.taltoeReply(loginvo);
	}
	//해당 아이디 게시판 삭제(회원 번호)
	public int taltoeBorder(UserVO loginvo) {
		return dao.taltoeBorder(loginvo);
	}
	//게시판 전체 갯수
	public int boardsize() {
		return dao.boardsize();
	}
	
	//회원정보
	public UserVO Information(UserVO uservo) {
           return dao.Information(uservo);
	}
	
	//회원정보수정
	public int Modify(UserVO uservo) {
		return dao.Modify(uservo);
	}

	public List<Client2VO> borderList(Client2VO client2VO){
		return dao.borderList(client2VO);
	}
	
	public List<Client2VO>list(int pageNo,int pageSize){
		HashMap<String,Object> param = new HashMap<>();
		param.put("offset", (pageNo-1)*pageSize);
		param.put("pageSize", pageSize);
		
		List<Client2VO> boardList = dao.findAll(param);
		
		return boardList;
	}

}

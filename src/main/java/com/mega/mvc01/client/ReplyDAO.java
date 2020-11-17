package com.mega.mvc01.client;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.ReplyVO;

@Repository
public class ReplyDAO {

	@Autowired
	SqlSessionTemplate mybatis;

	//카운터 글숫자확인
	public int create(ReplyVO replyvo) {

	int	result= mybatis.selectOne("Reply.ho", replyvo);
	
		return result;
	

	}
	//댓글쓰기
	public int create2(ReplyVO replyvo) {
	
		
		int result=	mybatis.insert("Reply.insert", replyvo); 
		return result;
	

	}
 
	// 글삭제
	public int delete(ReplyVO replyvo) {
	int result = mybatis.delete("Reply.delete", replyvo);
             return result;
	}
	//댓글 보기
	public List<ReplyVO> list(int border_id ) {
		 List<ReplyVO> list= mybatis.selectList("Reply.list", border_id);
		
		 System.out.println(list);
		 return list;
	}
	public int next(ReplyVO replyvo) {
	int result=	mybatis.selectOne("Reply.next", replyvo);
	        
                     return result;		
	}
}

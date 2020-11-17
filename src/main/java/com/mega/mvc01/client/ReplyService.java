package com.mega.mvc01.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.mvc01.ReplyVO;



@Service
public class ReplyService {

	@Autowired
	ReplyDAO dao;
	//글쓰기
		public int create(ReplyVO replyvo) {

			
			System.out.println("===============");
	        System.out.println(replyvo);
	        System.out.println("===============");
	        System.out.println("===============");
	        System.out.println("===============");

	        int result =  dao.create(replyvo)+ 1 ; 
	        String id = "border"+ result;  
	        
	        replyvo.setReply_id(id);
	        
	        System.out.println("===============");
	        System.out.println(replyvo);
	        System.out.println("===============");
	        System.out.println("==============="); 
	        System.out.println("===============");
	        System.out.println("===============");
	        
			
	         int result2	= dao.create2(replyvo);	
	         
		        
		        System.out.println("===============");
		        System.out.println(replyvo);
		        System.out.println("===============");
		        System.out.println("===============");
		        System.out.println("===============");
		        System.out.println("===============");
	         
	         return result2;
		}
		//글삭제
		public int delete(ReplyVO replyvo) {
	           return  dao.delete(replyvo);
		}
		//댓글보기
		public java.util.List<ReplyVO> list(int border_id) {
			 return dao.list(border_id);
		}
	    public int next(ReplyVO replyvo) {
	    return 	dao.next(replyvo);
	    }
}

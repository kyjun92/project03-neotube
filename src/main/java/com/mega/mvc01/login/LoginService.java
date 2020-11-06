package com.mega.mvc01.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.mvc01.UserVO;

@Service
public class LoginService {
     
	@Autowired
	LoginDAO dao;
	//회원가입
	public void insert(UserVO loginvo) {
             dao.insert(loginvo);
	}
	
	//아이디 중복확인
	public Boolean select(UserVO loginvo ) throws Exception {
	
		Boolean result = dao.select(loginvo);
		return result;
		
    }
	//로그인
	public int select1(UserVO loginvo) {
		 int result = dao.select1(loginvo);
		 return result;
	}
	//id찾기
	public UserVO select2(UserVO loginvo) {
		 return dao.select2(loginvo);
	}
	//id찾기 중복확인
	public int select3(UserVO loginvo) {
		 int result = dao.select3(loginvo);
		 return result;
	}
	//id찾기
	public UserVO select4(UserVO loginvo) {
		 return dao.select4(loginvo);
	}
	//pw찾기 중복확인
	public int select5(UserVO loginvo) {
		 int result = dao.select5(loginvo);
		 return result;
	}
	//pw찾기
	public UserVO select6(UserVO loginvo) {
		return  dao.select6(loginvo);
		 
	}
	
	
	
}

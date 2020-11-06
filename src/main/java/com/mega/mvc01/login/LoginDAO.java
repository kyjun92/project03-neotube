package com.mega.mvc01.login;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.UserVO;

@Repository
public class LoginDAO {

	@Autowired
	SqlSessionTemplate mybatis;

	// 회원가입
	public void insert(UserVO loginvo) {
		mybatis.insert("user.insert", loginvo);

	}

	// 아이디중복확인
	public Boolean select(UserVO loginvo) throws Exception {
		UserVO vo = null;
		vo = mybatis.selectOne("user.select", loginvo);
		Boolean result = true;

		if (vo != null) {
			if (vo.getUser_id().equals(loginvo.getUser_id())) {
				result = false;

			}
			System.out.println(vo.getUser_id() + " // " + loginvo.getUser_id());
			System.out.println(result);
		}
		return result;
	}

	// 로그인
	public int select1(UserVO loginvo) {
		UserVO vo = mybatis.selectOne("user.select1", loginvo);
		int result = 0;
		if (vo != null) {
			result = 1;

		}
		return result;
	}

	// id 찾기
	public UserVO select2(UserVO loginvo) {
		UserVO vo = mybatis.selectOne("user.select2", loginvo);
		return vo;
	}

	// id 찾기 중복확인
	public int select3(UserVO loginvo) {
		UserVO vo = mybatis.selectOne("user.select3", loginvo);
		int result = 0;

		if (vo != null) {
			result = 1;

		}
		return result;
	}

	// id 찾기
	public UserVO select4(UserVO loginvo) {
		UserVO vo = mybatis.selectOne("user.select4", loginvo);
		return vo;
	}

	// pw 찾기 중복확인
	public int select5(UserVO loginvo) {
		UserVO vo = mybatis.selectOne("user.select5", loginvo);
		int result = 0;
		if (vo != null) {
			result = 1;

		}
		return result;
	}

	// pw 찾기
	public UserVO select6(UserVO loginvo) {
		UserVO vo = mybatis.selectOne("user.select6", loginvo);
		return vo;
	}

}

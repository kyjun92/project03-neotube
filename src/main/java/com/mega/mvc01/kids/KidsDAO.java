package com.mega.mvc01.kids;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KidsDAO {
	
	@Autowired
	SqlSessionTemplate mybatis;

	public KidsVO one(KidsVO kidsVO) {
		return mybatis.selectOne("kidsone", kidsVO);
	}

	public List<KidsVO> listByCategory(SearcherVO vo) {
		List<KidsVO> result = mybatis.selectList("listByCategory", vo);
		return result;
	}
	
	public List<KidsVO> listByTracker(SearcherVO vo) {
		List<KidsVO> result = mybatis.selectList("listByCategoryNolimit", vo);
		return result;
	}
	
	public List<KidsVO> listByPopular(SearcherVO vo) {
		List<KidsVO> result = mybatis.selectList("listByPopular", vo);
		return result;
	}
	
	public List<KidsVO> listBySubscribe(SearcherVO vo) {
		List<KidsVO> result = mybatis.selectList("listBySubscribe", vo);
		return result;
	}
	
	public List<KidsVO> listByLike(SearcherVO vo) {
		List<KidsVO> result = mybatis.selectList("listByLike", vo);
		return result;
	}
	
	public List<KidsVO> listByHistory(SearcherVO vo) {
		List<KidsVO> result = mybatis.selectList("listByHistory", vo);
		return result;
	}
	
	public int addHistory(UserControlVO vo) {
		return mybatis.insert("addHistory",vo);
	}

	public Integer getLike(UserControlVO vo) {
		return mybatis.selectOne("getLike", vo);
	}
	
	public int likeCancel(UserControlVO vo) {
		return mybatis.delete("likeCancel", vo);
	}
	
	public int videoLikeUndo(UserControlVO vo) {
		return mybatis.update("videoLikeUndo", vo);
	}
	
	public int videoDislikeUndo(UserControlVO vo) {
		return mybatis.update("videoDislikeUndo", vo);
	}
	
	public int userLike(UserControlVO vo) {
		return mybatis.insert("userLike", vo);
	}
	
	public int userDislike(UserControlVO vo) {
		return mybatis.insert("userDislike", vo);
	}
	
	public int videoLike(UserControlVO vo) {
		return mybatis.update("videoLike", vo);
	}
	
	public int videoDislike(UserControlVO vo) {
		return mybatis.update("videoDislike", vo);
	}
	
	public Boolean getSubscribe(UserControlVO vo) {
		return mybatis.selectOne("getSubscribeOne",vo);
	}
	
	public int setSubscribe(UserControlVO vo) {
		return mybatis.insert("setSubscribe", vo);
	}

	public int doSubscribe(UserControlVO vo) {
		return mybatis.update("doSubscribe", vo);
	}

	public int undoSubscribe(UserControlVO vo) {
		return mybatis.update("undoSubscribe", vo);
	}

	public List<KidsReplyVO> getReply(KidsReplyVO vo) {
		List<KidsReplyVO> bag = mybatis.selectList("getReply",vo);
		return bag;
	}

	public int getReplyMaxID(ReplyMaxIDVO rmidvo) {
		int result = mybatis.selectOne("getReplyMaxId", rmidvo);
		return result;
	}

	public int postReply(KidsReplyVO vo) {
		return mybatis.insert("postReply",vo);
	}

	public String getCategory(KidsReplyVO vo) {
		return mybatis.selectOne("getCategory",vo);
	}
}

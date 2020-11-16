package com.mega.mvc01.kids;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KidsService {

	@Autowired
	KidsDAO dao;

	public KidsVO one(KidsVO kidsVO) {
		return dao.one(kidsVO);
	}

	public List<KidsVO> listByCategory(SearcherVO vo) {
		return dao.listByCategory(vo);
	}
	
	public List<KidsVO> listBySubscribe(SearcherVO vo) {
		return dao.listBySubscribe(vo);
	}
	
	public List<KidsVO> listByLike(SearcherVO vo) {
		return dao.listByLike(vo);
	}
	
	public List<KidsVO> listByHistory(SearcherVO vo) {
		return dao.listByHistory(vo);
	}

	public List<KidsVO> listBySearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public void upload(KidsVO vo) {
		// TODO Auto-generated method stub

	}
	
	public int addHistory(UserControlVO vo) {
		vo.setDate(new java.util.Date());
		return dao.addHistory(vo);
	}
	
	public Integer getLike(UserControlVO vo) {
		return dao.getLike(vo);
	}
	
	public String likeVideo(UserControlVO vo) {
		Integer like = dao.getLike(vo);	//검색을 못할 경우 받아오는 null을 처리하기 위한 wrapper class
		if (like != null) {
			if(like.intValue() == 1) {
				dao.likeCancel(vo);
				dao.videoLikeUndo(vo);
				return "undo";
			}
			else if(like.intValue() == 2) {
				dao.likeCancel(vo);
				dao.videoDislikeUndo(vo);
				dao.userLike(vo);
				dao.videoLike(vo);
				return "toggle";
			}
		}
		dao.userLike(vo);
		dao.videoLike(vo);
		return "do";
	}
	
	public String dislikeVideo(UserControlVO vo) {
		Integer like = dao.getLike(vo);
		if (like != null) {
			if(like.intValue() == 2) {
				dao.likeCancel(vo);
				dao.videoDislikeUndo(vo);
				return "undo";
			}
			else if(like.intValue() == 1) {
				dao.likeCancel(vo);
				dao.videoLikeUndo(vo);
				dao.userDislike(vo);
				dao.videoDislike(vo);
				return "toggle";
			}
		}
		dao.userDislike(vo);
		dao.videoDislike(vo);
		return "do";
	}
	
	public String subscribe(UserControlVO vo) {
		System.out.println(vo);
		Boolean did = dao.getSubscribe(vo);
		if (did == null) {
			dao.setSubscribe(vo);
			return "do";
		}
		else if (did.booleanValue()==false) {
			dao.doSubscribe(vo);
			return "do";
		}
		else{
			dao.undoSubscribe(vo);
			return "undo";
		}
	}

	public List<KidsReplyVO> getReply(KidsReplyVO vo) {
		return dao.getReply(vo);
	}

	public KidsReplyVO postReply(KidsReplyVO vo) {
		ReplyMaxIDVO rmidvo = new ReplyMaxIDVO();
		rmidvo.setReply_id(vo.getReply_id());
		String category = dao.getCategory(vo);
		rmidvo.setCategory(category);
		int replyMaxId = dao.getReplyMaxID(rmidvo);
		vo.setReply_id(category + "_" + (replyMaxId+1));
		vo.setDate(new java.util.Date());
		dao.postReply(vo);
		return vo;
	}


//	public List<KidsVO> nextVideoList() {
//		return dao.listByCategory(vo);
//	}
}

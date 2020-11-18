package com.mega.mvc01.kids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
	
	public List<KidsVO> listByTracker(SearcherVO vo) {
		return dao.listByTracker(vo);
	}
	
	public List<KidsVO> listByPopular(SearcherVO vo) {
		return dao.listByPopular(vo);
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

	public Map<String,Double> getPearsonList(String user_id) {
		//몽고DB에 접속
		MongoClient mongoClient = MongoClients.create("mongodb+srv://root:1234@neotubekids.e68cc.mongodb.net/neotube?retryWrites=true&w=majority");
		MongoDatabase database = mongoClient.getDatabase("neotube");
		MongoCollection<Document> collection = database.getCollection("neotube");
		
		//리스트 받아와서 나의 트래커 정보와 '남들은 봤는데 내가 아직 안 본' 영상 셋 추출
		//내 트래커 정보가 없을 경우 실행하지 않고 그냥 종료
		Document myDoc = null;
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		Set<String> columns = new HashSet<String>();
		for(Document doc: foundDocument) {
			columns.addAll(doc.keySet());
			if(doc.containsValue(user_id)) myDoc = doc;
		}
		if(myDoc == null) return null;
		columns.remove("user_id");
		columns.remove("_id");
		columns.removeAll(myDoc.keySet());
		
		//pearson coefficient를 계산하여 맵에 넣기
		Map<String,Double> pearson = new HashMap<String,Double>();
		for(Document yourDoc : foundDocument) {
			if(yourDoc.getString("user_id").equals(myDoc.getString("user_id"))) continue;
			double sx=0, sy=0, sx2=0, sy2=0, sxy=0;
			int count = 0;
			Set<String> column = new HashSet<String>();
			column.addAll(myDoc.keySet());
			for(String col : column) {
				if(col.equals("user_id") || col.equals("_id")) continue;
				if(yourDoc.containsKey(col)) {
					double x = myDoc.getDouble(col);
					double y = yourDoc.getDouble(col);
					sx += x;
					sy += y;
					sx2 += x * x;
					sy2 += y * y;
					sxy += x * y;
					count++;
				}
			}
			double dc = (double)count;
			double p = (sxy - (sx * sy / dc)) / Math.sqrt((sx2 - (sx * sx / dc)) * (sy2 - (sy * sy / dc)));
			if(p >= 0) pearson.put(yourDoc.getString("user_id"), p);
		}
		
		//pearson 기반으로 안 본 영상들의 예상평점 예상해보기
		if(pearson.isEmpty()) return null;
		Map<String,Double> predictedValue = new HashMap<String,Double>();
		for( String key : columns ) {
			double sum = 0;
			for( Document yourDoc : foundDocument ) {
				if(yourDoc.getString("user_id").equals(myDoc.getString("user_id"))) continue;
				if(yourDoc.containsKey(key)) sum += yourDoc.getDouble(key);
			}
			predictedValue.put(key, sum / foundDocument.size());
		}
		mongoClient.close();
		return predictedValue;
	}
}
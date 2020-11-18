package com.mega.mvc01.kids;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

@RequestMapping("kids")
@Controller
public class KidsController {
	
	@Autowired
	KidsService service;
	
	//페이지 리다이렉터
	@RequestMapping("/home")
	public String home() {
		return "kids/home";
	}
	@RequestMapping("/popular")
	public String popular() {
		return "kids/popular";
	}
	@RequestMapping("/subscribelist")
	public String subscribelist() {
		return "kids/subscribelist";
	}
	@RequestMapping("/likelist")
	public String likelist() {
		return "kids/likelist";
	}
	@RequestMapping("/history")
	public String history() {
		return "kids/history";
	}
	
	//전체리스트 최신순으로 뽑아오기
	@RequestMapping("/list")
	public String listByCategory(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);
		List<KidsVO> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	
//	//유저 상호작용 트래킹 기반 유저 맞춤 리스트 뽑아오기
//	@RequestMapping("/nextvideo")
//	public String listByTracker(SearcherVO vo, Model model) {
//		List<KidsVO> bag = service.listByTracker(vo);
//		model.addAttribute("bag", bag);
//		return "kids/video";
//	}
	
	//인기 기준 리스트 뽑아오기
	@RequestMapping("/listbypopular")
	public String listByPopular(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);
		List<KidsVO> bag = service.listByPopular(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	
	//구독 기준 리스트 뽑아오기
	@RequestMapping("/listbysubscribe")
	public String listBySubscribe(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);;
		List<KidsVO> bag = service.listBySubscribe(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	
	//좋아요 기준 리스트 뽑아오기
	@RequestMapping("/listbylike")
	public String listByLike(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);;
		List<KidsVO> bag = service.listByLike(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	
	//시청이력기준 리스트 뽑아오기
	@RequestMapping("/listbyhistory")
	public String listByHistory(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);
		List<KidsVO> bag = service.listByHistory(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	
	//비디오 1개 정보 뽑아오기 
	@RequestMapping("/video")
	public String getVideo(String id, String user_id, Model model) {
		KidsVO vo = new KidsVO();
		vo.setVideo_id(id);
		KidsVO video = service.one(vo);
		model.addAttribute("video", video);
		
		String[] taglist = video.getTag().split(" ");
		model.addAttribute("taglist", taglist);
		
		return "kids/video";
	}
	
	@RequestMapping("/nextvideo")
	@ResponseBody
	public List<KidsVO> getNextVideo(SearcherVO vo) {
		List<KidsVO> result = service.listByTracker(vo);
		Map<String,Double> pearson = service.getPearsonList(vo.user_id);
		if(pearson != null) {
			for(KidsVO record : result) {
				if(pearson.containsKey(record.video_id)) {
					record.pearson = pearson.get(record.video_id);
				}
			}
			Collections.sort(result);
		}
		return result;
	}
	
	//시청이력 추가하기
	@RequestMapping("/addhistory")
	public String addHistory(UserControlVO vo) {
		service.addHistory(vo);
		return "kids/addhistory";
	}
	
	//좋아요 버튼
	//결과는 "do", "undo", "toggle" 중 하나이며 결과에 따라서 페이지의 동작이 다르다.
	@RequestMapping("/like")
	public String likeVideo(UserControlVO vo, Model model) {
		String result = service.likeVideo(vo); //"do", "undo", "toggle"
		model.addAttribute("result", result);
		return "kids/like";
	}
	
	//싫어요 버튼
	@RequestMapping("/dislike")
	public String dislikeVideo(UserControlVO vo, Model model) {
		String result = service.dislikeVideo(vo);
		model.addAttribute("result", result);
		return "kids/dislike";
	}
	
	//구독 버튼
	@RequestMapping(value="/subscribe", method=RequestMethod.POST)
	public String subscribe(UserControlVO vo, Model model) {
		String result = service.subscribe(vo); //"do", "undo"
		model.addAttribute("result", result);
		return "kids/subscribe";
	}
	
	//댓글 가져오기
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String getReply(KidsReplyVO vo, Model model) {
		List<KidsReplyVO> bag = service.getReply(vo);
		model.addAttribute("reply_bag", bag);
		return "kids/reply";
	}
	
	//댓글 쓰기
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String postReply(KidsReplyVO vo, Model model) {
		KidsReplyVO result = service.postReply(vo);
		model.addAttribute("replyvo", result);
		return "kids/submitreply";
	}
	
	@RequestMapping("/tracker")
	public String userInteractTracker(KidsTrackerVO vo) {
		MongoClient mongoClient = MongoClients.create("mongodb+srv://root:1234@neotubekids.e68cc.mongodb.net/neotube?retryWrites=true&w=majority");
		MongoDatabase database = mongoClient.getDatabase("neotube");
		MongoCollection<Document> collection = database.getCollection("neotube");
		
		//user_id 찾아서 video_id 필드 업데이트, 필드나 도큐먼트가 없으면 추가
		double score = vo.elapsed_ratio + (double)vo.liketype + 1.0;
		List<Document> findUser = collection.find(
				Filters.eq("user_id", vo.user_id)
				).into(new ArrayList<Document>());
		if(!findUser.isEmpty()) {
			Document doc = findUser.get(0);
			if(doc.containsKey(vo.video_id)) {
				collection.updateOne(Filters.eq("user_id", vo.user_id), Updates.set(vo.video_id, score));
			} else {
				collection.replaceOne(Filters.eq("user_id", vo.user_id), doc.append(vo.video_id, score));
			}
		} else {
			collection.insertOne(new Document().append("user_id", vo.user_id).append(vo.video_id, score));
		}
		mongoClient.close();
		return "kids/tracker";
	}

	@RequestMapping("/json")
	public String jsonTester(Model model) {		
		// 몽고DB 클라우드 서비스에 접속 
		MongoClient mongoClient = MongoClients.create("mongodb+srv://root:1234@neotubekids.e68cc.mongodb.net/neotube?retryWrites=true&w=majority");
		MongoDatabase database = mongoClient.getDatabase("neotube");
		MongoCollection<Document> collection = database.getCollection("neotube");
		
		//insert
//		Document insertData = new Document().append("k3", new Document().append("k4", "v4").append("k5", "v5"));
//		collection.insertOne(insertData);
		
		//replace
//		collection.replaceOne(Filters.eq("k3", insertData.get("k3")),
//				new Document().append("k6", new Document().append("k7", "v7")));
		
		//find
//		List<Document> foundDocument = collection.find(Filters.exists("k3")).into(new ArrayList<Document>());
//		if(!foundDocument.isEmpty()) {
//			collection.updateOne(Filters.exists("k3"), Updates.set("k3", "v5"));
		
		//리스트 받아와서 나의 트래커 정보와 '남들은 봤는데 내가 아직 안 본' 영상 셋 추출
		//내 트래커 정보가 없을 경우 실행하지 않고 그냥 종료
		Document myDoc = null;
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		Set<String> columns = new HashSet<String>();
		for(Document doc: foundDocument) {
			columns.addAll(doc.keySet());
			if(doc.containsValue("admin")) myDoc = doc;
		}
		if(myDoc == null) return "kids/json";
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
		if(pearson.isEmpty()) return "kids/json";
		Map<String,Double> predictedValue = new HashMap<String,Double>();
		for( String key : columns ) {
			double sum = 0;
			for( Document yourDoc : foundDocument ) {
				if(yourDoc.getString("user_id").equals(myDoc.getString("user_id"))) continue;
				if(yourDoc.containsKey(key)) sum += yourDoc.getDouble(key);
			}
			predictedValue.put(key, sum / foundDocument.size());
		}
		
		
		model.addAttribute("json", predictedValue);
		mongoClient.close();
		return "kids/json";
	}
}

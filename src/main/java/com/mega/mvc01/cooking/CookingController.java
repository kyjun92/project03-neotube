package com.mega.mvc01.cooking;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mega.mvc01.ChannelVO;
import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserLikeVO;
import com.mega.mvc01.UserRecordVO;
import com.mega.mvc01.VideoVO;

@Controller
public class CookingController {

	@Autowired
	CookingServiceInterface cookingService; // 서비스단 싱글톤으로 호출

	@RequestMapping("cooking/select_main2.cooking")
	@ResponseBody // jackson lib를 이용하여 list를 json으로 변환하여 전달
	public List<VideoVO> select_main2(int page_index, Model model, HttpSession session) throws Exception {
		String userId = session.getAttribute("user_id") + ""; // 로그인이 되어 있으면 아이디를 가져옴
		List<VideoVO> list = cookingService.select_main(userId, page_index); 
		//좌측 사이드바에서 페이지를 넘기는 것을 인덱스 값을 가져와 설정하였음, 같은 형식의 페이지가 반복되기 때문에
		model.addAttribute("json", list);
		
		return list;
	}

	@RequestMapping("cooking/playingPage2.cooking")
	public void playingPage(String videoId, Model model, HttpSession session) {
		String userId = session.getAttribute("user_id") + "";
		cookingService.updatePlaynum(videoId); // 비디오의 조회수 +1, 영상을 클릭 시 조회수를 1씩 올려줌
		
		VideoVO videoVO = cookingService.playingVideo(videoId); // 재생한 비디오의 정보 조회
		model.addAttribute("videoVO", videoVO);
		ChannelVO channelVO = cookingService.selectChannel(videoVO.getChannel_id()); // 재생한 비디오를 등록한 채널 정보
		model.addAttribute("channelVO", channelVO);
		if(!userId.equals("null")) {
			int like = cookingService.selectLike(userId, videoId); // user_like table 에서 like 정보 따옴
			model.addAttribute("like", like);
			
			UserRecordVO userRecordVO = new UserRecordVO();
			userRecordVO.setUser_id(userId);
			userRecordVO.setVideo_id(videoId);
			cookingService.insertUserRecord(userRecordVO); // user_record table에 조회 기록 등록
			
			SubscribeVO subscribeVO = new SubscribeVO(); //값을 담아서 서비스로 넘겨줄 vo
			subscribeVO.setUser_id(session.getAttribute("user_id") + "");
			subscribeVO.setChannel_id(videoVO.getChannel_id());
			int sub = cookingService.startSubscribe(subscribeVO); 
			// 영상의 재생페이지에 들어갔을 때, 사용자의 아이디 값을 사용하여 구독의 정보가 db테이블에 있는지 확인하고
			// 없으면 일단 구독을 하지 않았다로 데이터 항목을 생성
			model.addAttribute("sub", sub);
		}
	}
	
	@RequestMapping("cooking/updateLike2.cooking")
	public void leavePlayingPage(String videoId, byte like, byte likeOrigin, Model model, HttpSession session) {
		// 페이지를 나갈 때, 변경된 좋아요 정보 수정
		String userId = session.getAttribute("user_id") + "";
		if(!userId.equals("null")) {
			UserLikeVO vo = new UserLikeVO(); //값을 담아주기 위해
			vo.setUser_id(userId);
			vo.setVideo_id(videoId);
			vo.setLike_index(like);
			cookingService.updateLike(vo);
			String[] l = {videoId, like+"", likeOrigin+""}; // 비디오id, 변경 like, 변경 전 like 정보를 배열로 묶어서 서비스단으로 보냄
			cookingService.updateLikeNum(l);
		}
	}

	@RequestMapping("cooking/updateSuscribe2.cooking")
	public void updateSubscribe(String channelId, Model model, HttpSession session) {
		String userId = session.getAttribute("user_id") + "";
		SubscribeVO vo = new SubscribeVO(); // 넘길 값을 담아주고
		vo.setUser_id(userId);
		vo.setChannel_id(channelId);
		int sub = cookingService.updateSubscibe(vo); // 구독 하고 취소하고의 값을 업데이트
		model.addAttribute("result", sub);  // 구독 여부에 따라 페이지 버튼의 모양을 바꾸기 위해 값을 넘겨줌
		System.out.println(sub);
	}
	
	// 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기 메일보내기
	@RequestMapping("cooking/errorMail.cooking")
	public void errorMail(HttpSession session) {
		System.out.println("controller!!!!");
		String userId = session.getAttribute("user_id") + "";
		try {
			cookingService.error(userId);
		} catch (Exception e) {
		}
		session.removeAttribute("user_id");
	}
	
}

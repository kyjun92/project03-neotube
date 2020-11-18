package com.mega.mvc01.cooking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.mvc01.SubscribeVO;
import com.mega.mvc01.UserVO;
import com.mega.mvc01.VideoVO;

@Repository
public class Algorithm {

	@Autowired
	CookingDAOInterface dao;
	
	public List<VideoVO> goAlgori(String userId, int page_index) throws Exception {
		System.out.println(userId+ page_index);
		List<VideoVO> list = dao.select_main(userId, page_index); // 리스트를 받아서
		
		int[] playNum = new int[list.size()];
		int[] likeNum = new int[list.size()];
		int[] dislikeNum = new int[list.size()];
		String[] date = new String[list.size()]; // db에서 온 데이터를 담아줄 배열들을 만들고
		for (int i = 0; i < list.size(); i++) {
			playNum[i] = list.get(i).getPlay_num(); // 조회수를 담아주고
			likeNum[i] = list.get(i).getLike_num(); // 좋아요 수를 담아주고
			dislikeNum[i] = list.get(i).getDislike_num(); // 싫어요 수를 담아주고
			date[i] = list.get(i).getVideo_date(); // 날짜를 담아주고
		}
		int[] yside_totalGrade = new int[list.size()]; // y축의 점수를 담아줄 배열 생성
		for (int i = 0; i < list.size(); i++) {
			yside_totalGrade[i] = calDateGrade(date)[i] + calGrade(playNum)[i] + calGrade(likeNum)[i]
					- calGrade(dislikeNum)[i];
			System.out.println("y " + (i + 1) + "번째 축의 값은 " + yside_totalGrade[i]);
		}
		
		int countRecord = dao.selectCountRecord(userId); // 사용자의 시청기록의 개수를 가져옴
		String[] channel_id = new String[list.size()]; // 계산에 필요한 100개의 동영상의 채널아이디를 순서대로 담아줄 배열
		for (int i = 0; i < channel_id.length; i++) {
			channel_id[i] = list.get(i).getChannel_id(); // 리스트에서 값을 꺼내 담아주고
		}
		int[] xside_totalGrade = new int[list.size()]; // x축의 점수를 담아줄 배열 생성
		for (int i = 0; i < list.size(); i++) {
			xside_totalGrade[i] = calChannelGrade(countRecord, userId, channel_id)[i];
			// 위에서 계산을 위해서 넘길 값들을 넣어 계산 후 담아주고
			System.out.println("x " + (i + 1) + "번째 축의 값은 " + xside_totalGrade[i]);
		}
		
		int[] finalGrade = finalGrade(xside_totalGrade, yside_totalGrade); // 최종 점수 계산 메서드에 넣어서 점수 쭉 나열]
		
		Object[][] addList = new Object[list.size()][2]; // 최종점수와 비디오의 아이디를 묶어주고
		for (int i = 0; i < list.size(); i++) { // 해당 아이디에 몇점이 부여되는지 알기위해서
			addList[i][0] = list.get(i).getVideo_id(); // 아이디와 최종 점수를 이차원 배열에 넣음
			addList[i][1] = finalGrade[i];
		}
		QuickSort(addList, 0, list.size()); // 정렬
		
		List<VideoVO> finalList = new ArrayList<VideoVO>();
		for (int i = 0; i < addList.length; i++) {
			String searchId = (String) addList[i][0];
			finalList.addAll(dao.selectFinalVideo(searchId));
		}
		return finalList;
	}
	

	// 데이터 처리 전 필요한 자료구조 목록
	public void swap(Object[][] a, int idx1, int idx2) {
		Object[] t = { a[idx1][0], a[idx1][1] };
		a[idx1][0] = a[idx2][0];
		a[idx1][1] = a[idx2][1];
		a[idx2][0] = t[0];
		a[idx2][1] = t[1];
	}

	public void QuickSort(Object[][] a, int l, int r) {
		int pl = l;
		int pr = r - 1;
		int x = (int) a[(pl + pr) / 2][1];

		do {
			while ((int) a[pl][1] < x)
				pl++;
			while ((int) a[pr][1] > x)
				pr--;
			if (pl <= pr)
				swap(a, pl++, pr--);
		} while (pl <= pr);

		if (l < pr)
			QuickSort(a, l, pr);
		if (pl < r)
			QuickSort(a, pl, r);
	}

	// 정렬을 해주는 메서드
	public float[] makeSort(int[] Num) {
		Arrays.sort(Num);
		float Num_min = Num[0]; // 최소값
		float Num_max = Num[Num.length - 1]; // 최대값 구해주고
		float[] minMax = { Num_min, Num_max }; // 반환해줄 데이터 담아주고

		return minMax;
	}

	// 영상의 정보를 계산 영상의 정보를 계산 영상의 정보를 계산 영상의 정보를 계산 영상의 정보를 계산 영상의 정보를 계산 영상의 정보를 계산

	// 영상의 조회수 / 좋아요 / 싫어요 의 점수를 산출하는 메서드
	public int[] calGrade(int[] Num) {
		// 리스트의 목록 중에 조회수를 담을 배열을 파라미터로 받고
		float[] minMax = makeSort(Num); // 오름차순 정렬을 해주는 메서드 호출
		float Num_min = minMax[0]; // 최소값 반환받은 값 담아주고
		float Num_max = minMax[1]; // 최대값 반환받은 값 담아주고
		// 조회수의 최대값에서 최소값을 빼서 조회된 총개수중 10개의 구간로 나눠주어 구간의 편차를 구함

		int[] NumGrade = new int[Num.length]; // 판별 점수를 담을 grade 배열 생성하고
		for (int i = 0; i < Num.length; i++) {
			NumGrade[i] = (int) ((99 / (Num_max - Num_min)) * Num[i]
					+ ((Num_max - (100 * Num_min)) / (Num_max - Num_min)));
		}
		return NumGrade;
	}

	// 날짜를 계산날짜를 계산날짜를 계산날짜를 계산날짜를 계산날짜를 계산날짜를 계산날짜를 계산날짜를 계산날짜를 계산날짜를 계산날짜를 계산

	// 영상의 날짜를 기준 날짜와 비교를 해서 점수를 산출하는 메서드
	public int[] calDateGrade(String[] inputDateTemp) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 날짜별 계산을 진행하기 위해서 날짜의 형식을 맞춰줌
		String baseDateTemp = "2000-01-01"; // 어느 날짜를 기준으로 계산을 진행할 건지에 대해서 잡아주었
		Date baseDate = format.parse(baseDateTemp);

		int[] dateGrade = new int[inputDateTemp.length];
		for (int i = 0; i < dateGrade.length; i++) {
			Date inputDate = format.parse(inputDateTemp[i]); // 순서대로 받은 날짜의 데이터 형식을 변환해주고
			float calDate = inputDate.getTime() - baseDate.getTime(); // 계산할 날짜와 기준 날짜의 차이를 구해주고
			float calDateDays = calDate / (24 * 60 * 60 * 1000); // 위에서 계산한 날짜의 수를 키워주고
			float day_year = ((calDateDays / 365) * 1000) - 20800; // 나온 수를 1년 기준으로 하루의 점수를 준다
			dateGrade[i] = (int) ((Math.abs(Math.log((2 * day_year) + 0.1))) + 1);
			// 나온 영상업로드 날짜의 점수를 구간의 점수로 주기 위해서 수식 처리를 한번 더 해준다
		}
		return dateGrade;
	}

	// 사용자의 정보로 계산 사용자의 정보로 계산 사용자의 정보로 계산 사용자의 정보로 계산 사용자의 정보로 계산 사용자의 정보로 계산

	// 채널의 점수를 산출하는 메서드 안에 들어갈 메서드(계산만 반복해줄)
	public int[] CalUserRecord(String userId, String[] channel_id) {
		List<SubscribeVO> allChlList = dao.selectCookingChannel(); // 쿠킹 카테고리의 채널 목록을 받아오고
		List<SubscribeVO> recordChlList = dao.selectRecordChannel(userId); // 사용자가 시청한 동영상의 채널과 횟수를 받아와서
		List<Integer> recordCount = dao.selectRecordChannel2(userId);
//		for (int i = 0; i < recordCount.size(); i++) {
//			System.out.println("여기 " + recordCount.get(i).intValue());
//
//		}
		Object[][] allChCount = new Object[allChlList.size()][2];
		Object[][] recordChCount = new Object[recordChlList.size()][2]; // list의 값을 넣어줄 이차원 배열을 만들고

		Random r = new Random();

		for (int i = 0; i < allChCount.length; i++) { // 이차원 배열에 값을 넣어주고
			allChCount[i][0] = allChlList.get(i).getChannel_id();
			allChCount[i][1] = (r.nextInt(10) + 1); // 0번 시청의 값은 넣지 않기 위해서 랜덤값을 삽입
		}
		for (int i = 0; i < recordChCount.length; i++) {
			recordChCount[i][0] = recordChlList.get(i).getChannel_id();
			recordChCount[i][1] = recordCount.get(i).intValue();
//			System.out.println("여기 2 "+recordChCount[i][0] + allChCount[i][1]);
		}
		for (int i = 0; i < allChCount.length; i++) {
			// 1번째 배열의 채널아이디가 2번째 배열의 채널아이디와 같다면 재생횟수를 복사해서 넣어주고
			// 그 값이 낮을 때도 값을 증가시켜주었다.
			for (int j = 0; j < recordChCount.length; j++) {
				if (allChCount[i][0].equals(recordChCount[j][0])) {
					allChCount[i][1] = (((int) recordChCount[j][1]) * (r.nextInt(10) + 1)) + 1;
				}
			}
//			System.out.println("값이 들어갔나 1 ? "+allChCount[i][1]);
		}

		int[] userGrade = new int[channel_id.length]; // 동영상 100개의 유저가 부여하게 된 채널의 점수를 넣어줄 배열 생성
		// 파라미터 값으로 받은 채널의 아이디와 1번째 배열의 채널아이디와 같다면
		// 점수 배열에 시청횟수에 따른 값을 삽입
		for (int i = 0; i < userGrade.length; i++) {
			for (int j = 0; j < allChCount.length; j++) {
				if (channel_id[i].equals(allChCount[j][0])) {
					userGrade[i] = (int) allChCount[j][1];
//					System.out.println("값이 들어갔나 2 ? "+userGrade[i]);
				}
			}
		}
		return userGrade;
	}

	// 사용자의 시청기록을 가져와서 자주 본 영상의 채널의 점수를 산출하는 메서드
	public int[] calChannelGrade(int countRecord, String userId, String[] channel_id) throws Exception { // 시청한 동영상의 총
		// 개수를 받아와서
		int[] userGrade = new int[channel_id.length]; // 동영상 100개의 유저가 부여하게 된 채널의 점수를 넣어줄 배열 생성
		// x축과 y축의 길이를 같게 만들기 위해 일일이 넣어주는 것

		if (countRecord >= 10) { // 시청 기록의 수가 10개 이상이면 진
			userGrade = CalUserRecord(userId, channel_id);
		} else {
			// 사용자의 시기록이 10개 미만인 경우 사용자 나이와 다른 사용자의 나이를 비교하여 +-3살 인 사용자들의
			// 채널 점수의 평균 값을 구하여 점수로 이용한다.
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			String oneAge = dao.selectOneAge(userId); // 사용자의 나이 받아주고
			Date oneAgeDate = format.parse(oneAge); // 데이트 형식으로 변환

			List<UserVO> allUserList = dao.selectAllAge(); // 전체 사용자의 아이디와 나이를 담아오고
			String[] allAge = new String[allUserList.size()]; // 전체 사용자의 생일을 담을 배열을 만들어주고

			String[] matchUser = new String[allUserList.size()]; // 아래 조건에 해당하는 유저의 아이디를 담아줄 배열을 만들어주고

			for (int i = 0; i < allUserList.size(); i++) {
				allAge[i] = allUserList.get(i).getBirthday(); // 리스트에서 배열에 담아주고
				Date allAgeDate = format.parse(allAge[i]); // 형식을 바꿔주고
				int ageCheck = oneAgeDate.getYear() - allAgeDate.getYear(); // 년도로만 계산을 해서
				if (ageCheck >= -3 && ageCheck <= 3) { // 그 값이 +-3 사이에 있으면
					matchUser[i] = allUserList.get(i).getUser_id(); // 평균을 구할 유저에 해당하기 때문에 담아준다.
				}
			}
			int[] temp = new int[channel_id.length]; // 채널 점수를 담아줄 임시 배열을 만들고
			for (int i = 0; i < matchUser.length; i++) { // 유저의 수만큼 식을 돌리는데
				for (int j = 0; j < channel_id.length; j++) { // 채널의 값을 더해주고
					temp[j] += CalUserRecord(matchUser[i], channel_id)[j];
				}
			}
			for (int i = 0; i < temp.length; i++) { // 채널의 점수를 일일이 빼서
				temp[i] /= matchUser.length; // 유저의 수만큼 나눠줘서 평균값을 구해줌
			}
			userGrade = temp; // 그걸 반환값으로 지정
		}
		return userGrade; // 반환
	}

	// 구한 x축과 y축의 값으로 최종 계산 메서드
	public int[] finalGrade(int[] xside_totalGrade, int[] yside_totalGrade) {
		int[] finalGrade = new int[xside_totalGrade.length]; // 결과값을 담을 배열 생성
		for (int i = 0; i < xside_totalGrade.length; i++) {
			finalGrade[i] = (int) Math.sqrt((Math.pow(xside_totalGrade[i], 2) + Math.pow(yside_totalGrade[i], 2)));
			// 피타고라스의 정리를 이용하여 x와 y의 거리를 이용하여 거리를 구했다
		}
		return finalGrade;
	}

}

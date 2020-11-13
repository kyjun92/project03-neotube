package com.mega.mvc01.sport;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
public class Algorithm {

	@Autowired
	VideoDAO dao;

	@Autowired
	VideoService service;
	
	
	public List<VideoVO> recommandAlgorithm() throws Exception {

		AlgorithmQuicksort Quicksort = new AlgorithmQuicksort();
		List<VideoVO> list = new ArrayList<>();
		list = dao.list(); // ArrayList에 DB값 저장
		

//		Diff_day df = new Diff_day(); 
//		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Random r = new Random();
//		float s_y;
//		float s_x;
//		double s_f;
//		int[][] recommandArray= new int[41][2];
//		String date;
//		
//		for (int i = 0; i < list.size(); i++) {
//			recommandArray[i][0] = i;
//			date=transFormat.format(list.get(i).getDate());
//			s_y = df.Diff_day(date);
//			s_x = list.get(i).getNum_play() /1000;
//			s_f = Math.pow((s_x*s_x)+(s_y*s_y),0.5); 
//			recommandArray[i][1] = Math.round((float)(s_f) + r.nextFloat()*45);
//		}
//		
//		QuickSort(recommandArray,0,40);
//		for (int i = 0; i < recommandArray.length; i++) {
//		}
//		
//		int[] s_id = new int[10];
//		for (int i = 0; i < 10; i++) {
//			s_id[i] = recommandArray[35-i][0];
//		}
//		int idx1;
//		JSONObject obj = new JSONObject();
//		JSONArray jArray = new JSONArray();// 배열이 필요할때
//		for (int i = 0; i < 10; i++)// 배열
//		{	
//			idx1 = s_id[i];
//			JSONObject sObject = new JSONObject();// 배열 내에 들어갈 json
//			sObject.put("id", list.get(idx1).getId());
//			sObject.put("title", list.get(idx1).getTitle());
//			sObject.put("date", transFormat.format(list.get(idx1).getDate()));
//			sObject.put("artist", list.get(idx1).getArtist());
//			sObject.put("lyric", list.get(idx1).getLyric());
//			sObject.put("file", list.get(idx1).getFile());
//			sObject.put("img", list.get(idx1).getImg());
//			sObject.put("genre", list.get(idx1).getGenre());
//			sObject.put("num_play", list.get(idx1).getNum_play());
//			jArray.add(sObject);
//		}
//		
//		obj.put("music", jArray);// 배열을 넣음
//
		return list;

	}
	
	
}

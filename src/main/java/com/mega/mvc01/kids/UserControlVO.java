package com.mega.mvc01.kids;

import java.util.Date;

//좋아요, 싫어요, 구독, 시청이력 등록을 위한 VO
public class UserControlVO {
	String video_id;
	String user_id;
	String channel_id;
	Date date;
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "UserControlVO [video_id=" + video_id + ", user_id=" + user_id + ", channel_id=" + channel_id + ", date="
				+ date + "]";
	}
	
}

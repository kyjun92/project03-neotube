package com.mega.mvc01;

public class UserLikeVO {
	String user_id;
	String video_id;
	byte like;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public byte getLike() {
		return like;
	}
	public void setLike(byte like) {
		this.like = like;
	}
}

package com.mega.mvc01;

public class UserLikeVO {
	String user_id;
	String video_id;
	byte like_index;
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
	public byte getLike_index() {
		return like_index;
	}
	public void setLike_index(byte like_index) {
		this.like_index = like_index;
	}

}

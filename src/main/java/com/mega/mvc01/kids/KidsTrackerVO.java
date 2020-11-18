package com.mega.mvc01.kids;

public class KidsTrackerVO {

	String user_id;
	String video_id;
	double elapsed_ratio;
	int liketype;
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
	public double getElapsed_ratio() {
		return elapsed_ratio;
	}
	public void setElapsed_ratio(double elapsed_ratio) {
		this.elapsed_ratio = elapsed_ratio;
	}
	public int getLiketype() {
		return liketype;
	}
	public void setLiketype(int liketype) {
		this.liketype = liketype;
	}
	@Override
	public String toString() {
		return "KidsTrackerVO [user_id=" + user_id + ", video_id=" + video_id + ", elapsed_ratio=" + elapsed_ratio
				+ ", liketype=" + liketype + "]";
	}
}

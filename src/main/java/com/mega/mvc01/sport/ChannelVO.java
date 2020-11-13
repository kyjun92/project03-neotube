package com.mega.mvc01.sport;

public class ChannelVO {

	private String channel_id;
	private String channel_title;
	private int video_num;
	private String user_id;
	private String channel_img;
	
	public String getChannel_img() {
		return channel_img;
	}
	public void setChannel_img(String channel_img) {
		this.channel_img = channel_img;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getChannel_title() {
		return channel_title;
	}
	public void setChannel_title(String channel_title) {
		this.channel_title = channel_title;
	}
	public int getVideo_num() {
		return video_num;
	}
	public void setVideo_num(int video_num) {
		this.video_num = video_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}

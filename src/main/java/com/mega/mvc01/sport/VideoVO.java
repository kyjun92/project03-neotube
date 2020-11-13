package com.mega.mvc01.sport;

import java.sql.*;

public class VideoVO {

	private String video_id;
	private String video_title;
	private String video_url;
	private int play_num;
	private int video_leng;
	private String video_date;
	private int like_num;
	private int dislike_num;
	private String tag;
	private String category;
	private String channel_id;
	private String thumbnail;
	private String channel_title;
	
	
	public String getChannel_title() {
		return channel_title;
	}
	public void setChannel_title(String channel_title) {
		this.channel_title = channel_title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public String getVideo_title() {
		return video_title;
	}
	public void setVideo_title(String video_title) {
		this.video_title = video_title;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public int getPlay_num() {
		return play_num;
	}
	public void setPlay_num(int play_num) {
		this.play_num = play_num;
	}
	public int getVideo_leng() {
		return video_leng;
	}
	public void setVideo_leng(int video_leng) {
		this.video_leng = video_leng;
	}
	
	public String getVideo_date() {
		return video_date;
	}
	public void setVideo_date(String video_date) {
		this.video_date = video_date;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public int getDislike_num() {
		return dislike_num;
	}
	public void setDislike_num(int dislike_num) {
		this.dislike_num = dislike_num;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String chanel_id) {
		this.channel_id = chanel_id;
	}
	
	@Override
	public String toString() {
		return "VideoVO [video_id=" + video_id + ", video_title=" + video_title + ", video_url=" + video_url
				+ ", play_num=" + play_num + ", video_leng=" + video_leng + ", video_date=" + video_date + ", like_num="
				+ like_num + ", dislike_num=" + dislike_num + ", tag=" + tag + ", category=" + category
				+ ", channel_id=" + channel_id + "]";
	}
	
}

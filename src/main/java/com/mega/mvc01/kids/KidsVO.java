package com.mega.mvc01.kids;

import java.util.Date;

//영상 1개 가져오기 VO

public class KidsVO {

	String video_id;
	String video_title;
	String video_url;
	int play_num;
	String video_leng;
	Date video_date;
	int like_num;
	int dislike_num;
	String tag;
	String category;
	String channel_id;
	String thumbnail;
	String channel_title;
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
	public String getVideo_leng() {
		return video_leng;
	}
	public void setVideo_leng(String video_leng) {
		this.video_leng = video_leng;
	}
	public Date getVideo_date() {
		return video_date;
	}
	public void setVideo_date(Date video_date) {
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
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getChannel_title() {
		return channel_title;
	}
	public void setChannel_title(String channel_title) {
		this.channel_title = channel_title;
	}
	@Override
	public String toString() {
		return "KidsVO [video_id=" + video_id + ", video_title=" + video_title + ", video_url=" + video_url + "]";
	}
	
}

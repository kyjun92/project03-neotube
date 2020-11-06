package com.mega.mvc01;

import java.util.Date;

public class ReplyVO {
	String reply_id;
	String user_id;
	String video_id;
	int border_id;
	String content;
	Date date;
	public String getReply_id() {
		return reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
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
	public int getBorder_id() {
		return border_id;
	}
	public void setBorder_id(int border_id) {
		this.border_id = border_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

package com.mega.mvc01;

import java.util.Date;

public class BorderVO {
	int border_id;
	String user_id;
	String border_title;
	String content;
	Date date;
	public int getBorder_id() {
		return border_id;
	}
	public void setBorder_id(int border_id) {
		this.border_id = border_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBorder_title() {
		return border_title;
	}
	public void setBorder_title(String border_title) {
		this.border_title = border_title;
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

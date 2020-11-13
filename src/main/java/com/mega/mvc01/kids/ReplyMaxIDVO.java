package com.mega.mvc01.kids;

//댓글 인덱스 최고 번호 가져오기용 VO
public class ReplyMaxIDVO {

	String reply_id;
	String category;
	public String getReply_id() {
		return reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}

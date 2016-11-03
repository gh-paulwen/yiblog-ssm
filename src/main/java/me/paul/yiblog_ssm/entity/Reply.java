package me.paul.yiblog_ssm.entity;

import java.util.Date;

public class Reply {
	
	private long id;
	
	private long comment;
	
	private String fromUser;
	
	private String toUser;
	
	private String content;
	
	private Date replytime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getComment() {
		return comment;
	}

	public void setComment(long comment) {
		this.comment = comment;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReplytime() {
		return replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

	public int getNewreply() {
		return newreply;
	}

	public void setNewreply(int newreply) {
		this.newreply = newreply;
	}

	private int newreply;

}

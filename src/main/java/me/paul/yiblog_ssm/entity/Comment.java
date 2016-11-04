package me.paul.yiblog_ssm.entity;

import java.util.Date;

public class Comment {
	
	private long id;
	
	private long passage;
	
	private String fromUser;
	
	private String toUser;
	
	private int replyCount;
	
	private String content;
	
	private Date commenttime;
	
	private int newComment;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPassage() {
		return passage;
	}

	public void setPassage(long passage) {
		this.passage = passage;
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

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}

	public int getNewComment() {
		return newComment;
	}

	public void setNewComment(int newComment) {
		this.newComment = newComment;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Comment){
			Comment c = (Comment) obj;
			if(Long.compare(c.id,id) == 0)
				return true;
		}
		return false;
	}
	
	@Override 
	public int hashCode(){
		return (int)id;
	}

}

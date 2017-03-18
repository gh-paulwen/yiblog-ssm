package me.paul.yiblog_ssm.entity;

import java.util.Date;

public class Announcement {
	
	public static final String REDIS_LAST_UPDATE_TIME = "lastupdatetime";
	
	public static final String REDIS_BUILD_TIME = "buildtime";

	private long id;

	private String content;

	private Date time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}

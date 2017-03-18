package me.paul.yiblog_ssm.entity;

import java.util.Date;

public class Passage {
	
	public static final String REDIS_PREFIX="passage_";
	
	public static final String REDIS_SIMPLE="passage_simple_";

	private long id;

	private String title;

	private User author;

	private int readtime;

	private Date writetime;

	private Category category;

	private SubCategory subCategory;

	private String content;
	
	private String outline;
	
	public String getOutline() {
		return outline;
	}
	
	public void setOutline(String outline){
		this.outline = outline;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getReadtime() {
		return readtime;
	}

	public void setReadtime(int readtime) {
		this.readtime = readtime;
	}

	public Date getWritetime() {
		return writetime;
	}

	public void setWritetime(Date writetime) {
		this.writetime = writetime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getRedisId(){
		return REDIS_PREFIX+id;
	}
	
	public String getRedisSimpleId(){
		return REDIS_SIMPLE + id;
	}

}

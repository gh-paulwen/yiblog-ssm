package me.paul.yiblog_ssm.entity;

public class Category {

	private long id;

	private String name;

	private int passageCount;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPassageCount() {
		return passageCount;
	}

	public void setPassageCount(int passageCount) {
		this.passageCount = passageCount;
	}
	

}

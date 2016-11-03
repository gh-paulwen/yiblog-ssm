package me.paul.yiblog_ssm.entity;

public class SubCategory {

	private long id;

	private String name;

	private int passageCount;
	
	private Category category;
	
	private String logopath;

	public String getLogopath() {
		return logopath;
	}
	
	public void setLogopath(String logopath) {
		this.logopath = logopath;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

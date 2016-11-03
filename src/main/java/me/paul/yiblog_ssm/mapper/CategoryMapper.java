package me.paul.yiblog_ssm.mapper;

import java.util.List;

import me.paul.yiblog_ssm.entity.Category;

public interface CategoryMapper {
	
	List<Category> getAll();
	
	void insert(Category category);
	
	void update(Category category);
	
	Category getById(long id);

}

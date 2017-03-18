package me.paul.yiblog_ssm.service;

import java.util.List;

import me.paul.yiblog_ssm.entity.Category;

public interface CategoryService {
	
	void insert(Category category);
	
	List<Category> getAll();

}

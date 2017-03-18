package me.paul.yiblog_ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paul.yiblog_ssm.entity.Category;
import me.paul.yiblog_ssm.mapper.CategoryMapper;
import me.paul.yiblog_ssm.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryMapper categoryMapper;

	public void insert(Category category) {
		categoryMapper.insert(category);
	}

	public List<Category> getAll() {
		return categoryMapper.getAll();
	}

}

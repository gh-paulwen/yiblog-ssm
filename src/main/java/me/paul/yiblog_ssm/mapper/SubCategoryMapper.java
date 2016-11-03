package me.paul.yiblog_ssm.mapper;

import java.util.List;

import me.paul.yiblog_ssm.entity.SubCategory;

public interface SubCategoryMapper {
	
	List<SubCategory> getByCategory(long categoryid);
	
	void insert(SubCategory subCategory);
	
	void update(SubCategory subCategory);
	
	SubCategory getById(long id);
	
	List<SubCategory> getAll();

}

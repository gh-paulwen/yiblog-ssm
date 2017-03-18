package me.paul.yiblog_ssm.service;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.SubCategory;

public interface SubCategoryService {
	
	ModelContent save(SubCategory subCategory);
	
	ModelContent updateLogopath(SubCategory subCategory);

}

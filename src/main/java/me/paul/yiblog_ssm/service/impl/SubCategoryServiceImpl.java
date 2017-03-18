package me.paul.yiblog_ssm.service.impl;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.SubCategory;
import me.paul.yiblog_ssm.mapper.SubCategoryMapper;
import me.paul.yiblog_ssm.service.SubCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

	@Autowired
	private SubCategoryMapper subCategoryMapper;
	@Override
	public ModelContent save(SubCategory subCategory) {
		ModelContent mc = new ModelContent();
		subCategoryMapper.insert(subCategory);
		mc.save("message", "添加成功");
		return mc;
	}

	@Override
	public ModelContent updateLogopath(SubCategory subCategory) {
		ModelContent mc = new ModelContent();
		SubCategory origin = subCategoryMapper.getById(subCategory.getId());
		origin.setLogopath(subCategory.getLogopath());
		subCategoryMapper.update(origin);
		mc.save("message", "修改成功");
		return mc;
	}

}

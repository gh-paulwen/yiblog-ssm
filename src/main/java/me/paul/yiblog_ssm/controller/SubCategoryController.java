package me.paul.yiblog_ssm.controller;

import me.paul.yiblog_ssm.entity.SubCategory;
import me.paul.yiblog_ssm.mapper.SubCategoryMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/subCategory")
public class SubCategoryController {
	
	private SubCategoryMapper subCategoryMapper ;
	
	public void setSubCategoryMapper(SubCategoryMapper subCategoryMapper) {
		this.subCategoryMapper = subCategoryMapper;
	}
	
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String submitSave(@ModelAttribute("subCategory")SubCategory subCategory,Model model){
		subCategoryMapper.insert(subCategory);
		model.addAttribute("message", "添加成功");
		return "message";
	}
	
	@RequestMapping(path="/submitUpdate",method=RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("subCategory") SubCategory subCategory,Model model){
		SubCategory origin = subCategoryMapper.getById(subCategory.getId());
		origin.setLogopath(subCategory.getLogopath());
		subCategoryMapper.update(origin);
		model.addAttribute("message", "添加成功");
		return "message";
	}
}

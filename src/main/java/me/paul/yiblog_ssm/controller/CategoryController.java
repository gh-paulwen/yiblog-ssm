package me.paul.yiblog_ssm.controller;

import java.util.List;

import me.paul.yiblog_ssm.entity.Category;
import me.paul.yiblog_ssm.mapper.CategoryMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author paul
 * @since 
 * 
 * */
@Controller
@RequestMapping(path="/category")
public class CategoryController {
	
	private CategoryMapper categoryMapper;
	
	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}
	
	/**
	 * 提交添加分类请求
	 * */
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String submitSave(@ModelAttribute("category")Category category,Model model){
		categoryMapper.insert(category);
		model.addAttribute("message", "添加成功");
		return "message";
	}
	
	/**
	 * 
	 * */
	@RequestMapping(path="/getAll",method=RequestMethod.GET)
	public String getAll(Model model){
		List<Category> list = categoryMapper.getAll();
		model.addAttribute("listCategory", list);
		return "";
	}
	
}

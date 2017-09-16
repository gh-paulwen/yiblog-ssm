package me.paul.yiblog_ssm.controller;

import java.util.List;

import me.paul.yiblog_ssm.entity.Category;
import me.paul.yiblog_ssm.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static javax.swing.text.StyleConstants.ModelAttribute;

/**
 * @author paul
 * @since 
 * 
 * */
@Controller
@RequestMapping(path="/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 提交添加分类请求
	 * */
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String submitSave(@ModelAttribute("category")Category category,Model model){
		categoryService.insert(category);
		model.addAttribute("message", "添加成功");
		return "message";
	}
	
	/**
	 * 
	 * */
	@RequestMapping(path="/getAll",method=RequestMethod.GET)
	public String getAll(Model model){
		List<Category> list = categoryService.getAll();
		model.addAttribute("listCategory", list);
		return "";
	}
	
}

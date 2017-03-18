package me.paul.yiblog_ssm.controller;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.SubCategory;
import me.paul.yiblog_ssm.service.SubCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/subCategory")
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String submitSave(@ModelAttribute("subCategory")SubCategory subCategory,Model model){
		ModelContent mc = subCategoryService.save(subCategory);
		mc.fillInModel(model);
		return "message";
	}
	
	@RequestMapping(path="/submitUpdate",method=RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("subCategory") SubCategory subCategory,Model model){
		ModelContent mc = subCategoryService.updateLogopath(subCategory);
		mc.fillInModel(model);
		return "message";
	}
}

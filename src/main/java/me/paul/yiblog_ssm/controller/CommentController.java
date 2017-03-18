package me.paul.yiblog_ssm.controller;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String submitSave(@ModelAttribute("comment")Comment comment,Model model){
		ModelContent mc = commentService.save(comment);
		mc.fillInModel(model);
		return "message";
	}
	
	@RequestMapping(path="/getAll",method=RequestMethod.GET)
	public String getAll(Model model){
		ModelContent mc = commentService.getAll();
		mc.fillInModel(model);
		return "commentList";
	}
	
	@RequestMapping(path="/getNew",method=RequestMethod.GET)
	public String getNew(Model model){
		ModelContent mc = commentService.getNew();
		mc.fillInModel(model);
		return "commentList";
	}
	
	

}

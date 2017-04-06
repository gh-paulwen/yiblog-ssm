package me.paul.yiblog_ssm.controller;

import javax.servlet.http.HttpSession;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.entity.User;
import me.paul.yiblog_ssm.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String getNew(Model model,HttpSession session){
		User user = (User) session.getAttribute("currentUser");
		if(user==null){
			return "redirect:login?target=/comment/getNew";
		}
		ModelContent mc = commentService.getNew(user.getId());
		mc.fillInModel(model);
		return "commentList";
	}
	
	@RequestMapping(path="/read/{commentid}",method=RequestMethod.GET)
	public String read(HttpSession session,Model model,@PathVariable("commentid") long commentid){
		User user = (User) session.getAttribute("currentUser");
		if(user == null){
			model.addAttribute("message", "操作未定义");
			return "message";
		}
		long author = user.getId();
		//true 为有效
		boolean res = commentService.checkCommentAuthor(commentid, author);
		if(!res){
			model.addAttribute("message", "操作未定义");
			return "message";
		}
		ModelContent mc = commentService.read(commentid); 
		mc.fillInModel(model);
		return mc.getDestination();
	}
	
	

}

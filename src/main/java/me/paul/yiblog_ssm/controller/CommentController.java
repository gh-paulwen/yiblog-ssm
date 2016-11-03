package me.paul.yiblog_ssm.controller;

import java.util.List;

import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.mapper.CommentMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/comment")
public class CommentController {
	
	private CommentMapper commentMapper;
	
	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}
	
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String submitSave(@ModelAttribute("comment")Comment comment,Model model){
		String content = comment.getContent();
		if(content == null||content.isEmpty()){
			model.addAttribute("message", "评论内容不能为空纳");
			return "message";
		}
		String fromUser = comment.getFromUser();
		if(fromUser==null||fromUser.isEmpty())
			comment.setFromUser("游客");
		comment.setToUser("paulWen");
		commentMapper.insert(comment);
		model.addAttribute("message", "评论成功");
		return "message";
	}
	
	@RequestMapping(path="/getAll",method=RequestMethod.GET)
	public String getAll(Model model){
		List<Comment> list = commentMapper.getAll();
		model.addAttribute("listComment", list);
		return "commentList";
	}
	
	@RequestMapping(path="/getNew",method=RequestMethod.GET)
	public String getNew(Model model){
		List<Comment> list = commentMapper.getNew();
		model.addAttribute("listComment", list);
		return "commentList";
	}
	
	

}

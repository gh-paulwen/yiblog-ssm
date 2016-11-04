package me.paul.yiblog_ssm.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.entity.Reply;
import me.paul.yiblog_ssm.mapper.CommentMapper;
import me.paul.yiblog_ssm.mapper.ReplyMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/reply")
public class ReplyController {
	
	private ReplyMapper replyMapper;
	
	public void setReplyMapper(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}
	
	private CommentMapper commentMapper;
	
	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}
	
	@RequestMapping(path="/save/{comment}/{fromUser}",method=RequestMethod.GET)
	public String save(@PathVariable("comment")long comment,@PathVariable("fromUser")String fromUser,Model model){
		Reply reply = new Reply();
		reply.setComment(comment);
		reply.setToUser(fromUser);
		model.addAttribute("reply", reply);
		return "reply";
	}
	
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String sumbitSave(@ModelAttribute("reply")Reply reply,Model model) throws UnsupportedEncodingException{
		reply.setFromUser("paulWen");
		Comment c = commentMapper.getById(reply.getComment());
		c.setNewComment(0);
		commentMapper.update(c);
		String toUser = new String(reply.getToUser().getBytes("ISO-8859-1"),"UTF-8");
		reply.setToUser(toUser);
		replyMapper.insert(reply);
		return "redirect:/operation";
	}
	
	@RequestMapping(path="/getAll",method=RequestMethod.GET)
	public String getAll(Model model){
		List<Reply> list = replyMapper.getAll();
		model.addAttribute("listReply", list);
		return "replyList";
	}
	
	public String getNew(Model model){
		List<Reply> list = replyMapper.getNew();
		model.addAttribute("listReply",list);
		return "replyList";
	}

}

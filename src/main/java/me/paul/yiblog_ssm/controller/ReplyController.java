package me.paul.yiblog_ssm.controller;

import java.io.UnsupportedEncodingException;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Reply;
import me.paul.yiblog_ssm.service.ReplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
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
		ModelContent mc = replyService.save(reply);
		mc.fillInModel(model);
		return "redirect:/operation";
	}
	
	@RequestMapping(path="/getAll",method=RequestMethod.GET)
	public String getAll(Model model){
		ModelContent mc = replyService.getAll();
		mc.fillInModel(model);
		return "replyList";
	}
	
	public String getNew(Model model){
		ModelContent mc = replyService.getNew();
		mc.fillInModel(model);
		return "replyList";
	}

}

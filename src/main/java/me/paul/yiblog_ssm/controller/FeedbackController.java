package me.paul.yiblog_ssm.controller;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Feedback;
import me.paul.yiblog_ssm.service.FeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/feedback")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping(path="/save",method=RequestMethod.GET)
	public String save(Model model){
		model.addAttribute("feedback", new Feedback());
		return "feedback";
	}
	
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String submitSave(@ModelAttribute("feedback")Feedback feedback,Model model){
		ModelContent mc = feedbackService.save(feedback);
		mc.fillInModel(model);
		return "message";
	}
	
	@RequestMapping(path="/getAll",method=RequestMethod.GET)
	public String getAll(Model model){
		ModelContent mc = feedbackService.getAll();
		mc.fillInModel(model);
		return "feedbackList";
	}

}

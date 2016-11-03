package me.paul.yiblog_ssm.controller;

import java.util.List;

import me.paul.yiblog_ssm.entity.Feedback;
import me.paul.yiblog_ssm.mapper.FeedbackMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/feedback")
public class FeedbackController {
	
	private FeedbackMapper feedbackMapper;
	
	public void setFeedbackMapper(FeedbackMapper feedbackMapper) {
		this.feedbackMapper = feedbackMapper;
	}
	
	@RequestMapping(path="/save",method=RequestMethod.GET)
	public String save(Model model){
		model.addAttribute("feedback", new Feedback());
		return "feedback";
	}
	
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String submitSave(@ModelAttribute("feedback")Feedback feedback,Model model){
		String content = feedback.getFeedbackContent();
		if(content==null || content.isEmpty()){
			model.addAttribute("message", "反馈失败，反馈内容为空");
			return "message";
		}
		feedbackMapper.insert(feedback);
		model.addAttribute("message", "反馈成功，谢谢你的反馈");
		return "message";
	}
	
	@RequestMapping(path="/getAll",method=RequestMethod.GET)
	public String getAll(Model model){
		List<Feedback> list = feedbackMapper.getAll();
		model.addAttribute("feedbackList", list);
		return "feedbackList";
	}

}

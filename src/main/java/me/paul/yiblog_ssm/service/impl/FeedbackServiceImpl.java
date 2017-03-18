package me.paul.yiblog_ssm.service.impl;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Feedback;
import me.paul.yiblog_ssm.mapper.FeedbackMapper;
import me.paul.yiblog_ssm.service.FeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Override
	public ModelContent save(Feedback feedback) {
		ModelContent mc = new ModelContent();
		String content = feedback.getFeedbackContent();
		if(content==null || content.isEmpty()){
			mc.save("message", "反馈失败，反馈内容为空");
			return mc;
		}
		feedbackMapper.insert(feedback);
		mc.save("message", "反馈成功，谢谢你的反馈");
		return mc;
	}

	@Override
	public ModelContent getAll() {
		ModelContent mc = new ModelContent();
		return mc;
	}

}

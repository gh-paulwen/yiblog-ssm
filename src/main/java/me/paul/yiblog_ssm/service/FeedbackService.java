package me.paul.yiblog_ssm.service;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Feedback;

public interface FeedbackService {
	
	ModelContent save(Feedback feedback);
	
	ModelContent getAll();

}

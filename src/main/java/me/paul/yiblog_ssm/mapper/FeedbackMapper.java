package me.paul.yiblog_ssm.mapper;

import java.util.List;

import me.paul.yiblog_ssm.entity.Feedback;

public interface FeedbackMapper {
	
	void insert(Feedback feedback);
	
	List<Feedback> getAll();
	
}

package me.paul.yiblog_ssm.service;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Comment;

public interface CommentService {
	
	ModelContent save(Comment comment);
	
	ModelContent getAll();
	
	ModelContent getNew();

}

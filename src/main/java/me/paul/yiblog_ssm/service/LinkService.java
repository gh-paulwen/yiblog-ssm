package me.paul.yiblog_ssm.service;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Link;

public interface LinkService {
	
	ModelContent pass(long id);
	
	ModelContent save(Link link);
	
	ModelContent getPass();

}

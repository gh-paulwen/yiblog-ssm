package me.paul.yiblog_ssm.service;

import java.io.UnsupportedEncodingException;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Reply;

public interface ReplyService {
	
	ModelContent save(Reply reply)  throws UnsupportedEncodingException ;
	
	ModelContent getAll();
	
	ModelContent getNew();

}

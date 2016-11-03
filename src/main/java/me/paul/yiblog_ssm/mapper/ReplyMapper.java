package me.paul.yiblog_ssm.mapper;

import java.util.List;

import me.paul.yiblog_ssm.entity.Reply;

public interface ReplyMapper {
	
	void insert(Reply reply);
	
	Reply select(long id);
	
	List<Reply> getAll();
	
	List<Reply> getNew();
	
	Reply getByComment(long id);

}

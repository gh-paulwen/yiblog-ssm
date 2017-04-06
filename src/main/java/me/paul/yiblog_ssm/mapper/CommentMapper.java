package me.paul.yiblog_ssm.mapper;

import java.util.List;

import me.paul.yiblog_ssm.entity.Comment;

public interface CommentMapper {
	
	void insert(Comment comment);
	
	Comment select(long id);
	
	List<Comment> getAll();
	
	List<Comment> getNew(long author);
	
	List<Comment> getByPassage(long id);
	
	Comment getById(long id);
	
	void update(Comment c);
	
}

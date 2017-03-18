package me.paul.yiblog_ssm.service.impl;

import java.util.List;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.mapper.CommentMapper;
import me.paul.yiblog_ssm.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public ModelContent save(Comment comment) {
		ModelContent mc = new ModelContent();
		String content = comment.getContent();
		if(content == null||content.isEmpty()){
			mc.save("message", "评论内容不能为空纳");
			return mc;
		}
		String fromUser = comment.getFromUser();
		if(fromUser==null||fromUser.isEmpty())
			comment.setFromUser("游客");
		comment.setToUser("paulWen");
		commentMapper.insert(comment);
		mc.save("message", "评论成功");
		return mc;
	}

	@Override
	public ModelContent getAll() {
		ModelContent mc = new ModelContent();
		List<Comment> list = commentMapper.getAll();
		mc.save("listComment", list);
		return mc;
	}

	@Override
	public ModelContent getNew() {
		ModelContent mc = new ModelContent();
		List<Comment> list = commentMapper.getNew();
		mc.save("listComment", list);
		return mc;
	}

}

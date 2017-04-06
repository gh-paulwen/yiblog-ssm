package me.paul.yiblog_ssm.service.impl;

import java.util.List;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.mapper.CommentMapper;
import me.paul.yiblog_ssm.mapper.PassageMapper;
import me.paul.yiblog_ssm.mapper.UserMapper;
import me.paul.yiblog_ssm.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PassageMapper passageMapper;
	
	@Override
	public ModelContent save(Comment comment) {
		ModelContent mc = new ModelContent();
		String content = comment.getContent();
		if(content == null||content.isEmpty()){
			mc.save("message", "评论内容不能为空");
			return mc;
		}
		String fromUser = comment.getFromUser();
		if(fromUser==null||fromUser.isEmpty())
			comment.setFromUser("游客");
		String authorName = userMapper.getAuthorNameByPassage(comment.getPassage());
		comment.setToUser(authorName);
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
	public ModelContent getNew(long author) {
		ModelContent mc = new ModelContent();
		List<Comment> list = commentMapper.getNew(author);
		mc.save("listComment", list);
		return mc;
	}

	@Override
	public ModelContent read(long comment) {
		ModelContent mc = new ModelContent("message");
		Comment com = commentMapper.getById(comment);
		if(com == null){
			mc.save("message", "操作出错");
			return mc;
		}
		com.setNewComment(0);
		commentMapper.update(com);
		mc.save("message", "操作成功");
		return mc;
	}

	@Override
	public boolean checkCommentAuthor(long comment, long author) {
		Passage p = passageMapper.getByCommentAndAuthor(comment, author);
		return p != null;
	}
	
}

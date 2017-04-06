package me.paul.yiblog_ssm.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.entity.Reply;
import me.paul.yiblog_ssm.mapper.CommentMapper;
import me.paul.yiblog_ssm.mapper.PassageMapper;
import me.paul.yiblog_ssm.mapper.ReplyMapper;
import me.paul.yiblog_ssm.service.ReplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private PassageMapper passageMapper;

	@Override
	public ModelContent save(Reply reply) throws UnsupportedEncodingException {
		ModelContent mc = new ModelContent();
		reply.setFromUser("paulWen");
		Comment c = commentMapper.getById(reply.getComment());
		c.setNewComment(0);
		commentMapper.update(c);
		String toUser = new String(reply.getToUser().getBytes("ISO-8859-1"),"UTF-8");
		reply.setToUser(toUser);
		replyMapper.insert(reply);
		return mc;
	}

	@Override
	public ModelContent getAll() {
		ModelContent mc = new ModelContent();
		List<Reply> list = replyMapper.getAll();
		mc.save("listReply", list);
		return mc;
	}

	@Override
	public ModelContent getNew() {
		ModelContent mc = new ModelContent();
		List<Reply> list = replyMapper.getNew();
		mc.save("listReply",list);
		return mc;
	}

	@Override
	public boolean checkCommentAuthor(long comment, long author) {
		Passage passage = passageMapper.getByCommentAndAuthor(comment, author);
		return passage!=null;
	}

}

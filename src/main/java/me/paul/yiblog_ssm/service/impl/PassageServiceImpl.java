package me.paul.yiblog_ssm.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Announcement;
import me.paul.yiblog_ssm.entity.Category;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.entity.Reply;
import me.paul.yiblog_ssm.entity.SubCategory;
import me.paul.yiblog_ssm.entity.User;
import me.paul.yiblog_ssm.mapper.AnnouncementMapper;
import me.paul.yiblog_ssm.mapper.CategoryMapper;
import me.paul.yiblog_ssm.mapper.CommentMapper;
import me.paul.yiblog_ssm.mapper.PassageMapper;
import me.paul.yiblog_ssm.mapper.ReplyMapper;
import me.paul.yiblog_ssm.mapper.SubCategoryMapper;
import me.paul.yiblog_ssm.service.PassageService;
import me.paul.yiblog_ssm.util.ICachePassageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassageServiceImpl implements PassageService{
	
	@Autowired
	private PassageMapper passageMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private SubCategoryMapper subCategoryMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private AnnouncementMapper announcementMapper;
	
	@Autowired
	private ICachePassageUtil icpu;

	@Override
	public ModelContent get(long id) {
		ModelContent mc = new ModelContent();
		Passage passage = null;
		passage = icpu.get(Passage.REDIS_PREFIX + id);
			
		if(passage == null){
			passage = passageMapper.getById(id);
		}
		
		if (passage == null) {return mc;}
		
		List<Comment> listComment = commentMapper.getByPassage(passage.getId());
		Map<Comment, Reply> mapCommentReply = new HashMap<Comment, Reply>();
		for (Comment c : listComment) {
			Reply reply = replyMapper.getByComment(c.getId());
			mapCommentReply.put(c, reply);
		}
		mc.save("passage", passage);
		mc.save("mapCommentReply", mapCommentReply);
		Comment comment = new Comment();
		comment.setPassage(id);
		mc.save("comment", comment);
		return mc;
	}

	@Transactional
	@Override
	public synchronized ModelContent edit(Passage passage, long before) {
		ModelContent mc = new ModelContent();
		SubCategory subCategory = subCategoryMapper.getById(passage.getSubCategory().getId());
		if(Long.compare(before, passage.getSubCategory().getId()) != 0){
			SubCategory beforeSubCategory = subCategoryMapper.getById(before);
			beforeSubCategory.setPassageCount(beforeSubCategory.getPassageCount() -1);
			beforeSubCategory.getCategory().setPassageCount(beforeSubCategory.getCategory().getPassageCount() - 1);
	        subCategory.setPassageCount(subCategory.getPassageCount() + 1);
	        subCategory.getCategory().setPassageCount(subCategory.getCategory().getPassageCount() + 1);
			subCategoryMapper.update(beforeSubCategory);
			subCategoryMapper.update(subCategory);
			categoryMapper.update(beforeSubCategory.getCategory());
			categoryMapper.update(subCategory.getCategory());
		}
        passage.setSubCategory(subCategory);
        passage.setCategory(subCategory.getCategory());
		passageMapper.update(passage);
		icpu.set(passage);
		return mc;
	}

	@Transactional
	@Override
	public ModelContent save(Passage passage) {
		ModelContent mc = new ModelContent();
		synchronized (this) {
			SubCategory subCategory = subCategoryMapper.getById(passage
					.getSubCategory().getId());
			subCategory.setPassageCount(subCategory.getPassageCount() + 1);
			Category category = categoryMapper.getById(subCategory
					.getCategory().getId());
			Announcement lastUpdate = announcementMapper.getById(3l);
			lastUpdate.setTime(new Date());
			category.setPassageCount(category.getPassageCount() + 1);
			passage.setCategory(category);
			User user = new User();
			user.setId(1l);
			passage.setAuthor(user);
			passageMapper.insert(passage);
			announcementMapper.update(lastUpdate);
			subCategoryMapper.update(subCategory);
			categoryMapper.update(category);
		}
		Passage completePassage = passageMapper.getById(passage.getId());
		icpu.save(completePassage);
		mc.save("message", "添加成功");
		return mc;
	}

	@Override
	public ModelContent page(int page,int passagePerPage) {
		ModelContent mc = new ModelContent();
		List<Passage> listPassage = Collections.emptyList();
		listPassage = icpu.simpleList(page,passagePerPage);
		mc.save("listPassage", listPassage);
		int totalPassageCount = passageMapper.passageCount();
		if (totalPassageCount <= passagePerPage * page) {
			mc.save("nextPage", page);
		} else {
			mc.save("nextPage", page + 1);
		}
		if (page == 1) {
			mc.save("lastPage", page);
		} else {
			mc.save("lastPage", page - 1);
		}
		return mc;
	}

	@Override
	public ModelContent categoryPage(long category, int page,int passagePerPage) {
		ModelContent mc = new ModelContent();
		List<Passage> list = Collections.emptyList(); 
		list = icpu.simpleList(page, passagePerPage, category);
		mc.save("listPassage", list);
		mc.save("category", category);
		int categoryPassageCount = passageMapper.categoryPassageCount(category);
		if (categoryPassageCount <= passagePerPage * page) {
			mc.save("nextPage", page);
		} else {
			mc.save("nextPage", page + 1);
		}
		if (page == 1) {
			mc.save("lastPage", page);
		} else {
			mc.save("lastPage", page - 1);
		}
		return mc;
	}

	@Override
	public ModelContent search(String content) throws UnsupportedEncodingException {
		ModelContent mc = new ModelContent();
		content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
		content = "%" + content + "%";
		List<Passage> list = passageMapper.search(content);
		mc.save("listPassage", list);
		mc.save("hide", 1);
		return mc;
	}

	@Deprecated
	@Override
	public Passage onlyPassage(long id) {
		return icpu.get(Passage.REDIS_PREFIX + id);
	}

}

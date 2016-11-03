package me.paul.yiblog_ssm.controller;

import java.util.Date;
import java.util.List;

import me.paul.yiblog_ssm.entity.Category;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.entity.SubCategory;
import me.paul.yiblog_ssm.entity.User;
import me.paul.yiblog_ssm.mapper.CategoryMapper;
import me.paul.yiblog_ssm.mapper.CommentMapper;
import me.paul.yiblog_ssm.mapper.PassageMapper;
import me.paul.yiblog_ssm.mapper.SubCategoryMapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/test")
public class TestController {
	
	private CategoryMapper categoryMapper ;
	
	private SubCategoryMapper subCategoryMapper;
	
	private PassageMapper passageMapper;
	
	private CommentMapper commentMapper;
	
	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}
	
	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}
	
	public void setSubCategoryMapper(SubCategoryMapper subCategoryMapper) {
		this.subCategoryMapper = subCategoryMapper;
	}
	
	public void setPassageMapper(PassageMapper passageMapper) {
		this.passageMapper = passageMapper;
	}
	
	@RequestMapping(path="/category")
	public String categoryTest(){
		List<Category> list = categoryMapper.getAll();
		for(Category c : list){
			System.out.println(c.getName());
		}
		
		return "index";
	}
	
	@RequestMapping(path="/subCategory")
	public String subCategoryTest(){
		List<SubCategory> list = subCategoryMapper.getByCategory(1l);
		System.out.println(list.size());
		for(SubCategory sc:list){
			System.out.println(sc.getName());
		}
		return "index";
	}
	
	@RequestMapping(path="/passage")
	public String passageTest(){
		Passage passage = passageMapper.getById(2l);
		System.out.println(passage.getContent());
		return "index";
	}
	
	@RequestMapping(path="/addComment")
	public String addCommentTest(){
		Comment comment = new Comment();
		comment.setPassage(6l);
		comment.setFromUser("john");
		comment.setToUser("paulWen");
		comment.setContent("i love you");
		comment.setNewComment(1);
		comment.setReplyCount(0);
		comment.setCommenttime(new Date());
		commentMapper.insert(comment);
		return "index";
	}
	
	@RequestMapping(path="/getComment")
	public String getCommentTest(){
		Comment comment = commentMapper.select(1l);
		System.out.println(comment.getContent());
		return "index";
	}
	
	@RequestMapping(path="/addPassage")
	public String addPassage(){
		Passage passage = new Passage();
		User user = new User();
		user.setId(1l);
		Category category = new Category();
		category.setId(1l);
		SubCategory subCategory = new SubCategory();
		subCategory.setId(1l);
		passage.setAuthor(user);
		passage.setCategory(category);
		passage.setSubCategory(subCategory);
		passage.setContent("jkafjdjflasjdlkfjsdalkjflksdjlkfjlsdakjfhadkjshfuiewhfe");
		passage.setReadtime(0);
		passage.setTitle("r93hfweujiifehdurfuw");
		passage.setWritetime(new Date());
		passageMapper.insert(passage);
		return "index";
	}

}

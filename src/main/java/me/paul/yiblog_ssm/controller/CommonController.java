package me.paul.yiblog_ssm.controller;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import me.paul.yiblog_ssm.entity.Category;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.entity.Feedback;
import me.paul.yiblog_ssm.entity.Link;
import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.entity.SubCategory;
import me.paul.yiblog_ssm.mapper.CategoryMapper;
import me.paul.yiblog_ssm.mapper.CommentMapper;
import me.paul.yiblog_ssm.mapper.FeedbackMapper;
import me.paul.yiblog_ssm.mapper.LinkMapper;
import me.paul.yiblog_ssm.mapper.PassageMapper;
import me.paul.yiblog_ssm.util.ICachePassageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
	
	@Autowired
	private ICachePassageUtil icpu;
	
	@Autowired
	private PassageMapper passageMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private LinkMapper linkMapper;

	@Autowired
	private FeedbackMapper feedbackMapper;

	private int passagePerPage;

	public void setPassagePerPage(int passagePerPage) {
		this.passagePerPage = passagePerPage;
	}
	
	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		List<Passage> listPassage = Collections.emptyList(); 
		listPassage = icpu.simpleList(1, passagePerPage);
		model.addAttribute("listPassage", listPassage);
		int totalPassageCount = passageMapper.passageCount();
		if (totalPassageCount <= passagePerPage * 1) {
			model.addAttribute("nextPage", 1);
		} else {
			model.addAttribute("nextPage", 2);
		}
		model.addAttribute("lastPage", 1);
		return "index";
	}

	@RequestMapping(path = "/about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}

	@RequestMapping(path = "/cates", method = RequestMethod.GET)
	public String cates(Model model) {
		List<Category> listCategory = categoryMapper.getAll();
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("category", new Category());
		model.addAttribute("subCategory", new SubCategory());
		return "cates";
	}

	@RequestMapping(path = "/operation", method = RequestMethod.GET)
	public String operation(Model model) {
		List<Comment> listComment = commentMapper.getNew();
		List<Link> listLink = linkMapper.getAll();
		List<Feedback> listFeedback = feedbackMapper.getAll();
		List<Passage> listPassage = passageMapper.getBasic();
		model.addAttribute("listComment", listComment);
		model.addAttribute("listLink", listLink);
		model.addAttribute("listFeedback", listFeedback);
		model.addAttribute("listPassage", listPassage);
		return "operation";
	}
	
	@PostConstruct
	public void postConstruct(){
		icpu.flush();
		List<Passage> list = passageMapper.getAll();
		icpu.batchSave(list);
	}
}

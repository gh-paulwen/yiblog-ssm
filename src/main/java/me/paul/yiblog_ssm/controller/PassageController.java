package me.paul.yiblog_ssm.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.paul.yiblog_ssm.entity.Category;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.entity.Reply;
import me.paul.yiblog_ssm.entity.SubCategory;
import me.paul.yiblog_ssm.entity.User;
import me.paul.yiblog_ssm.mapper.CategoryMapper;
import me.paul.yiblog_ssm.mapper.CommentMapper;
import me.paul.yiblog_ssm.mapper.PassageMapper;
import me.paul.yiblog_ssm.mapper.ReplyMapper;
import me.paul.yiblog_ssm.mapper.SubCategoryMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/passage")
public class PassageController {

	private PassageMapper passageMapper;
	private CategoryMapper categoryMapper;
	private SubCategoryMapper subCategoryMapper;
	private CommentMapper commentMapper;
	private ReplyMapper replyMapper;
	private int passagePerPage;

	public void setPassagePerPage(int passagePerPage) {
		this.passagePerPage = passagePerPage;
	}

	public void setPassageMapper(PassageMapper passageMapper) {
		this.passageMapper = passageMapper;
	}

	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	public void setSubCategoryMapper(SubCategoryMapper subCategoryMapper) {
		this.subCategoryMapper = subCategoryMapper;
	}

	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	public void setReplyMapper(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}

	/**
	 * 用文章号获取文章
	 * */
	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	public String getPassage(@PathVariable("id") long id, Model model) {
		if (id < 0) {
			return "error";
		}
		Passage passage = passageMapper.getById(id);
		if (passage == null)
			return "post";
		List<Comment> listComment = commentMapper.getByPassage(passage.getId());
		Map<Comment, Reply> mapCommentReply = new HashMap<Comment, Reply>();
		for (Comment c : listComment) {
			Reply reply = replyMapper.getByComment(c.getId());
			mapCommentReply.put(c, reply);
		}
		model.addAttribute("passage", passage);
		model.addAttribute("mapCommentReply", mapCommentReply);
		Comment comment = new Comment();
		comment.setPassage(id);
		model.addAttribute("comment", comment);
		return "post";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
	public String editPassage(@PathVariable("id") long id, Model model) {
		if (id < 0) {
			return "error";
		}
		Passage passage = passageMapper.getById(id);
		model.addAttribute("passage", passage);
		model.addAttribute("beforeModify", passage.getSubCategory().getId());
		return "edit";
	}

	@RequestMapping(path = "/submitEdit", method = RequestMethod.POST)
	public synchronized String submitEdit(@ModelAttribute("passage") Passage passage,@RequestParam("before") long before,
			Model model) {
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
		return "redirect:/operation";
	}

	@RequestMapping(path = "/save", method = RequestMethod.GET)
	public String save(Model model) {
		model.addAttribute("passage", new Passage());
		return "passage";
	}

	@RequestMapping(path = "/submitSave", method = RequestMethod.POST)
	public String submitSave(@ModelAttribute("passage") Passage passage,
			Model model) {
		synchronized (this) {
			SubCategory subCategory = subCategoryMapper.getById(passage
					.getSubCategory().getId());
			subCategory.setPassageCount(subCategory.getPassageCount() + 1);
			Category category = categoryMapper.getById(subCategory
					.getCategory().getId());
			category.setPassageCount(category.getPassageCount() + 1);
			passage.setCategory(category);
			User user = new User();
			user.setId(1l);
			passage.setAuthor(user);
			passageMapper.insert(passage);
			subCategoryMapper.update(subCategory);
			categoryMapper.update(category);
		}
		model.addAttribute("message", "添加成功");
		return "message";
	}

	@RequestMapping(path = "/page/{page}", method = RequestMethod.GET)
	public String page(@PathVariable("page") int page, Model model) {
		List<Passage> listPassage = passageMapper.page((page - 1)
				* passagePerPage, passagePerPage);
		model.addAttribute("listPassage", listPassage);
		int totalPassageCount = passageMapper.passageCount();
		if (totalPassageCount <= passagePerPage * page) {
			model.addAttribute("nextPage", page);
		} else {
			model.addAttribute("nextPage", page + 1);
		}
		if (page == 1) {
			model.addAttribute("lastPage", page);
		} else {
			model.addAttribute("lastPage", page - 1);
		}
		return "index";
	}

	@RequestMapping(path = "/page/{category}/{page}", method = RequestMethod.GET)
	public String categoryPage(@PathVariable("category") long category,
			@PathVariable("page") int page, Model model) {
		List<Passage> list = passageMapper.categoryPage(category, (page - 1) * passagePerPage,
				passagePerPage);
		model.addAttribute("listPassage", list);
		model.addAttribute("category", category);
		int categoryPassageCount = passageMapper.categoryPassageCount(category);
		if (categoryPassageCount <= passagePerPage * page) {
			model.addAttribute("nextPage", page);
		} else {
			model.addAttribute("nextPage", page + 1);
		}
		if (page == 1) {
			model.addAttribute("lastPage", page);
		} else {
			model.addAttribute("lastPage", page - 1);
		}
		return "index";
	}

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("content") String content, Model model)
			throws UnsupportedEncodingException {
		content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
		content = "%" + content + "%";
		List<Passage> list = passageMapper.search(content);
		model.addAttribute("listPassage", list);
		model.addAttribute("hide", 1);
		return "index";
	}

}

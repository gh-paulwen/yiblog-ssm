package me.paul.yiblog_ssm.controller;

import java.io.UnsupportedEncodingException;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.service.PassageService;

import org.springframework.beans.factory.annotation.Autowired;
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

	private int passagePerPage ;

	public void setPassagePerPage(int passagePerPage) {
		this.passagePerPage = passagePerPage;
	}
	
	@Autowired
	private PassageService passageService;

	/**
	 * 用文章号获取文章
	 * */
	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	public String getPassage(@PathVariable("id") long id, Model model) {
		if (id < 0) {
			return "error";
		}
		ModelContent mc = passageService.get(id);
		mc.fillInModel(model);
		return "post";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
	public String editPassage(@PathVariable("id") long id, Model model) {
		if (id < 0) {
			return "error";
		}
		Passage passage = passageService.onlyPassage(id);
		model.addAttribute("passage", passage);
		model.addAttribute("beforeModify", passage.getSubCategory().getId());
		return "edit";
	}

	@RequestMapping(path = "/submitEdit", method = RequestMethod.POST)
	public synchronized String submitEdit(@ModelAttribute("passage") Passage passage,@RequestParam("before") long before,
			Model model) {
		ModelContent mc = passageService.edit(passage, before);
		mc.fillInModel(model);
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
		ModelContent mc = passageService.save(passage);
		mc.fillInModel(model);
		return "message";
	}

	@RequestMapping(path = "/page/{page}", method = RequestMethod.GET)
	public String page(@PathVariable("page") int page, Model model) {
		ModelContent mc = passageService.page(page, passagePerPage);
		mc.fillInModel(model);
		return "index";
	}

	@RequestMapping(path = "/page/{category}/{page}", method = RequestMethod.GET)
	public String categoryPage(@PathVariable("category") long category,
			@PathVariable("page") int page, Model model) {
		ModelContent mc = passageService.categoryPage(category, page, passagePerPage);
		mc.fillInModel(model);
		return "index";
	}

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("content") String content, Model model)
			throws UnsupportedEncodingException {
		ModelContent mc = passageService.search(content);
		mc.fillInModel(model);
		return "index";
	}

}

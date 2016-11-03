package me.paul.yiblog_ssm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class CommonController {

	private PassageMapper passageMapper;

	private CategoryMapper categoryMapper;

	private CommentMapper commentMapper;

	private LinkMapper linkMapper;

	private FeedbackMapper feedbackMapper;

	private CommonsMultipartResolver multipartResolver;

	public void setMultipartResolver(CommonsMultipartResolver multipartResolver) {
		this.multipartResolver = multipartResolver;
	}

	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	public void setPassageMapper(PassageMapper passageMapper) {
		this.passageMapper = passageMapper;
	}

	public void setCommentMapper(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	public void setFeedbackMapper(FeedbackMapper feedbackMapper) {
		this.feedbackMapper = feedbackMapper;
	}

	public void setLinkMapper(LinkMapper linkMapper) {
		this.linkMapper = linkMapper;
	}

	private int passagePerPage;

	public void setPassagePerPage(int passagePerPage) {
		this.passagePerPage = passagePerPage;
	}

	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		List<Passage> listPassage = passageMapper.page(0, passagePerPage);
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

	@RequestMapping(path = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "upload";
	}

	@RequestMapping(path = "/submitUpload", method = RequestMethod.POST)
	public String submitUpload(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("filepath") String filepath)
			throws IllegalStateException, IOException {
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multipartRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multipartRequest.getFile(iter.next());
				if (file != null) {
					String path = request.getServletContext().getRealPath(
							"/WEB-INF/static/");
					File localFile = new File(path, filepath);
					file.transferTo(localFile);
				}
			}
		}
		request.setAttribute("message", "上传成功");
		return "message";
	}

	@RequestMapping(path = "/download", method = RequestMethod.GET)
	public String download(@RequestParam("filename") String filename,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="+filename);
		
		try{
			String path = request.getServletContext().getRealPath("/WEB-INF/static/file/");
			File file = new File(path,filename);
			InputStream inputStream = new FileInputStream(file);
			
			OutputStream os = response.getOutputStream();
			
			byte[] b = new byte[2048];
			int length = 0;
			while((length = inputStream.read(b)) > 0){
				os.write(b, 0, length);
			}
			os.close();
			inputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}

}

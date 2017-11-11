package me.paul.yiblog_ssm.controller;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import me.paul.yiblog_ssm.entity.Category;
import me.paul.yiblog_ssm.entity.Comment;
import me.paul.yiblog_ssm.entity.Feedback;
import me.paul.yiblog_ssm.entity.Link;
import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.entity.SubCategory;
import me.paul.yiblog_ssm.entity.User;
import me.paul.yiblog_ssm.mapper.CategoryMapper;
import me.paul.yiblog_ssm.mapper.CommentMapper;
import me.paul.yiblog_ssm.mapper.FeedbackMapper;
import me.paul.yiblog_ssm.mapper.LinkMapper;
import me.paul.yiblog_ssm.mapper.PassageMapper;
import me.paul.yiblog_ssm.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {

    @Autowired
    private PassageMapper passageMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Value("8")
    private int passagePerPage;

    @RequestMapping(path="/",method = RequestMethod.GET)
    public String welcome(){
        return "forward:/index";
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<Passage> listPassage = Collections.emptyList();
        listPassage = passageMapper.page(0, passagePerPage);
        model.addAttribute("listPassage", listPassage);
        int totalPassageCount = passageMapper.passageCount();
        if (totalPassageCount <= passagePerPage) {
            model.addAttribute("nextPage", 1);
        } else {
            model.addAttribute("nextPage", 2);
        }
        model.addAttribute("lastPage", 1);
        return "index";
    }

    @RequestMapping(path = "/page/{page}", method = RequestMethod.GET)
    public String page(@PathVariable("page") String page) {
        return page;
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
    public String operation(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        List<Comment> listComment = commentMapper.getNew(user.getId());
        List<Passage> listPassage = passageMapper.getBasic(user.getId());
        model.addAttribute("listComment", listComment);
        model.addAttribute("listPassage", listPassage);
        return "operation";
    }

    @RequestMapping(path = "/adminOperation", method = RequestMethod.GET)
    public String adminOperation(Model model) {
        List<Feedback> listFeedback = feedbackMapper.getAll();
        List<Link> listLink = linkMapper.getAll();
        List<Passage> listPassage = passageMapper.getBasicAll();
        List<User> listUser = userMapper.getAll();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listLink", listLink);
        model.addAttribute("listPassage", listPassage);
        model.addAttribute("listUser", listUser);
        return "adminOperation";
    }

    @RequestMapping(path = "/message", method = RequestMethod.GET)
    public String message() {
        return "message";
    }

}

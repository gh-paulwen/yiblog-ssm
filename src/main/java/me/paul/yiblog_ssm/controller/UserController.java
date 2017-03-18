package me.paul.yiblog_ssm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import me.paul.yiblog_ssm.entity.User;
import me.paul.yiblog_ssm.mapper.UserMapper;
import me.paul.yiblog_ssm.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	private String target;
	
	@RequestMapping(path="/login",method=RequestMethod.GET)
	public String login(@RequestParam("target") String target,Model model){
		this.target = target;
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(path="submitLogin",method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("user") User user,Model model,HttpSession session){
		String password = CommonUtil.generateMD5(user.getPassword());
		user = userMapper.getByName(user.getName().trim());
		if(user == null){
			model.addAttribute("message", "user does not exist");
			return "login";
		}
		if(!user.getPassword().equalsIgnoreCase(password)){
			model.addAttribute("message", "invalid password");
			return "login";
		}
		if(user.getPower().getId()!=1){
			model.addAttribute("message", "you are not a adminstrator");
			return "login";
		}
		
		session.setAttribute("currentAdmin", user);
		
		return "redirect:" + target;
	}
	
	@RequestMapping(path="/getAll",method=RequestMethod.GET)
	public String getAll(){
		List<User> list = userMapper.getAll();
		for(User user : list){
			System.out.println(user.getPower().getName());
		}
		return "index";
	}

}

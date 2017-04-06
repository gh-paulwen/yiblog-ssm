package me.paul.yiblog_ssm.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.User;
import me.paul.yiblog_ssm.mapper.UserMapper;
import me.paul.yiblog_ssm.service.UserService;
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
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(path="/login",method=RequestMethod.GET)
	public String login(@RequestParam("target") String target,Model model){
		model.addAttribute("user", new User());
		model.addAttribute("target", target);
		return "login";
	}
	
	@RequestMapping(path="/submitLogin",method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("user") User user,@RequestParam("target") String target,Model model,HttpSession session){
		String password = CommonUtil.generateMD5(user.getPassword());
		String name = user.getName().trim();
		user = userMapper.getByName(name);
		if(user == null){
			user = userMapper.getByEmail(name);
			if(user == null){
				model.addAttribute("message", "user does not exist");
				return "login";
			}
		}
		if(!user.getPassword().equalsIgnoreCase(password)){
			model.addAttribute("message", "invalid password");
			return "login";
		}
		if(user.getPower() == null || user.getPower().getId() < 1 || user.getPower().getId() > 2){
			model.addAttribute("message", "you are not a adminstrator");
			return "login";
		}
		session.setAttribute("currentUser", user);
		user.setLastLoginDate(new Date());
		userMapper.update(user);
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
	
	@RequestMapping(path="/register",method=RequestMethod.POST)
	public String register(@ModelAttribute("user")User user,Model model) throws UnsupportedEncodingException, MessagingException{
		ModelContent mc = userService.register(user);
		mc.fillInModel(model);
//		model.addAttribute("email",email);
//		String code = CommonUtil.generateVerifyCode();
//		Map<String,String> saving = new HashMap<>();
//		saving.put("code", code);
//		saving.put("password", password);
//		memcachedUtil.save(email, saving, 1800);
//		javaMailUtil.send(email, "欢迎注册本网站，您的验证码为：" + code + "，30分钟内有效");
		return "verify";
	}
	
	@RequestMapping(path="/verify",method=RequestMethod.POST)
	public String verify(@RequestParam("email") String email,@RequestParam("code") String code,Model model){
		ModelContent mc = userService.verify(email, code);
		mc.fillInModel(model);
		return "message";
	}
	
	@RequestMapping(path="/forget",method=RequestMethod.POST)
	public String forget(@RequestParam("email") String email,Model model){
		ModelContent mc = userService.forget(email);
		mc.fillInModel(model);
		return mc.getDestination();
	}
	
	@RequestMapping(path="/resetVerify",method=RequestMethod.POST)
	public String resetVerify(@RequestParam("email") String email,@RequestParam("code") String code ,Model model){
		ModelContent mc = userService.resetVerify(email, code);
		mc.fillInModel(model);
		return mc.getDestination();
	}
	
	@RequestMapping(path="/resetPassword",method=RequestMethod.POST)
	public String resetPassword(@RequestParam("email") String email,@RequestParam("code") String code,@RequestParam("password") String password,@RequestParam("repeatPassword") String repeatPassword,Model model){
		ModelContent mc = userService.resetPassword(email, code, password, repeatPassword);
		mc.fillInModel(model);
		return mc.getDestination();
	}
	
	@RequestMapping(path="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session,Model model){
		session.removeAttribute("currentUser");
		model.addAttribute("message", "退出成功");
		return "message";
	}
	
}

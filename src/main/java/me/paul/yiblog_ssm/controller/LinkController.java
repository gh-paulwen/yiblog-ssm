package me.paul.yiblog_ssm.controller;

import java.util.List;

import me.paul.yiblog_ssm.entity.Link;
import me.paul.yiblog_ssm.mapper.LinkMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/link")
public class LinkController {
	
	private LinkMapper linkMapper ;
	
	public void setLinkMapper(LinkMapper linkMapper) {
		this.linkMapper = linkMapper;
	}
	
	@RequestMapping(path="/save",method=RequestMethod.GET)
	public String save(Model model){
		model.addAttribute("link",new Link());
		return "link";
	}
	
	@RequestMapping(path="/pass/{id}",method=RequestMethod.GET)
	public String pass(@PathVariable("id")long id,Model model){
		Link link = new Link();
		link.setId(id);
		link.setPass(1);
		linkMapper.update(link);
		return "redirect:/operation";
	}
	
	@RequestMapping(path="/submitSave",method=RequestMethod.POST)
	public String submitSave(@ModelAttribute("link")Link link,Model model){
		String name = link.getName();
		String url = link.getUrl();
		String email = link.getEmail();
		String introduction = link.getIntroduction();
		if(name == null || url == null || email==null || introduction==null){
			model.addAttribute("message", "申请失败，没有完整填写信息");
			return "message";
		}
		if(name.isEmpty()||url.isEmpty()||email.isEmpty()||introduction.isEmpty()){
			model.addAttribute("message", "申请失败，没有完整填写信息");
			return "message";
		}
		linkMapper.insert(link);
		model.addAttribute("message", "申请成功，会尽快测评并回复");
		return "message";
	}
	
	@RequestMapping(path="/getLink",method=RequestMethod.GET)
	public String getPass(Model model){
		List<Link> list = linkMapper.getPass();
		model.addAttribute("listLink", list);
		return "linkList";
	}

}

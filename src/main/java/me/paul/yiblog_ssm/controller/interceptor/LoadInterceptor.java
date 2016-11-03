package me.paul.yiblog_ssm.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.paul.yiblog_ssm.entity.Announcement;
import me.paul.yiblog_ssm.entity.Link;
import me.paul.yiblog_ssm.entity.SubCategory;
import me.paul.yiblog_ssm.mapper.AnnouncementMapper;
import me.paul.yiblog_ssm.mapper.LinkMapper;
import me.paul.yiblog_ssm.mapper.SubCategoryMapper;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoadInterceptor extends HandlerInterceptorAdapter {
	
	
	private SubCategoryMapper subCategoryMapper;
	
	private AnnouncementMapper announcementMapper;
	
	private LinkMapper linkMapper;
	
	public void setAnnouncementMapper(AnnouncementMapper announcementMapper) {
		this.announcementMapper = announcementMapper;
	}
	
	public void setSubCategoryMapper(SubCategoryMapper subCategoryMapper) {
		this.subCategoryMapper = subCategoryMapper;
	}
	
	public void setLinkMapper(LinkMapper linkMapper) {
		this.linkMapper = linkMapper;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		List<SubCategory> list = subCategoryMapper.getAll();
		int halfsize = list.size()/2;
		request.setAttribute("listSubCategory", list);
		request.setAttribute("halfOfCates", halfsize);
		
		Announcement build = announcementMapper.getById(2l);
		Announcement lastUpdate = announcementMapper.getById(3l);
		request.setAttribute("build", build);
		request.setAttribute("lastUpdate", lastUpdate);
		
		List<Link> listLink = linkMapper.getPass();
		request.setAttribute("listLink", listLink);
		return true;
	}
}
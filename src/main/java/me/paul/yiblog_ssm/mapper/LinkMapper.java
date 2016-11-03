package me.paul.yiblog_ssm.mapper;

import java.util.List;

import me.paul.yiblog_ssm.entity.Link;

public interface LinkMapper {
	
	void insert (Link link);
	
	void update(Link link);
	
	List<Link> getPass();
	
	List<Link> getAll();

}

package me.paul.yiblog_ssm.mapper;

import java.util.List;

import me.paul.yiblog_ssm.entity.User;

public interface UserMapper {
	
	User getByName(String name);
	
	User getByEmail(String email);
	
	List<User> getAll();

}

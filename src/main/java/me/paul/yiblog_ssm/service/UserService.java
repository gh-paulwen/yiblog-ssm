package me.paul.yiblog_ssm.service;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.User;

public interface UserService {
	
	ModelContent register(User user);
	
	ModelContent verify(String email,String code);
	
	ModelContent forget(String email);
	
	ModelContent resetVerify(String email,String code);
	
	ModelContent resetPassword(String email,String code ,String password,String repeatPassword);
	
}

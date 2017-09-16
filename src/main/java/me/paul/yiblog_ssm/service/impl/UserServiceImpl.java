package me.paul.yiblog_ssm.service.impl;

import me.paul.yiblog_ssm.dto.ModelContent;
import me.paul.yiblog_ssm.entity.User;
import me.paul.yiblog_ssm.mapper.UserMapper;
import me.paul.yiblog_ssm.service.UserService;
import me.paul.yiblog_ssm.util.CommonUtil;
import me.paul.yiblog_ssm.util.JavaMailUtil;

import me.paul.yiblog_ssm.util.MemcachedUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Override
	public ModelContent register(User user) {
		return null;
	}

	@Override
	public ModelContent verify(String email, String code) {
		return null;
	}

	@Override
	public ModelContent forget(String email) {
		return null;
	}

	@Override
	public ModelContent resetVerify(String email, String code) {
		return null;
	}

	@Override
	public ModelContent resetPassword(String email, String code, String password, String repeatPassword) {
		return null;
	}

//	@Autowired
//	private JavaMailUtil javaMailUtil;
//
//	@Autowired
//	private MemcachedUtil memcachedUtil;
//
//	@Autowired
//	private UserMapper userMapper;
//
//	@Override
//	public ModelContent register(User user){
//		ModelContent mc = new ModelContent();
//		String code = CommonUtil.generateVerifyCode();
//		memcachedUtil.set(user.getEmail() + "_code", code , 1800);
//		memcachedUtil.set(user.getEmail() + "_user", user , 1800);
//		javaMailUtil.send(user.getEmail(), "欢迎注册本网站，您的验证码为：" + code + "，30分钟内有效");
//		mc.save("email", user.getEmail());
//		return mc;
//	}
//
//	@Override
//	public ModelContent verify(String email, String code) {
//		ModelContent mc = new ModelContent();
//		String trueCode = memcachedUtil.get(email + "_code", String.class);
//		if(trueCode == null){
//			mc.save("message", "验证码已过期");
//			return mc;
//		}
//		if(!trueCode.equals(code)){
//			mc.save("message", "验证码错误");
//			return mc;
//		}
//		User user = memcachedUtil.get(email + "_user", User.class);
//		user.setPassword(CommonUtil.generateMD5(user.getPassword()));
//		userMapper.insert(user);
//		mc.save("message", "注册成功");
//		return mc;
//	}
//
//	@Override
//	public ModelContent forget(String email) {
//		ModelContent mc = new ModelContent("resetVerify");
//		User user = userMapper.getByEmail(email);
//		if(user == null){
//			mc.save("message", "该邮箱未被注册");
//			mc.setDestination("message");
//			return mc;
//		}
//		String code = CommonUtil.generateVerifyCode();
//		javaMailUtil.send(email, "您正在找回密码，您的验证码为：" + code + "，30分钟内有效");
//		memcachedUtil.set(email + "_code",code,1800);
//		mc.save("email", email);
//		return mc;
//	}
//
//	@Override
//	public ModelContent resetVerify(String email, String code) {
//		ModelContent mc = new ModelContent();
//		String trueCode = memcachedUtil.get(email + "_code", String.class);
//		if(trueCode == null){
//			mc.save("message", "验证码已过期");
//			mc.setDestination("message");
//			return mc;
//		}
//		if(!trueCode.equals(code)){
//			mc.save("message", "验证码错误");
//			mc.setDestination("message");
//			return mc;
//		}
//		mc.save("email", email);
//		mc.save("code", code);
//		mc.setDestination("resetPassword");
//		return mc;
//	}
//
//	@Override
//	public ModelContent resetPassword(String email, String code,
//			String password, String repeatPassword) {
//		ModelContent mc = new ModelContent("message");
//		String trueCode = memcachedUtil.get(email + "_code", String.class);
//		if(trueCode == null){
//			mc.save("message", "验证码已过期");
//			return mc;
//		}
//		if(!trueCode.equals(code)){
//			mc.save("message", "验证码错误");
//			return mc;
//		}
//		if(password == null){
//			mc.save("message", "密码不能为空");
//			return mc;
//		}
//		if(!password.equals(repeatPassword)){
//			mc.save("message", "两次密码输入不一致");
//			return mc;
//		}
//		User user = userMapper.getByEmail(email);
//		if(user == null){
//			mc.save("message", "该邮箱未被注册");
//			return mc;
//		}
//		user.setPassword(CommonUtil.generateMD5(password));
//		userMapper.update(user);
//		mc.save("message", "密码修改成功");
//		return mc;
//	}
	
}

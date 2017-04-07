package me.paul.yiblog_ssm.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
	
	private Properties props;
	
	private String account;
	
	private String accountName;
	
	private String password;
	
	public void setProps(Properties props) {
		this.props = props;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public void send(String email,String content) {
		
		
		Authenticator auth = new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account,password);
			}
		};
		
		Session session = Session.getDefaultInstance(props,auth);
		Message message = new MimeMessage(session);
		try{
			message.setHeader("Content-Type", "text/html;charset=utf-8");
			message.setSubject("来自FORUM的验证码");
			message.setFrom(new InternetAddress(account,accountName));
			message.setRecipient(RecipientType.TO, new InternetAddress(email));
			message.setText(content);
			Transport.send(message);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}

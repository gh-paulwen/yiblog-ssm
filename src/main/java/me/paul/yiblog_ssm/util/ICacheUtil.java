package me.paul.yiblog_ssm.util;

import java.util.List;
import java.util.Map;

public interface ICacheUtil {
	
	void save(String key,Object obj);
	
	void save(String key,Object obj,int exp);
	
	void set(String key,Object obj);
	
	void set(String key,Object obj,int exp);
	
	<T> T get(String key,Class<T> c);
	
	<T> List<T> list(List<String> keys,Class<T> c);
	
	void batchSave(Map<String,Object> entrys);
	
	void flush();

}

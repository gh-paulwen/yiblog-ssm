package me.paul.yiblog_ssm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

public class MemcachedUtil implements ICacheUtil{
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private MemcachedClient mmc;
	
	private int exp;
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void shutdown(){
		mmc.shutdown();
	} 
	
	@Override
	public <T> T get(String key,Class<T> c) {
		String objKey = (String) mmc.get(key);
		T t = gson.fromJson(objKey, c);
		return t;
	}
	
	@Override
	public void save(String key,Object obj){
		String value = gson.toJson(obj);
		mmc.add(key, exp,value);
	}
	
	@Override
	public void set(String key,Object obj){
		String value = gson.toJson(obj);
		mmc.set(key, exp, value);
	}
	
	@Override
	public void batchSave(Map<String,Object> map){
		Set<String> keys = map.keySet();
		for(String key:keys){
			String value = gson.toJson(map.get(key));
			mmc.add(key, exp, value);
		}
	}
	
	@Override
	public <T> List<T> list(List<String> keys,Class<T> c){
		List<T> list = new ArrayList<>();
		for(String key:keys){
			String objKey = (String) mmc.get(key);
			T t = gson.fromJson(objKey,c);
			list.add(t);
		}
		return list;
	}
	
	@Override
	public void flush(){
		mmc.flush();
	}

	@Override
	public void save(String key, Object obj, int exp) {
		String value = gson.toJson(obj);
		mmc.add(key, exp,value);
	}

	@Override
	public void set(String key, Object obj, int exp) {
		String value = gson.toJson(obj);
		mmc.set(key, exp, value);
	}
	
}

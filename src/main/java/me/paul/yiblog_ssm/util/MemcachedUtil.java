package me.paul.yiblog_ssm.util;

import com.google.gson.Gson;
import net.spy.memcached.MemcachedClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MemcachedUtil {
	
	private Gson gson;
	
	private MemcachedClient mmc;
	
	private int exp;
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void shutdown(){
		mmc.shutdown();
	} 
	
	public <T> T get(String key,Class<T> c) {
		String objKey = (String) mmc.get(key);
		T t = gson.fromJson(objKey, c);
		return t;
	}
	
	public void save(String key,Object obj){
		String value = gson.toJson(obj);
		mmc.add(key, exp,value);
	}
	
	public void set(String key,Object obj){
		String value = gson.toJson(obj);
		mmc.set(key, exp, value);
	}
	
	public void batchSave(Map<String,Object> map){
		Set<String> keys = map.keySet();
		for(String key:keys){
			String value = gson.toJson(map.get(key));
			mmc.add(key, exp, value);
		}
	}
	
	public <T> List<T> list(List<String> keys,Class<T> c){
		List<T> list = new ArrayList<>();
		for(String key:keys){
			String objKey = (String) mmc.get(key);
			T t = gson.fromJson(objKey,c);
			list.add(t);
		}
		return list;
	}
	
	public void flush(){
		mmc.flush();
	}

	public void save(String key, Object obj, int exp) {
		String value = gson.toJson(obj);
		mmc.add(key, exp,value);
	}

	public void set(String key, Object obj, int exp) {
		String value = gson.toJson(obj);
		mmc.set(key, exp, value);
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public void setMmc(MemcachedClient mmc) {
		this.mmc = mmc;
	}
}


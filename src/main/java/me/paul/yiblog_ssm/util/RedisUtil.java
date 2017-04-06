package me.paul.yiblog_ssm.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import com.google.gson.Gson;

public class RedisUtil implements ICacheUtil{
	
	private String host;
	
	private int port;
	
	private String password;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	private final Gson gson = new Gson();
	
	@Override
	public void save(String key , Object obj){
		Jedis jedis = getJedis();
		String value = gson.toJson(obj);
		if(!jedis.exists(key)){
			jedis.set(key, value);
		}
		jedis.close();
	}
	
	@Override
	public void set(String key , Object obj){
		Jedis jedis = getJedis();
		String value = gson.toJson(obj);
		jedis.set(key, value);
		jedis.close();
	}
	
	@Override
	public void batchSave(Map<String,Object> entrys){
		Jedis jedis = getJedis();
		Iterator<String> it = entrys.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = gson.toJson(entrys.get(key));
			if(!jedis.exists(key)){
				jedis.set(key, value);
			}
		}
		jedis.close();
	}
	
	@Override
	public <T> T get(String key,Class<T> c){
		Jedis jedis = getJedis();
		String value = jedis.get(key);
		T t = gson.fromJson(value, c);
		jedis.close();
		return t;
	}
	
	@Override
	public <T> List<T> list(List<String> keys,Class<T> c){
		Jedis jedis = getJedis();
		List<T> ts = new ArrayList<T>();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = jedis.get(key);
			T t = gson.fromJson(value,c);
			ts.add(t);
		}
		jedis.close();
		return ts;
	}
	
	@Override
	public void flush(){
		Jedis jedis = getJedis();
		jedis.flushAll();
		jedis.close();
	}
	
	private Jedis getJedis(){
		JedisShardInfo jsi = new JedisShardInfo(host, port);
		jsi.setPassword(password);
		Jedis jedis = new Jedis(jsi);
		return jedis;
	}

	@Override
	public void save(String key, Object obj, int exp) {
		
	}

	@Override
	public void set(String key, Object obj, int exp) {
		
	}

}
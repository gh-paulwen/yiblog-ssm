package me.paul.yiblog_ssm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.paul.yiblog_ssm.entity.Passage;

public final class RedisPassageUtil implements ICachePassageUtil{
	
	private RedisUtil ru ;
	
	public void setRu(RedisUtil ru) {
		this.ru = ru;
	}
	
	@Override
	public void save(Passage p){
		ru.save(p.getRedisId(), p);
		p.setContent(null);
		ru.save(p.getRedisSimpleId(), p);
	}
	
	@Override
	public void set(Passage p){
		ru.set(p.getRedisId(), p);
		p.setContent(null);
		ru.set(p.getRedisSimpleId(), p);
	}
	
	@Override
	public void batchSave(List<Passage> list){
		Map<String,Object> full = new HashMap<>();
		Map<String,Object> simple = new HashMap<>();
		for(Passage p:list){
			full.put(p.getRedisId(), p);
		}
		ru.batchSave(full);
		for(Passage p:list){
			p.setContent(null);
			simple.put(p.getRedisSimpleId(), p);
		}
		ru.batchSave(simple);
	}
	
	@Override
	public Passage get(String id){
		return ru.get(id, Passage.class);
	}
	
	@Override
	public List<Passage> list(List<String> keys){
		return ru.list(keys, Passage.class);
	}
	
	@Override
	public void flush() {
		ru.flush();
	}

}

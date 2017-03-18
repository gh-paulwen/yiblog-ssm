package me.paul.yiblog_ssm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.paul.yiblog_ssm.entity.Passage;

public class MemcachedPassageUtil implements ICachePassageUtil{
	
	private MemcachedUtil mu;
	
	public void setMu(MemcachedUtil mu) {
		this.mu = mu;
	}
	
	@Override
	public void save(Passage passage){
		mu.save(passage.getRedisId(), passage);
		passage.setContent(null);
		mu.save(passage.getRedisSimpleId(), passage);
	}
	
	@Override
	public void set(Passage passage){
		mu.set(passage.getRedisId(), passage);
		passage.setContent(null);
		mu.set(passage.getRedisSimpleId(), passage);
	}
	
	@Override
	public void batchSave(List<Passage> list){
		Map<String,Object> full = new HashMap<>();
		Map<String,Object> simple = new HashMap<>();
		for(Passage p:list){
			full.put(p.getRedisId(), p);
		}
		mu.batchSave(full);
		for(Passage p:list){
			p.setContent(null);
			simple.put(p.getRedisSimpleId(), p);
		}
		mu.batchSave(simple);
	}
	
	@Override
	public Passage get(String id){
		return mu.get(id, Passage.class);
	}
	
	@Override
	public List<Passage> list(List<String> keys){
		return mu.list(keys, Passage.class);
	}

	@Override
	public void flush() {
		mu.flush();
	}

}

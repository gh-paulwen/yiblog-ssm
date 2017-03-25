package me.paul.yiblog_ssm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.mapper.PassageMapper;

public class MemcachedPassageUtil implements ICachePassageUtil{
	
	@Autowired
	private PassageMapper pm;
	
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
	public void flush() {
		mu.flush();
	}

	@Override
	public List<Passage> list(int page, int passagePerPage) {
		List<Long> list = pm.pageIds((page - 1) * passagePerPage, passagePerPage);
		List<String> keys= getKeys(Passage.REDIS_PREFIX , list);
		List<Passage> passages = mu.list(keys, Passage.class);
		return passages;
	}
	
	private List<String> getKeys(String prefix,List<Long> ids){
		List<String> keys = new ArrayList<>();
		for(Long id : ids){
			keys.add(prefix + id);
		}
		return keys;
	}

	@Override
	public List<Passage> list(int page, int passagePerPage, long category) {
		List<Long> list = pm.categoryPageIds(category, (page - 1) * passagePerPage, passagePerPage);
		List<String> keys = getKeys(Passage.REDIS_PREFIX,list);
		return mu.list(keys, Passage.class);
	}

	@Override
	public List<Passage> simpleList(int page, int passagePerPage) {
		List<Long> list = pm.pageIds((page - 1) * passagePerPage, passagePerPage);
		List<String> keys= getKeys(Passage.REDIS_SIMPLE , list);
		List<Passage> passages = mu.list(keys, Passage.class);
		return passages;
	}

	@Override
	public List<Passage> simpleList(int page, int passagePerPage, long category) {
		List<Long> list = pm.categoryPageIds(category, (page - 1) * passagePerPage, passagePerPage);
		List<String> keys = getKeys(Passage.REDIS_SIMPLE,list);
		return mu.list(keys, Passage.class);
	}

}

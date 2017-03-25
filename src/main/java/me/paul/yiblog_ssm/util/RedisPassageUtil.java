package me.paul.yiblog_ssm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import me.paul.yiblog_ssm.entity.Passage;
import me.paul.yiblog_ssm.mapper.PassageMapper;

public final class RedisPassageUtil implements ICachePassageUtil{
	
	@Autowired
	private PassageMapper pm;
	
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
	public List<Passage> list(int page,int passagePerPage){
		List<Long> list = pm.pageIds((page - 1) * passagePerPage, passagePerPage);
		List<String> keys = getKeys(Passage.REDIS_PREFIX,list);
		return ru.list(keys, Passage.class);
	}
	
	@Override
	public List<Passage> list(int page, int passagePerPage, long category) {
		List<Long> list = pm.categoryPageIds(category, (page - 1) * passagePerPage, passagePerPage);
		List<String> keys = getKeys(Passage.REDIS_PREFIX,list);
		return ru.list(keys, Passage.class);
	}
	
	@Override
	public List<Passage> simpleList(int page, int passagePerPage) {
		List<Long> list = pm.pageIds((page - 1) * passagePerPage, passagePerPage);
		List<String> keys = getKeys(Passage.REDIS_SIMPLE,list);
		return ru.list(keys, Passage.class);
	}

	@Override
	public List<Passage> simpleList(int page, int passagePerPage, long category) {
		List<Long> list = pm.categoryPageIds(category, (page - 1) * passagePerPage, passagePerPage);
		List<String> keys = getKeys(Passage.REDIS_SIMPLE,list);
		return ru.list(keys, Passage.class);
	}
	
	@Override
	public void flush() {
		ru.flush();
	}

	private List<String> getKeys(String prefix,List<Long> ids){
		List<String> keys = new ArrayList<>();
		for(Long id : ids){
			keys.add(prefix + id);
		}
		return keys;
	}
}

package me.paul.yiblog_ssm.util;

import java.util.List;

import me.paul.yiblog_ssm.entity.Passage;

public interface ICachePassageUtil {
	
	void save(Passage passage);
	
	void set(Passage passage);
	
	Passage get(String key);
	
	List<Passage> list(List<String> keys);
	
	void batchSave(List<Passage> list);
	
	void flush();

}

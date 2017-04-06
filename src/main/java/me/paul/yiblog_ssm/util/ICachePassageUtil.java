package me.paul.yiblog_ssm.util;

import java.util.List;

import me.paul.yiblog_ssm.entity.Passage;

public interface ICachePassageUtil {
	
	void save(Passage passage);
	
	void set(Passage passage);
	
	Passage get(long key);
	
	List<Passage> list(int page,int passagePerPage);
	
	List<Passage> list(int page,int passagePerPage,long category);
	
	List<Passage> simpleList(int page,int passagePerPage);
	
	List<Passage> simpleList(int page,int passagePerPage,long category);
	
	void batchSave(List<Passage> list);
	
	void flush();
	
}
